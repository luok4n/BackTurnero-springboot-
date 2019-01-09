/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.turnSystem.Service;

import com.taller.turnSystem.DTO.AsesorDTO;
import com.taller.turnSystem.DTO.CategoriaDTO;
import com.taller.turnSystem.DTO.ConsecutivosDTO;
import com.taller.turnSystem.DTO.TurnoDTO;

/**
 *
 * @author C-Lug
 */
public interface GestionTurnosService {
    
    TurnoDTO generarTurno(CategoriaDTO categoria);
    TurnoDTO cambiarEstado(Integer asesorId, Character estado);
    TurnoDTO llamarTurnoAsesor(AsesorDTO asesor);
    TurnoDTO rellamar(AsesorDTO asesor);
    
    
}
