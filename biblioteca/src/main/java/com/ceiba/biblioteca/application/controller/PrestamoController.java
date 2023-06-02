package com.ceiba.biblioteca.application.controller;

import com.ceiba.biblioteca.application.dto.PrestamoDTO;
import com.ceiba.biblioteca.application.dto.PrestamoErrorDTO;
import com.ceiba.biblioteca.application.dto.PrestamoSuccesfulDTO;
import com.ceiba.biblioteca.application.dto.PrestamoTwoDTO;
import com.ceiba.biblioteca.domain.model.Prestamo;
import com.ceiba.biblioteca.domain.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prestamo")
public class PrestamoController {
    @Autowired
    protected PrestamoService prestamoService;
    @PostMapping
    public ResponseEntity<PrestamoDTO> crearPrestamo(@RequestBody Prestamo prestamo) {
        try {
            prestamoService.crearPrestamo(prestamo);
            PrestamoSuccesfulDTO prestamoResponse = new PrestamoSuccesfulDTO();
            prestamoResponse.setId(prestamo.getId());
            prestamoResponse.setFechaMaximaDevolucion(prestamoService.formatearFecha(prestamo.getFechaMaximaDevolucion()));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(prestamoResponse);
        } catch (Exception e) {
            PrestamoErrorDTO errorMessage = new PrestamoErrorDTO();
            errorMessage.setMensaje(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorMessage);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoDTO> buscarPorId(@PathVariable(name = "id") Integer id) {
        try {
            Prestamo prestamoEncontrado = prestamoService.buscarPorId(id);
            PrestamoTwoDTO prestamoResponse = new PrestamoTwoDTO();
            prestamoResponse.setId(prestamoEncontrado.getId());
            prestamoResponse.setIsbn(prestamoEncontrado.getIsbn());
            prestamoResponse.setIdentificacionUsuario(prestamoEncontrado.getIdentificacionUsuario());
            prestamoResponse.setFechaMaximaDevolucion(prestamoService.formatearFecha(prestamoEncontrado.getFechaMaximaDevolucion()));
            prestamoResponse.setTipoUsuario(prestamoEncontrado.getTipoUsuario());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(prestamoResponse);
        } catch (Exception e) {
            PrestamoErrorDTO errorMessage = new PrestamoErrorDTO();
            errorMessage.setMensaje(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorMessage);
        }
    }
}