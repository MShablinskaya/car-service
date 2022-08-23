package com.innowise.sharing.entity;

import com.innowise.sharing.enums.State;
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
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
public class Orders {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JoinColumn(name = "customer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Users customerId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(id, orders.id)
                && Objects.equals(customerId, orders.customerId)
                && Objects.equals(car_id, orders.car_id)
                && state == orders.state
                && Objects.equals(bookingDate, orders.bookingDate)
                && Objects.equals(returnDate, orders.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, car_id, state, bookingDate, returnDate);
    }
}
