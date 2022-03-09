package com.example.rentme_backend_morgan.business.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity(name = "realtyobject")
//@Table(name = "realtyobject")
public class RealtyObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(name = "name_object")
    String nameOfRentObject;

    @Column(name = "size_metr")
    int size;

    @Column(name = "flor_number")
    int floor;

    @Column(name = "bedrooms")
    int bedrooms;

    @Column(name = "is_rented")
    boolean rented;

    @Column(name = "appt_number")
    String apptNumber; //TODO -1

    @Column(name = "avatar_photo", columnDefinition = "MEDIUMTEXT")
    String avatarPhoto;//TODO -1

    @OneToMany(mappedBy = "realtyObject", cascade = CascadeType.ALL)
    Set<Announcement> announcement;

    @OneToMany(mappedBy = "realtyObject", cascade = CascadeType.ALL)
    List<Photo> photo;

    @ManyToOne(cascade = CascadeType.ALL)
    Address address;

    @ManyToOne(cascade = CascadeType.ALL)
    TypeOfRealtyObject typeOfRealtyObject;

    @ManyToOne
    Owner owner;

    @ManyToMany(mappedBy = "realtyObjectss", cascade = CascadeType.ALL)
    Set<Amenitie> amenities = new HashSet<>();

    public RealtyObject(
            Address address,
            Owner owner,
            String nameOfRentObject,
            String apptNumber,
            int size,
            int floor,
            int bedrooms,
            String avatarPhoto,
            TypeOfRealtyObject typeOfRealtyObject
    ) {
        this.address = address;
        this.nameOfRentObject = nameOfRentObject;
        this.size = size;
        this.floor = floor;
        this.bedrooms = bedrooms;
        this.apptNumber = apptNumber == null ? "" : apptNumber;
        this.owner = owner;
        this.avatarPhoto = avatarPhoto;
        this.typeOfRealtyObject = typeOfRealtyObject;
    }
}