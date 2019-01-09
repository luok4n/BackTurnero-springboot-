/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.turnSystem.Service.implementation;

import com.taller.turnSystem.DTO.TurnoDTO;
import com.taller.turnSystem.Service.ReportesService;
import com.taller.turnSystem.model.Turno;
import com.taller.turnSystem.repository.TurnoRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author C-Lug
 */

@Service
public class DefaultServiceReportes implements ReportesService{

    private TurnoRepository turnoRepository;
    private ModelMapper modMapper;

    public DefaultServiceReportes(TurnoRepository turnoRepository, ModelMapper modMapper) {
        this.turnoRepository = turnoRepository;
        this.modMapper = modMapper;
    }
    
    
    @Override
    public List<TurnoDTO> consultarTurnosPorAsesor(Integer asesorId) {
        List<TurnoDTO> respuesta = new ArrayList<TurnoDTO>();
        List<Turno> turnosPorAsesor = turnoRepository.findByAsesorId(asesorId);
        if(turnosPorAsesor != null && !turnosPorAsesor.isEmpty()){
            for(Turno turnito : turnosPorAsesor){
                respuesta.add(modMapper.map(turnito, TurnoDTO.class));
            }
            return respuesta;
        }
        return null;
    }

    @Override
    public Long promedioPorCategoria(Integer categoriaId) {
        List<Turno> turnosCategoria = turnoRepository.findByCategoriaIdAndEstado(categoriaId,'T');
        Long minutes = 0L;
        if(turnosCategoria != null && !turnosCategoria.isEmpty()){
            for(Turno turnito : turnosCategoria){ 
                minutes = minutes + (turnito.getTiempo_finalizacion().getTime()- turnito.getTiempo_atencion().getTime());
            }
            minutes = minutes/1000;
            minutes = minutes/60;
            return minutes / turnosCategoria.size();
            
        }
        return null;
    }

    @Override
    public String promedioPorCategoriaAndAsesor(Integer categoriaId, Integer asesorId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
