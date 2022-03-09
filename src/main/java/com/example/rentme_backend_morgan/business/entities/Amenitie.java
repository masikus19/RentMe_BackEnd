package com.example.rentme_backend_morgan.business.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
//@EqualsAndHashCode(exclude = "realtyObjects")

@Entity
@Table(name = "amenities")
public class Amenitie {

    @Id
    @Column(length = 100)
    String name;

    @ManyToMany(cascade = CascadeType.ALL)
    Set<RealtyObject> realtyObjectss = new HashSet<>();

    public Amenitie(String name) {
        super();
        this.name = name;
    }


}
