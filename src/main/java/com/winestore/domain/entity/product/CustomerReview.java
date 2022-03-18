package com.winestore.domain.entity.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "customer_review")
@NoArgsConstructor
public class CustomerReview implements Serializable {

    @Serial
    private static final long serialVersionUID = 5427772784081118493L;

    @EmbeddedId
    UserWinePK id;

    @Column(name = "message")
    private String message;

    @Column(name = "rating")
    private short rating;

    @Column(name = "confirm")
    private boolean confirm;
}
