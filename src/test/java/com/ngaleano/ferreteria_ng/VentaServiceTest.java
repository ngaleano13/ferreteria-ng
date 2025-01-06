package com.ngaleano.ferreteria_ng;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.ngaleano.ferreteria_ng.model.Producto;
import com.ngaleano.ferreteria_ng.model.Venta;

import com.ngaleano.ferreteria_ng.service.ProductoService;
import com.ngaleano.ferreteria_ng.service.VentaService;

@SpringBootTest
public class VentaServiceTest {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private ProductoService productoService;

    private Producto producto1;

    @BeforeEach
    public void setup() {
        producto1 = new Producto();
        producto1.setCodProducto("DES193");
        producto1.setNombreProducto("Martillo");
        producto1.setUbicacion("A1");
        producto1 = productoService.guardarProducto(producto1);
    }

    @Test
    @DirtiesContext
    public void testObtenerPorTicketCode() {
        List<String> codigosProductos = List.of("DES193");
        Venta venta = ventaService.guardarVenta(codigosProductos, 100.0);

        Venta resultado = ventaService.obtenerPorTicketCode(venta.getTicketCode());

        assertNotNull(resultado);
        assertEquals(venta.getTicketCode(), resultado.getTicketCode());
    }

    @Test
    @DirtiesContext
    public void testObtenerPorTicketCodeNoEncontrado() {
        String ticketCodeInexistente = "TCKT1383";

        assertThrows(RuntimeException.class, () -> {
            ventaService.obtenerPorTicketCode(ticketCodeInexistente);
        });
    }

    @Test
    @DirtiesContext
    public void testGuardarVenta() {
        List<String> codigosProductos = List.of("DES193");
        double precio = 100.0;

        Venta venta = ventaService.guardarVenta(codigosProductos, precio);
        
        assertNotNull(venta.getTicketCode());
        assertEquals(precio, venta.getPrecio());
        assertEquals(1, venta.getProductos().size());
        assertEquals("Martillo", venta.getProductos().get(0).getNombreProducto());
    }

    @Test
    @DirtiesContext
    public void testListarVentas() {  
        List<String> codigosProductos = List.of("DES193");
        ventaService.guardarVenta(codigosProductos, 100.0);

        List<Venta> ventas = ventaService.listarVentas();

        assertNotNull(ventas);
        assertTrue(ventas.size() > 0);
    }

    @Test
    @DirtiesContext
    public void testObtenerPorFecha() {
        List<String> codigosProductos = List.of("DES193");
        Venta venta = ventaService.guardarVenta(codigosProductos, 100.0);

        List<Venta> ventas = ventaService.obtenerPorFecha(venta.getFecha());

        assertNotNull(ventas);
        assertTrue(ventas.size() > 0);
    }
}