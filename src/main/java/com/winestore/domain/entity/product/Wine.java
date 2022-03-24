package com.winestore.domain.entity.product;

import com.winestore.enums.Sweetness;
import com.winestore.enums.WineType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "wine")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Wine implements Serializable {

    @Serial
    private static final long serialVersionUID = 9117659239522049469L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "descriptions")
    private String descriptions;

    @ManyToOne
    @JoinColumn(name = "land_id")
    private Land land;

    @Column(name = "region")
    private String region;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Enumerated
    @Column(name = "type")
    private WineType type;

    @Enumerated
    @Column(name = "sweetness")
    private Sweetness sweetness;

    @Column(name = "strength")
    private int strength;

    @Column(name = "sugar_amount")
    private float sugarAmount;

    @Column(name = "ean")
    private String EAN;

    @Column(name = "image_url")
    private String image;

    @Column(name = "enable")
    private boolean enable;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "discount")
    private int discount;

    @Column(name = "amount_for_sale")
    private int amountForSale;

    @Column(name = "sold_amount")
    private int soldAmount;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
