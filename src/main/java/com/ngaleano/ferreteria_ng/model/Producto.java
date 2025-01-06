package com.ngaleano.ferreteria_ng.model;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
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

    @NotBlank(message = "Se necesita una ubicacion del producto")
    private String ubicacion;


}
