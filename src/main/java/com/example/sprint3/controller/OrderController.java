package com.example.sprint3.controller;

import com.example.sprint3.model.Order;
import com.example.sprint3.repository.OrderRepository;
import io.swagger.v3.oas.annotations.Operation; // Nuevo
import io.swagger.v3.oas.annotations.tags.Tag; // Nuevo
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid; // Nuevo: Para activar la validación en el POST/PUT
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Gestión de Pedidos", description = "CRUD de órdenes para el equipo Digital NAO")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    // 1. CREATE (POST)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) 
    @Operation(summary = "Crear un nuevo pedido", description = "Registra una nueva orden en el sistema.")
    public Order createOrder(@Valid @RequestBody Order order) { // Se usa @Valid aquí
        return orderRepository.save(order);
    }

    // 2. READ All (GET)
    @GetMapping
    @Operation(summary = "Obtener todos los pedidos", description = "Devuelve una lista de todas las órdenes registradas.")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 2. READ By ID (GET)
    @GetMapping("/{id}")
    @Operation(summary = "Obtener pedido por ID", description = "Devuelve el pedido por ID")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok) // Si existe, devuelve 200 OK
                .orElse(ResponseEntity.notFound().build()); // Si no existe, devuelve 404 NOT FOUND
    }

    // 3. UPDATE (PUT)
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar pedido por ID", description = "Actualiza el pedido")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        return orderRepository.findById(id)
                .map(order -> {
                    // Actualiza solo los campos que pueden cambiar
                    order.setCustomerName(orderDetails.getCustomerName());
                    order.setTotalAmount(orderDetails.getTotalAmount());
                    order.setStatus(orderDetails.getStatus());
                    
                    Order updatedOrder = orderRepository.save(order);
                    return ResponseEntity.ok(updatedOrder);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. DELETE (DELETE)
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar el pedido por ID", description = "Devuelve aviso de eliminacion")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // HTTP 204 No Content
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}