package com.ngaleano.ferreteria_ng;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.ngaleano.ferreteria_ng.model.Producto;
import com.ngaleano.ferreteria_ng.model.Venta;
import com.ngaleano.ferreteria_ng.repository.ProductoRepository;
import com.ngaleano.ferreteria_ng.repository.VentaRepository;
import com.ngaleano.ferreteria_ng.service.VentaService;

@SpringBootTest
public class VentaServiceTest {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    private Producto productoExistente;
    
    @BeforeEach
    void setup() {

        productoExistente = new Producto();
        productoExistente.setCodProducto("P123");
        productoExistente.setCantProducto(100);
        productoExistente.setUbicacion("F391");
        productoExistente.setNombreProducto("Tornillos");

        productoRepository.save(productoExistente);
    }

    @Test
    @DirtiesContext
    void testObtenerPorTicketCode() {
        Venta venta = new Venta();
        venta.setPrecio(200.0);
        venta.setFecha(LocalDateTime.now());
        venta.setProducto(productoExistente);
        venta.setTicketCode("ABC12345");

        ventaRepository.save(venta);

        Venta result = ventaService.obtenerPorTicketCode("ABC12345");

        assertNotNull(result);
        assertEquals("ABC12345", result.getTicketCode());
    }

    @Test
    @DirtiesContext
    void testGuardarVenta() {
        String codProducto = "P123";
        int cantProducto = 5;
        double precio = 100.0;
        LocalDateTime fecha = LocalDateTime.now();

        Venta venta = ventaService.guardarVenta(codProducto, cantProducto, precio, fecha);

        assertNotNull(venta);
        assertEquals(precio, venta.getPrecio());
        assertEquals(codProducto, venta.getProducto().getCodProducto());

        Producto productoActualizado = productoRepository.findByCodProducto(codProducto);
        assertEquals(95, productoActualizado.getCantProducto());
    }

    @Test
    @DirtiesContext
    void testGuardarVentaProductoNoExistente() {
        String codProducto = "P241824";

        Exception exception = assertThrows(RuntimeException.class, () -> {
            ventaService.guardarVenta(codProducto, 5, 100.0, LocalDateTime.now());
        });

        assertEquals("No se encontro el codigo del producto", exception.getMessage());
    }

    @Test
    @DirtiesContext
    void testGuardarVentaNoHayCantidadSuficiente() {
        String codProducto = "P123";
        int cantProducto = 200;

        Exception exception = assertThrows(RuntimeException.class, () -> {
            ventaService.guardarVenta(codProducto, cantProducto, 100.0, LocalDateTime.now());
        });

        assertEquals("No hay cantidades disponibles para vender", exception.getMessage());
    }
}
