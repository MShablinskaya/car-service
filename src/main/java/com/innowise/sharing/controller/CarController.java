package com.innowise.sharing.controller;

import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.exception.CarException;
import com.innowise.sharing.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {
    private final CarService carService;

    //for owners
    @GetMapping("/all")
    @Operation(summary = "Get list of all cars", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list is successfully received"),
            @ApiResponse(responseCode = "403", description = "Access denied. Not enough rights")
    })
    public ResponseEntity<List<CarDto>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get car by car's ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The car successfully received"),
            @ApiResponse(responseCode = "403", description = "Access denied. Not enough rights")
    })
    public ResponseEntity<CarDto> getCarById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(carService.findCarById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Change car availability status to opposite", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status successfully changed"),
            @ApiResponse(responseCode = "403", description = "Access denied. Not enough rights")
    })
    public ResponseEntity<CarDto> changeCarAvailabilityStatus(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(carService.changeAvailabilityStatus(id));
    }

    @PostMapping
    @Operation(summary = "Post new record", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The new car successfully saved "),
            @ApiResponse(responseCode = "403", description = "Access denied. Not enough rights")
    })
    public ResponseEntity<CarDto> addNewCarToList(@RequestBody CarDto dto) throws CarException {
        return ResponseEntity.ok(carService.addNewCarToList(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete car from list. Opportunity for owners only.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The car successfully deleted"),
            @ApiResponse(responseCode = "202", description = "The car accepted for delete"),
            @ApiResponse(responseCode = "204", description = "The car deleted, no content for response body"),
            @ApiResponse(responseCode = "403", description = "Access denied. Not enough rights")
    })
    public ResponseEntity deleteCar(@PathVariable(name = "id") Long id) throws CarException {
        carService.deleteCar(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    //for customers
    @GetMapping("/available")
    @Operation(summary = "Get list of available cars", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "The list is successfully received")
    public ResponseEntity<List<CarDto>> getAvailableCars() {
        return ResponseEntity.ok(carService.getAvailableCars());
    }
}
