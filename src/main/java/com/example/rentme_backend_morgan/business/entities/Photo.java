package com.example.rentme_backend_morgan.business.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})

@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    @ManyToOne(cascade = CascadeType.ALL)
    RealtyObject realtyObject;

    //    @Column(name = "photo", columnDefinition = "MEDIUMTEXT")
    @Column(name = "photo")
    String photo;

    public Photo(RealtyObject realtyObject, String photo) {
        this.realtyObject = realtyObject;
        this.photo = photo;
    }
}
