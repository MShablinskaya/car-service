package com.innowise.sharing.controller;

import com.innowise.sharing.dto.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/orders")
public interface OrderRest {

    @PostMapping
    @Operation(summary = "Post new record", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The new car successfully saved "),
            @ApiResponse(responseCode = "403", description = "Access denied. Not enough rights")
    })
    ResponseEntity<HttpStatus> postNewOrder(@RequestBody OrderDto dto);

    @GetMapping("/{id}")
    @Operation(summary = "Get order by order's id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The car successfully received"),
            @ApiResponse(responseCode = "403", description = "Access denied. Not enough rights")
    })
    ResponseEntity<OrderDto> getOrderById(@PathVariable(name = "id") Long id);

    @GetMapping("/my-orders")
    @Operation(summary = "Get list of all cars", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list is successfully received"),
            @ApiResponse(responseCode = "403", description = "Access denied. Not enough rights")
    })
    ResponseEntity<List<OrderDto>> getMyOrders(@RequestBody String email);

    @PutMapping("/{id}")
    @Operation(summary = "Change car availability status to opposite", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status successfully changed"),
            @ApiResponse(responseCode = "403", description = "Access denied. Not enough rights")
    })
    ResponseEntity<HttpStatus> updateStateCarOrder(@PathVariable(name = "id") Long id,
                                                   @RequestBody String action);
}
