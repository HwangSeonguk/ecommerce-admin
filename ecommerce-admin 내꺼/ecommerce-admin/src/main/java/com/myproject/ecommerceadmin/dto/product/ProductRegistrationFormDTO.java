package com.myproject.ecommerceadmin.dto.product;

import com.myproject.ecommerceadmin.domain.product.Product;
import com.myproject.ecommerceadmin.enums.DeliveryType;
import com.myproject.ecommerceadmin.enums.ProductStatus;
import lombok.Data;


import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class ProductRegistrationFormDTO {
    private String name;
    private BigDecimal price;
    private int stockQuantity;
    private String productDesc;
    private DeliveryType deliveryType;
    private String createdBy;

    public Product toEntity(){
        OffsetDateTime now = OffsetDateTime.now();
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        product.setProductDesc(productDesc);
        product.setDeliveryType(deliveryType);
        product.setStatus(ProductStatus.READY_TO_SELL);
        product.setExposed(false);
        product.setCreatedAt(now);
        product.setCreatedBy(createdBy);
        return product;
    }
}
