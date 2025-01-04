package com.ngaleano.ferreteria_ng.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ngaleano.ferreteria_ng.model.Producto;
import com.ngaleano.ferreteria_ng.model.Venta;
import com.ngaleano.ferreteria_ng.repository.ProductoRepository;
import com.ngaleano.ferreteria_ng.repository.VentaRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VentaService {
    
    private VentaRepository ventaRepository;
    private ProductoRepository productoRepository;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int TICKET_CODE_LENGTH = 8;

    public Venta obtenerPorTicketCode(String ticketCode){
        return ventaRepository.findByTicketCode(ticketCode).orElseThrow(
            () -> new RuntimeException("No se encontro el codigo del ticket: " +ticketCode));
    }

    public List<Venta> obtenerPorFecha(LocalDateTime fecha){
        return ventaRepository.findByFecha(fecha);
    }

    public List<Venta> listarVentas(){
        return ventaRepository.findAll();
    }


    @Transactional
    public Venta guardarVenta(String codProducto, int cantProducto, double precio, LocalDateTime fecha){

        Producto productoExistente = productoRepository.findByCodProducto(codProducto);

        if (productoExistente == null) {
            throw new RuntimeException("No se encontro el codigo del producto");
        }

        if (productoExistente.getCantProducto() < cantProducto) {
            throw new RuntimeException("No hay cantidades disponibles para vender");
        }

        if (fecha == null) {
            throw new RuntimeException("La fecha no puede ser nula.");
        }
        
        productoExistente.setCantProducto(productoExistente.getCantProducto() - cantProducto);


        Venta ventaNueva = new Venta();

        ventaNueva.setPrecio(precio);
        ventaNueva.setFecha(fecha);
        ventaNueva.setProducto(productoExistente);
        ventaNueva.setTicketCode(generarTicketCodeUnico());
    
        return ventaRepository.save(ventaNueva);
    }


    private String generarTicketCodeUnico() {
        String ticketCode;
        do {
            ticketCode = generarTicketCode();
        } while (ventaRepository.findByTicketCode(ticketCode).isPresent());
        return ticketCode;
    }

    private String generarTicketCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder ticketCode = new StringBuilder(TICKET_CODE_LENGTH);
        for (int i = 0; i < TICKET_CODE_LENGTH; i++) {
            ticketCode.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        
        return ticketCode.toString();
    }

}
