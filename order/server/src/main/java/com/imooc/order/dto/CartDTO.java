package com.imooc.order.dto;

import lombok.Data;

@Data
public class CartDTO {

    /**
     * 商品id
     */
    private String productId;
    /**
     * 扣减数量
     */
    private Integer productQuantity;

    public CartDTO(){
        super();
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
