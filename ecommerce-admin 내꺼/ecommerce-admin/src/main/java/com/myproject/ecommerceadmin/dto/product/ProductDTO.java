package com.myproject.ecommerceadmin.dto.product;

import com.myproject.ecommerceadmin.domain.product.Product;
import com.myproject.ecommerceadmin.enums.DeliveryType;
import com.myproject.ecommerceadmin.enums.ProductStatus;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String imagePath;
    private int stockQuantity;
    private String productDesc;
    private DeliveryType deliveryType;
    private int detailImageCount;
    private ProductStatus status;
    private boolean isExposed;
    private boolean isDeleted;
    private OffsetDateTime createdAt;
    private String createdBy;
    private OffsetDateTime updatedAt;
    private String updatedBy;
    private String thumbnailUrl;
    private List<String> ImageUrlList;

    public static ProductDTO of(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .imagePath(product.getImagePath())
                .stockQuantity(product.getStockQuantity())
                .productDesc(product.getProductDesc())
                .deliveryType(product.getDeliveryType())
                .detailImageCount(product.getDetailImageCount())
                .status(product.getStatus())
                .isExposed(product.isExposed())
                .isDeleted(product.isDeleted())
                .createdAt(product.getCreatedAt())
                .createdBy(product.getCreatedBy())
                .updatedAt(product.getUpdatedAt())
                .updatedBy(product.getUpdatedBy()).build();
    }
}
