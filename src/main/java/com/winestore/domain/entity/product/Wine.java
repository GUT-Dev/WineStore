package com.winestore.domain.entity.product;

import com.winestore.enums.AvailableStatus;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "descriptions")
    private String descriptions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "land_id")
    private Land land;

    @Column(name = "region")
    private String region;

    @ManyToOne(fetch = FetchType.LAZY)
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
    private Float sugarAmount;

    @Column(name = "ean")
    private String ean;

    @Column(name = "image_url")
    private String img;

    @Column(name = "available")
    private boolean available;

    @Enumerated(EnumType.STRING)
    @Column(name = "available_status")
    private AvailableStatus availableStatus;

    @Column(name = "visible")
    private boolean visible;

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

    @Transient
    private BigDecimal priceWithSale;

    @Transient
    private int rating;

    @PostLoad
    @PostPersist
    private void countPriceWithSale() {
        if (discount != 0) {
            priceWithSale = BigDecimal.valueOf(price.longValue() - (price.longValue() * discount / 100));
        } else {
            priceWithSale = price;
        }
    }
}
