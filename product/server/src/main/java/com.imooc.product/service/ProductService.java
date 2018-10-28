package com.imooc.product.service;

import com.imooc.product.dataobject.ProductInfo;
import common.DecreaseStockInput;
import common.ProductInfoOutput;

import java.util.List;

public interface ProductService {
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findList(List<String> productIdList);


    /**
     * 扣库存
     * @param decreaseStockInputList
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
