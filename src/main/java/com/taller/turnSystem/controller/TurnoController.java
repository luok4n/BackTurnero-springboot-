/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.turnSystem.controller;

import com.taller.turnSystem.DTO.AsesorDTO;
import com.taller.turnSystem.DTO.CategoriaDTO;
import com.taller.turnSystem.DTO.TurnoDTO;
import com.taller.turnSystem.Service.ConfiguracionService;
import com.taller.turnSystem.Service.GestionTurnosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author C-Lug
 */
@CrossOrigin
@RestController
@RequestMapping("turnero/v1/turno")
public class TurnoController {
    
    private GestionTurnosService turnoService;
    private ConfiguracionService configuracionService;

    public TurnoController(GestionTurnosService turnoService, ConfiguracionService configuracionService) {
        this.turnoService = turnoService;
        this.configuracionService = configuracionService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<TurnoDTO> listaTurnos = configuracionService.listaTurnos();
        if (listaTurnos == null || listaTurnos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaTurnos);
    }
    
    @GetMapping("/pendientes")
    public ResponseEntity<?> getPendientes() {
        List<TurnoDTO> listaTurnos = configuracionService.listaTurnosPen();
        if (listaTurnos == null || listaTurnos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaTurnos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTurno(@PathVariable("id") Long id) {
        TurnoDTO turno = configuracionService.consultarTurno(id);

        if (turno == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(turno);
    }

    @PostMapping("/asesor")
    public ResponseEntity<?> asignarAsesor(@RequestBody AsesorDTO asesor) {
        TurnoDTO turno = turnoService.llamarTurnoAsesor(asesor);
        if (turno == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Hay Turnos Para Atender");
        }
        return ResponseEntity.ok(turno);
    }
    
    @PostMapping("/rasesor")
    public ResponseEntity<?> rellamar(@RequestBody AsesorDTO asesor) {
        TurnoDTO turno = turnoService.rellamar(asesor);
        if (turno == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Hay Turnos Para Atender");
        }
        return ResponseEntity.ok(turno);
    }
    
    @PostMapping("/asesor/{id}/estadoPendiente")
    public ResponseEntity<?> AsignarEstadoPendiente(@PathVariable("id") Integer asesorId) {
        char estado = 'P';
        TurnoDTO turno = turnoService.cambiarEstado(asesorId,estado);
        return ResponseEntity.ok(turno);
    }
    
    @PostMapping("/asesor/{id}/estadoTerminado")
    public ResponseEntity<?> AsignarEstadoTerminado(@PathVariable("id") Integer asesorId) {
        char estado = 'T';
        TurnoDTO turno = turnoService.cambiarEstado(asesorId,estado);
        return ResponseEntity.ok(turno);
    }
    

    @PostMapping()
    public ResponseEntity<?> generateTurn(@RequestBody CategoriaDTO categoria) {
        TurnoDTO turno = turnoService.generarTurno(categoria);
        return ResponseEntity.ok(turno);
    }

}
