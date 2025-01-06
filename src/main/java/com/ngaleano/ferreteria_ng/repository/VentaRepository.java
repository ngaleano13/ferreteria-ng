package com.ngaleano.ferreteria_ng.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngaleano.ferreteria_ng.model.Venta;
import java.util.List;
import java.time.LocalDate;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{

    Optional<Venta> findByTicketCode(String ticketCode);
    
    List<Venta> findByFecha(LocalDate fecha);
}
