package com.winestore.domain.entity.cart;

import com.winestore.domain.entity.product.Wine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "cart_item")
@NoArgsConstructor
public class CartItem implements Serializable {

    @Serial
    private static final long serialVersionUID = 8712225284657742940L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wine_id")
    private Wine wine;

    private int amount;
}
