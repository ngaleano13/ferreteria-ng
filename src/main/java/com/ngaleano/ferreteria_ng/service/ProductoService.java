package com.ngaleano.ferreteria_ng.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ngaleano.ferreteria_ng.model.Producto;
import com.ngaleano.ferreteria_ng.repository.ProductoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductoService {
    
    private ProductoRepository productoRepository;

    public Producto obtenerPorId(Long id){
        return productoRepository.findById(id).orElseThrow(
            () -> new RuntimeException("No se encontro el producto con el id: "+ id)
        );
    }

    public Producto obtenerPorCodigo(String codigo){
        Producto producto = productoRepository.findByCodProducto(codigo);

        if (producto == null) {
            throw new RuntimeException("No se encontro el producto con el codigo: " +codigo);
        }
        
        return producto;
    }

    public List<Producto> obtenerPorNombre(String nombre){
        return productoRepository.findByNombreProducto(nombre);
    }

    public List<Producto> listaProductos(){
        return productoRepository.findAll();
    }



}
