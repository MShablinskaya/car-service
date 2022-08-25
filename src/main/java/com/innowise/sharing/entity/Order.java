package com.innowise.sharing.entity;

import com.innowise.sharing.enums.State;
import lombok.Data;
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
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @JoinColumn(name = "customer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User customerId;

    @JoinColumn(name = "car_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Car car_id;

    @Column(name = "state")
    private State state;

    @Column(name = "booking_date")
    private Timestamp bookingDate;

    @Column(name = "return_date")
    private Timestamp returnDate;
}
