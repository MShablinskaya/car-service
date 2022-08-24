package com.innowise.sharing.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
@Table(name = "cars")
public class Car {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Column(name = "release_year")
    private Timestamp releaseYear;

    @Column(name = "registration_number")
    private String registrationNumber;

    @JoinColumn(name = "owner_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User ownerId;

    @Column(name = "availability")
    private Boolean availability;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id)
                && Objects.equals(brand, car.brand)
                && Objects.equals(model, car.model)
                && Objects.equals(color, car.color)
                && Objects.equals(releaseYear, car.releaseYear)
                && Objects.equals(registrationNumber, car.registrationNumber)
                && Objects.equals(ownerId, car.ownerId)
                && Objects.equals(availability, car.availability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, color, releaseYear, registrationNumber, ownerId, availability);
    }
}
