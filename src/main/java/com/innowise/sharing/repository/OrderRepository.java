package com.innowise.sharing.repository;

import com.innowise.sharing.entity.Order;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(value = "order-entity-graph")
    @NotNull
    Optional<Order> findById(Long id);

    @EntityGraph(value = "order-entity-graph")
    @Query("select o from Order o where o.customer.email=:email")
    List<Order> selectMyOrders(@Param("email") String email);
}
