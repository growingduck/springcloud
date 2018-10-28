package com.imooc.product.controller;

import com.imooc.product.VO.ProductInfoVO;
import com.imooc.product.VO.ProductVO;
import com.imooc.product.VO.ResultVO;
import com.imooc.product.dataobject.ProductCategory;
import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.service.CategoryService;
import com.imooc.product.service.ProductService;
import com.imooc.product.uitls.ResultVOUtil;
import common.DecreaseStockInput;
import common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO<List<ProductVO>> list(HttpServletRequest request) {
        //1. 查询所有在架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2. 获取类目type列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        //3. 从数据库查询类目
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //4. 构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }

//    public static void main(String[] args) {
//        List<ProductInfo> productInfoList = new ArrayList<>();
//        for(int i=0;i<5;i++){
//            ProductInfo info = new ProductInfo();
//            info.setProductId(i+"");
//            info.setCategoryType(i+10);
//            productInfoList.add(info);
//        }
//        List<Integer> collect = productInfoList.stream()
//                .map(ProductInfo::getCategoryType)
//                //map函数，对原stream中类型转换，ProductInfo转成了Integer,ProductInfo是类名
//                .collect(Collectors.toList());
//        List<Integer> collect2 = productInfoList.stream()
//                .map(productInfo->productInfo.getCategoryType())
//                .collect(Collectors.toList());//此种写法效果与上面一样，productInfo是对象，名字可以随便取
//        List<ProductInfo> collect3 = productInfoList.stream()
//                .filter(productInfo -> productInfo.getCategoryType() == 10)
//                //filter函数，对原stream中满足条件的数据过滤筛选，类型不会变（只能用 -> 的写法）
//                .collect(Collectors.toList());
//        System.out.println(collect.toString()+"-------"+collect2.toString()+"-------"+collect3.toString());
//
//    }

    /**
     * 获取商品列表(给订单服务用的)
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList) {
            return productService.findList(productIdList);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList) {
        productService.decreaseStock(decreaseStockInputList);
    }

}
