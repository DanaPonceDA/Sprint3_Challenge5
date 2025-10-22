package com.example.sprint3;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

// CORRECCIÓN 1: Fuerza el orden de ejecución para las pruebas CRUD
@SpringBootTest 
@AutoConfigureMockMvc 
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderControllerIntegrationTest {

    private static final String API_URL = "/api/orders";
    // Base64 de "digitalnaouser:test-pass"
    private final String AUTH_HEADER = "Basic ZGlnaXRhbG5hb3VzZXI6dGVzdC1wYXNz"; 

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    // Clase auxiliar para generar JSON (más limpia que usar la clase Order directamente)
    record TestOrder(String customerName, double totalAmount, String status) {}
    
    private String createOrderJson(String name, double amount, String status) throws Exception {
        return objectMapper.writeValueAsString(new TestOrder(name, amount, status));
    }


    // --- 1. CASO DE ÉXITO: Crear (POST) ---
    @Test
    @Order(1) // Se ejecuta primero para crear el recurso ID=1
    void a_shouldCreateAndReturnOrder() throws Exception {
        mockMvc.perform(post(API_URL)
                .header("Authorization", AUTH_HEADER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(createOrderJson("Customer A", 50.0, "PENDING")))
                .andExpect(status().isCreated()) 
                .andExpect(jsonPath("$.id").value(1)) 
                .andExpect(jsonPath("$.customerName", is("Customer A")));
    }

    // --- 2. CASO DE FALLO: Seguridad (401) ---
    @Test
    @Order(2)
    void b_shouldReturn401WithoutAuth() throws Exception {
        mockMvc.perform(post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(createOrderJson("Unauthorized", 10.0, "NEW")))
                .andExpect(status().isUnauthorized()); // Espera HTTP 401
    }

    // --- 3. CASO DE FALLO: Validación (400) ---
    @Test
    @Order(3)
    void c_shouldReturn400OnInvalidData() throws Exception {
        // Envía dato inválido (-10.0)
        mockMvc.perform(post(API_URL)
                .header("Authorization", AUTH_HEADER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(createOrderJson("Invalid", -10.0, "NEW")))
                .andExpect(status().isBadRequest()) 
                // CORRECCIÓN 2: Busca "$.totalAmount" (lo que devuelve GlobalExceptionHandler)
                .andExpect(jsonPath("$.totalAmount", containsString("El monto total debe ser positivo")));
    }
    
    // --- 4. CASO DE ÉXITO: Actualizar y Eliminar (PUT/DELETE) ---
    @Test
    @Order(4)
    void d_shouldUpdateAndDeleteOrder() throws Exception {
        // 1. UPDATE (PUT) - Usa el ID=1 creado en el test 'a'
        mockMvc.perform(put(API_URL + "/1")
                .header("Authorization", AUTH_HEADER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(createOrderJson("Customer A Updated", 150.0, "SHIPPED")))
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$.status", is("SHIPPED")));

        // 2. DELETE (DELETE)
        mockMvc.perform(delete(API_URL + "/1")
                .header("Authorization", AUTH_HEADER))
                .andExpect(status().isNoContent()); // Espera HTTP 204
                
        // 3. Verifica la eliminación (Debe dar 404)
        mockMvc.perform(get(API_URL + "/1")
                .header("Authorization", AUTH_HEADER))
                .andExpect(status().isNotFound()); 
    }

    // --- 5. CASO DE BORDE: No Encontrado (404) ---
    @Test
    @Order(5) 
    void e_shouldReturn404WhenOrderNotFound() throws Exception {
        // Intenta obtener un ID que no existe (ej. 9999)
        mockMvc.perform(get(API_URL + "/9999")
                .header("Authorization", AUTH_HEADER))
                .andExpect(status().isNotFound()); 
    }
}