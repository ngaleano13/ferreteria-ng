package com.ngaleano.ferreteria_ng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ngaleano.ferreteria_ng.model.Producto;
import com.ngaleano.ferreteria_ng.service.ProductoService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@AllArgsConstructor
public class ProductoViewController {
    
    private ProductoService productoService;

    @GetMapping("/productos")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listaProductos());
        return "listaProductos";
    }

    @GetMapping("/guardarProducto")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/productos/{codigo}")
    public String buscarPorCodigo(@PathVariable String codigo, Model model) {
        model.addAttribute("producto", productoService.obtenerPorCodigo(codigo));
        return "detalleProducto";
    }
    
    @GetMapping("/actualizarProducto/{id}")
    public String mostrarFormActualizarProducto(@PathVariable Long id, Model model) {
        model.addAttribute("producto", productoService.obtenerPorId(id));
        return "actualizarProductoForm";
    }

    @PostMapping("/actualizarProducto/{idProducto}")
    public String postMethodName(@PathVariable Long idProducto, @ModelAttribute Producto productoActualizado) {
        productoService.actualizarProducto(idProducto, productoActualizado);
        return "redirect:/productos";
    }
    
    @GetMapping("/eliminarProducto/{id}")
    public String elimnarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }
    
    
}
