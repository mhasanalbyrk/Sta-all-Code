package com.obss.jss.onlinemarketplace.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class BlackList extends BaseEntity{
    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User owner;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Seller> sellerBlackList;
}
