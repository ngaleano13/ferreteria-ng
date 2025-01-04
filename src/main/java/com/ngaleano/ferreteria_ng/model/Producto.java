package com.ngaleano.ferreteria_ng.model;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Se necesita un codigo del producto")
    private String codProducto;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombreProducto;

    private int cantProducto;

    @NotBlank(message = "Se necesita una ubicacion del producto")
    private String ubicacion;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Venta> ventas;

}
