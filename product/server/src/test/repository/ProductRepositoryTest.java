package com.imooc.product.repository;

import com.imooc.product.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    private ProductInfoRepository productRepository;
    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> productInfoList = productRepository.findByProductStatus(0);
        Assert.assertTrue(productInfoList.size()>0);
    }

    @Test
    public void findByProductIdIn() throws Exception {
    }

}