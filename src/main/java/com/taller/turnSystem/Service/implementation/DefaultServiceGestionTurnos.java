/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.turnSystem.Service.implementation;

import com.taller.turnSystem.DTO.AsesorDTO;
import com.taller.turnSystem.DTO.CategoriaDTO;
import com.taller.turnSystem.DTO.ConsecutivosDTO;
import com.taller.turnSystem.DTO.TurnoDTO;
import com.taller.turnSystem.Service.ConfiguracionService;
import com.taller.turnSystem.Service.GestionTurnosService;
import com.taller.turnSystem.model.Categoria;
import com.taller.turnSystem.model.Consecutivos;
import com.taller.turnSystem.model.Turno;
import com.taller.turnSystem.repository.ConsecutivoRepository;
import com.taller.turnSystem.repository.TurnoRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author C-Lug
 */
@Service
public class DefaultServiceGestionTurnos implements GestionTurnosService{
    
    private ConsecutivoRepository consecutivoRepository;
    private TurnoRepository turnoRepository;
    private ModelMapper modMapper;
    private ConfiguracionService confService;

    public DefaultServiceGestionTurnos(ConsecutivoRepository consecutivoRepository, TurnoRepository turnoRepository, ModelMapper modMapper, ConfiguracionService confService) {
        this.consecutivoRepository = consecutivoRepository;
        this.turnoRepository = turnoRepository;
        this.modMapper = modMapper;
        this.confService = confService;
    }
    
    
    
    
    private int contExterno=0;
    private int contInterno=0;

    @Override
    public TurnoDTO generarTurno(CategoriaDTO categoria) {
        Optional<Consecutivos> conse = consecutivoRepository.findById(categoria.getId());
        ConsecutivosDTO consecu = modMapper.map(conse.get(),ConsecutivosDTO.class);
        consecu.consecutivo = consecu.consecutivo +1;
        Consecutivos con = modMapper.map(consecu, Consecutivos.class);
        consecutivoRepository.save(con);
        TurnoDTO turno = new TurnoDTO();
        turno.setCategoria(categoria);
        turno.setNro_turno(consecu.consecutivo);
        turno.setEstado('E');
        Date fecha = new Date();
        Turno turnito = modMapper.map(turno, Turno.class);
        turnito.setTiempo_solicitud(fecha);
        turnoRepository.save(turnito);
        return turno;
    }

    @Override
    public TurnoDTO cambiarEstado(Integer asesorId, Character estado) {
        List<Turno> turnosAsesor = turnoRepository.findByAsesorIdAndEstado(asesorId,'A');
        int largo = turnosAsesor.size() - 1;
        TurnoDTO turnoActual = modMapper.map(turnosAsesor.get(largo), TurnoDTO.class);
        turnoActual.setEstado(estado);
        Optional<Turno> turnito = turnoRepository.findById(turnoActual.getId());
        Turno turn = turnito.get();
        if(estado == 'T')
        {Date fechafin = new Date();
        turn.setTiempo_finalizacion(fechafin);
        turn.setEstado(estado);
        }
        turnoRepository.save(turn);
        return turnoActual;
    }

    @Override
    public TurnoDTO llamarTurnoAsesor(AsesorDTO asesor) {
        List<Turno> turnosParaAtender = turnoRepository.findByEstado('E');
        if (turnosParaAtender.isEmpty())
            {return null;
        }
        List<Turno> turnosPrioritarios = turnoRepository.findByCategoriaIdAndEstado(3,'E');
        if (turnosPrioritarios.isEmpty()){ 
            List<Turno> turnosInternos = turnoRepository.findByCategoriaIdAndEstado(2,'E');
            List<Turno> turnosExternos = turnoRepository.findByCategoriaIdAndEstado(1,'E');
            if((!turnosInternos.isEmpty() && contInterno<3) || (!turnosInternos.isEmpty() && turnosExternos.isEmpty())){
                contInterno++;
                TurnoDTO turnoAsesor = modMapper.map(turnosInternos.get(0), TurnoDTO.class);
                turnoAsesor.setAsesor(asesor);
                turnoAsesor.setEstado('A');
                Turno turnito = modMapper.map(turnoAsesor, Turno.class);
                Date fechac = new Date();
                turnito.setTiempo_atencion(fechac);
                turnoRepository.save(turnito);
                return turnoAsesor;}
            else{
                contExterno++;
                TurnoDTO turnoAsesor = modMapper.map(turnosExternos.get(0), TurnoDTO.class);
                turnoAsesor.setAsesor(asesor);
                turnoAsesor.setEstado('A');
                Turno turnito = modMapper.map(turnoAsesor, Turno.class);
                Date fechac = new Date();
                turnito.setTiempo_atencion(fechac);
                turnoRepository.save(turnito);   
                if(contExterno==2){
                    contExterno=0;
                    contInterno=0;    
                }
                return turnoAsesor;      
            }
        }
        TurnoDTO turnoAsesor = modMapper.map(turnosPrioritarios.get(0), TurnoDTO.class);
        turnoAsesor.setAsesor(asesor);
        turnoAsesor.setEstado('A');
        Turno turnito = modMapper.map(turnoAsesor, Turno.class);
        Date fechac = new Date();
        turnito.setTiempo_atencion(fechac);
        turnoRepository.save(turnito);
        return turnoAsesor;
    }    
    
    @Override
    public TurnoDTO rellamar(AsesorDTO asesor){
        List<Turno> turnosAsesor = turnoRepository.findByAsesorIdAndEstado(asesor.getId(),'A');
        int largo = turnosAsesor.size() - 1;
        TurnoDTO turnoActual = modMapper.map(turnosAsesor.get(largo), TurnoDTO.class);
        turnoRepository.save(modMapper.map(turnoActual, Turno.class));
        return turnoActual;
    }
}
