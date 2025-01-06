package com.ngaleano.ferreteria_ng.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ngaleano.ferreteria_ng.model.Venta;
import com.ngaleano.ferreteria_ng.service.ProductoService;
import com.ngaleano.ferreteria_ng.service.VentaService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@AllArgsConstructor
public class VentaViewController {
    
    private VentaService ventaService;
    private ProductoService productoService;

    @GetMapping("/ventas")
    public String listarVentas(Model model) {
        model.addAttribute("ventas", ventaService.listarVentas());
        return "listaVentas";
    }

    @GetMapping("/ventas/{ticketCode}")
    public String getMethodName(@PathVariable String ticketCode, Model model) {
        model.addAttribute("ventas", ventaService.obtenerPorTicketCode(ticketCode));
        return "ventaTicket";
    }
    
    @GetMapping("/agregarVenta")
    public String mostrarFormVenta(Model model) {
        model.addAttribute("productos", productoService.listaProductos());
        model.addAttribute("ventas", new Venta());
        return "agregarVentaForm";
    }
    
    @PostMapping("/guardarVenta")
    public String guardarVenta(@RequestParam List<String> codProducto, @RequestParam double precio) {
        ventaService.guardarVenta(codProducto, precio);
        return "redirect:/ventas";
    }
    


}
