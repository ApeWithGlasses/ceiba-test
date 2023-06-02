package com.ceiba.biblioteca.domain.repository;

import com.ceiba.biblioteca.domain.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
    boolean existsByIdentificacionUsuario(String identificacionUsuario);
}
