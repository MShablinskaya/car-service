package com.innowise.sharing.it.tests.repository;

import com.innowise.sharing.entity.Order;
import com.innowise.sharing.entity.User;
import com.innowise.sharing.enums.State;
import com.innowise.sharing.it.BaseIntegrationTest;
import com.innowise.sharing.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Sql({"/data/erasure_documents.sql", "/data/insert_documents.sql",
        "/data/erasure_users.sql", "/data/insert_users.sql",
        "/data/erasure_cars.sql", "/data/insert_cars.sql",
        "/data/erasure_orders.sql", "/data/insert_orders.sql"})
class OrderIntegrationTest extends BaseIntegrationTest {
    public static final Long ID = 1L;
    public static final Long ID_NOT_EXIST = 2L;
    public static final State STATE = State.RESERVED;
    public static final Instant BOOKING_DATE = Instant.parse("2022-09-12T16:05:25.356401995Z");
    public static final Instant RETURN_DATE = Instant.parse("2022-09-12T16:05:25.356401995Z");
    public static final String EMAIL = "john_doe@yopmail.com";
    public static final String EMAIL_NOT_EXIST = "doe@yopmail.com";

    @Autowired
    private OrderRepository orderRepository;

    private final User customer = new User()
            .setId(ID)
            .setEmail(EMAIL);

    private final Order expectedOrder = new Order()
            .setId(ID)
            .setCustomer(customer)
            .setState(STATE)
            .setBookingDate(BOOKING_DATE)
            .setReturnDate(RETURN_DATE);

    @Test
    void findById_When_Success() {
        Order currentOrder = orderRepository.findById(ID).orElseThrow();

        assertNotNull(currentOrder);
        assertThat(currentOrder.getId()).isEqualTo(expectedOrder.getId());
    }

    @Test
    void selectMyOrders() {
        List<Order> currentOrders = orderRepository.selectMyOrders(EMAIL);

        assertNotNull(currentOrders);
        assertEquals(1, currentOrders.size());
    }

    @Test
    void save_When_Success() {
        Order currentOrder = orderRepository.save(expectedOrder);

        assertNotNull(currentOrder);
        assertThat(currentOrder.getId()).isEqualTo(expectedOrder.getId());
    }

    @Test
    void findById_When_Entity_Not_Found() {
        Order currentOrder = orderRepository.findById(ID_NOT_EXIST).orElse(null);

        assertNull(currentOrder);
    }

    @Test
    void selectMyOrders_When_Customer_Have_No_Orders() {
        List<Order> currentOrders = orderRepository.selectMyOrders(EMAIL_NOT_EXIST);

        assertTrue(currentOrders.isEmpty());
    }

}
