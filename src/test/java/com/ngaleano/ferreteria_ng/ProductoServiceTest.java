package com.ngaleano.ferreteria_ng;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.ngaleano.ferreteria_ng.model.Producto;
import com.ngaleano.ferreteria_ng.repository.ProductoRepository;
import com.ngaleano.ferreteria_ng.service.ProductoService;

@SpringBootTest
public class ProductoServiceTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    public void setup() {
        producto = new Producto();
        producto.setCodProducto("MART001");
        producto.setNombreProducto("Martillo");
        producto.setUbicacion("Estante 7");

        productoRepository.save(producto);
    }

    @Test
    @DirtiesContext
    public void testObtenerPorId() {
        Long id = producto.getId();

        Producto result = productoService.obtenerPorId(id);

        assertNotNull(result);
        assertEquals("MART001", result.getCodProducto());
        assertEquals("Martillo", result.getNombreProducto());
    }

    @Test
    @DirtiesContext
    public void testObtenerPorCodigo() {
        String codigo = producto.getCodProducto();
        
        Producto result = productoService.obtenerPorCodigo(codigo);

        assertNotNull(result);
        assertEquals("Martillo", result.getNombreProducto());
    }

    @Test
    @DirtiesContext
    public void testGuardarProducto() {
        Producto nuevoProducto = new Producto();
        nuevoProducto.setCodProducto("DEST391");
        nuevoProducto.setNombreProducto("Destornillador");
        nuevoProducto.setUbicacion("B2");

        Producto result = productoService.guardarProducto(nuevoProducto);

        assertNotNull(result);
        assertEquals("DEST391", result.getCodProducto());
        assertEquals("Destornillador", result.getNombreProducto());
    }

    @Test
    @DirtiesContext
    public void testEliminarProducto() {
        Long id = producto.getId();
        productoService.eliminarProducto(id);

        Optional<Producto> result = productoRepository.findById(id);
        assertFalse(result.isPresent());
    }

    @Test
    @DirtiesContext
    public void testActualizarProducto() {
        Long id = producto.getId();
        Producto productoActualizado = new Producto();
        productoActualizado.setCodProducto("MART001");
        productoActualizado.setNombreProducto("Martillo Actualizado");
        productoActualizado.setUbicacion("Estante 5");

        productoService.actualizarProducto(id, productoActualizado);

        Producto result = productoService.obtenerPorId(id);
        assertEquals("Martillo Actualizado", result.getNombreProducto());
        assertEquals("Estante 5", result.getUbicacion());
    }

    
    @Test
    @DirtiesContext
    public void testListaProductos() {
        List<Producto> productos = productoService.listaProductos();
        assertTrue(productos.size() > 0);
    }
}