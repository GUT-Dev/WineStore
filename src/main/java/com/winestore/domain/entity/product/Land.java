package com.winestore.domain.entity.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "land")
@NoArgsConstructor
public class Land implements Serializable {

    @Serial
    private static final long serialVersionUID = 2339480821761076499L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;
}
