package com.example.rentme_backend_morgan.business.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "realtyobject2")
public class RealtyObject2 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @OneToMany(mappedBy = "realtyobject2", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Photo> photo;

}