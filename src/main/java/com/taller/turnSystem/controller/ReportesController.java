/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.turnSystem.controller;

import com.taller.turnSystem.DTO.TurnoDTO;
import com.taller.turnSystem.Service.ReportesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author C-Lug
 */
@CrossOrigin
@RestController
@RequestMapping("turnero/v1/reportes")
public class ReportesController {
    
    
    private ReportesService reporteService;

    public ReportesController(ReportesService reporteService) {
        this.reporteService = reporteService;
    }
    

    @GetMapping("/categoria/{id}")
    public ResponseEntity<?> promedioCategoria(@PathVariable("id") Integer categoriaId) {
        Long promedio = reporteService.promedioPorCategoria(categoriaId);

        if (promedio == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(promedio);
    }
    
    @GetMapping("/asesor/{id}")
    public ResponseEntity<?> turnosAsesor(@PathVariable("id") Integer asesorId) {
        List<TurnoDTO> promedio = reporteService.consultarTurnosPorAsesor(asesorId);
        if (promedio == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(promedio);
    }
    
}
