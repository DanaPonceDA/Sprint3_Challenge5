package com.example.sprint3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank; // Nuevo
import jakarta.validation.constraints.NotNull; // Nuevo
import jakarta.validation.constraints.Positive; // Nuevo
import lombok.Data; 
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "T_ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del cliente es obligatorio") // VALIDACIÓN: No vacío

    private String customerName;
    private LocalDateTime orderDate = LocalDateTime.now();

    @NotNull(message = "El monto total es obligatorio") // VALIDACIÓN: No nulo
    @Positive(message = "El monto total debe ser positivo") // VALIDACIÓN: Mayor que cero
    private Double totalAmount;

    @NotBlank(message = "El estado es obligatorio")
    private String status; // Ej: PENDING, SHIPPED, DELIVERED

    // Constructor por defecto requerido por JPA
    public Order() {
    }
}