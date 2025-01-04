package com.ngaleano.ferreteria_ng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngaleano.ferreteria_ng.model.Producto;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNombreProducto(String nombreProducto);

    Producto findByCodProducto(String codProducto);
}
