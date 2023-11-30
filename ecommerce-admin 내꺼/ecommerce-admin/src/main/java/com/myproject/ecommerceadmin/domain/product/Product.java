package com.myproject.ecommerceadmin.domain.product;

import com.myproject.ecommerceadmin.enums.DeliveryType;
import com.myproject.ecommerceadmin.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "products", schema = "ecommerce")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "stock_quantity")
    private int stockQuantity;
    @Column(name = "product_desc")
    private String productDesc;
    @Column(name = "delivery_type")
    @Enumerated(value = EnumType.STRING)
    private DeliveryType deliveryType;
    @Column(name = "detail_image_count")
    private int detailImageCount;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private ProductStatus status;
    @Column(name = "is_exposed")
    private boolean isExposed;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
    @Column(name = "updated_by")
    private String updatedBy;

}
