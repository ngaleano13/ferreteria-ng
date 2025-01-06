package com.ngaleano.ferreteria_ng.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Venta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ticketCode" , unique = true, nullable = false)
    private String ticketCode;

    @NotNull(message = "El precio no puede ser nulo")
    private double precio;

    @NotNull(message = "La fecha de venta no puede ser nula")
    private LocalDate fecha;

    @ManyToMany
    @JoinTable(
        name = "venta_producto", 
        joinColumns = @JoinColumn(name = "venta_id"), 
        inverseJoinColumns = @JoinColumn(name = "producto_cod")
    )
    private List<Producto> productos;

}
