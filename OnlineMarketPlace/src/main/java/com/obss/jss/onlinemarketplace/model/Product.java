package com.obss.jss.onlinemarketplace.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Getter
@Setter
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {
    private String name;

    private String description;

    private String category;

    private int rating;

    private Long price;

    private Long inStock;
    @ManyToOne
    private Seller seller;

}
