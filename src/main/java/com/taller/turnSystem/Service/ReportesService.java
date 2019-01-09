/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.turnSystem.Service;

import com.taller.turnSystem.DTO.TurnoDTO;
import java.util.List;

/**
 *
 * @author C-Lug
 */
public interface ReportesService {
    
    List<TurnoDTO> consultarTurnosPorAsesor(Integer asesorId);
    Long promedioPorCategoria(Integer categoriaId);
    String promedioPorCategoriaAndAsesor(Integer categoriaId,Integer asesorId);
}
