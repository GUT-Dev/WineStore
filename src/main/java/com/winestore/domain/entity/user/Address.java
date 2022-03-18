package com.winestore.domain.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "address")
@NoArgsConstructor
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 2331275981375956264L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "land")
    private String land;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "home_number")
    private String homeNumber;
}
