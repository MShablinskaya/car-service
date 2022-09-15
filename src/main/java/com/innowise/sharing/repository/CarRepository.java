package com.innowise.sharing.repository;

import com.innowise.sharing.entity.Car;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    @EntityGraph(attributePaths = "owner")
    @NotNull
    Optional<Car> findById(@NotNull Long id);

    @EntityGraph(attributePaths = "owner")
    @Query("select c from Car c where c.availability=true")
    List<Car> selectAvailableCars();
}
