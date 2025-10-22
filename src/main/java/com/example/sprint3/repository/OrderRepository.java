package com.example.sprint3.repository;

import com.example.sprint3.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// <Tipo de Entidad, Tipo de la clave primaria>
public interface OrderRepository extends JpaRepository<Order, Long> {
}