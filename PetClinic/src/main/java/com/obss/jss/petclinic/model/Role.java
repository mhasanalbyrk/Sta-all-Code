package com.obss.jss.petclinic.model;

import lombok.*;
import lombok.experimental.SuperBuilder;


import javax.persistence.Entity;

@Getter
@Setter
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity{

    private String name;

}
