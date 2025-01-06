package com.ngaleano.ferreteria_ng.service;

import java.util.List;
import java.util.Optional;

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

    public List<Producto> obtenerPorCodigos(List<String> codigos) {
        return productoRepository.findByCodProductoIn(codigos);
    }

    public List<Producto> obtenerPorNombre(String nombre){
        return productoRepository.findByNombreProducto(nombre);
    }

    public List<Producto> listaProductos(){
        return productoRepository.findAll();
    }

    public Producto guardarProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public void actualizarProducto (Long idProducto, Producto productoActualizado){

        Optional<Producto> producto = productoRepository.findById(idProducto);
        
        Producto productoExistente = armarProducto(productoActualizado, producto);
        productoRepository.save(productoExistente);
    }

    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }


    private Producto armarProducto(Producto productoActualizado, Optional<Producto> productoOptional){
        Producto.ProductoBuilder productoBuilder = Producto.builder();

        productoOptional.ifPresent(productoExistente -> {
            productoBuilder
                .id(productoExistente.getId())
                .codProducto(productoActualizado.getCodProducto())
                .nombreProducto(productoActualizado.getNombreProducto())
                .ubicacion(productoActualizado.getUbicacion());
        });

        return productoBuilder.build();

    }


}
