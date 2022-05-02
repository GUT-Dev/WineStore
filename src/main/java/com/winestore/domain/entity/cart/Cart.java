package com.winestore.domain.entity.cart;

import com.winestore.domain.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cart")
@NoArgsConstructor
public class Cart implements Serializable {

    @Serial
    private static final long serialVersionUID = -7659642770712016302L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "available")
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "cart_id")
    private Set<CartItem> cartItems;
}
