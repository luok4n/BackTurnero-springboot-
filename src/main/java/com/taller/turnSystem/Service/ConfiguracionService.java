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
import java.util.List;

/**
 *
 * @author C-Lug
 */
public interface ConfiguracionService {
    
    
    List<AsesorDTO> listaAsesores();
    
    AsesorDTO consultarAsesor(Integer id);
    
    AsesorDTO guardarAsesor(AsesorDTO asesor);
    
    AsesorDTO eliminarAsesor(Integer id);
    
    
    List<CategoriaDTO> listaCategorias();
    
    CategoriaDTO consultarCategoria(Integer id);
    
    CategoriaDTO guardarCategoria(CategoriaDTO categoria);
    
    CategoriaDTO eliminarCategoria(Integer id);
    
    
    List<ConsecutivosDTO> listaConsecutivos();
    
    ConsecutivosDTO consultarConsecutivo(Integer id);
    
    ConsecutivosDTO guardarConsecutivo(ConsecutivosDTO categoria);
    
    ConsecutivosDTO eliminarConsecutivo(Integer id);
    
    
    List<TurnoDTO> listaTurnos();
    
    TurnoDTO consultarTurno(Long id);
    
    List<TurnoDTO> listaTurnosById();

    List<TurnoDTO> listaTurnosPen();
    
    
}
