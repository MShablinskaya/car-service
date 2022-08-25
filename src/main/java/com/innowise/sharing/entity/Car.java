package com.innowise.sharing.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_post"
    )
    @SequenceGenerator(
            name = "seq_post",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "registration_number")
    private String registrationNumber;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @ToString.Exclude
    private User ownerId;

    @Column(name = "availability")
    private Boolean availability;
}
