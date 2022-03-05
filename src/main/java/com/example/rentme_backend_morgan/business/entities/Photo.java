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

//    @ManyToOne
//    RealtyObject realtyObject;

    @ManyToOne
    RealtyObject2 realtyObject;

    @Column(name = "photo", columnDefinition = "MEDIUMTEXT")
    String photo;

    @ManyToOne
    @JoinColumn(name = "realtyobject2_id")
    private RealtyObject2 realtyobject2;

    public Photo(RealtyObject2 realtyObject, String photo) {
        this.realtyObject = realtyObject;
        this.photo = photo;
    }
}
