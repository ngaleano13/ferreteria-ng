package com.ngaleano.ferreteria_ng;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ngaleano.ferreteria_ng.model.Producto;
import com.ngaleano.ferreteria_ng.repository.ProductoRepository;
import com.ngaleano.ferreteria_ng.service.ProductoService;

@SpringBootTest
public class ProductoServiceTest {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private ProductoService productoService;


    private Producto productoExistente;


    @BeforeEach
    void setup() {

        productoExistente = new Producto();
        productoExistente.setCodProducto("P1041");
        productoExistente.setCantProducto(15);
        productoExistente.setUbicacion("F21");
        productoExistente.setNombreProducto("Tornillos");

        productoRepository.save(productoExistente);
    }

    @Test
    void testObtenerPorCodigo() {

        String codProd = "P1041";

        Producto resultado = productoService.obtenerPorCodigo(codProd);


        assertNotNull(resultado);
        assertEquals(codProd, resultado.getCodProducto());

    }

}
