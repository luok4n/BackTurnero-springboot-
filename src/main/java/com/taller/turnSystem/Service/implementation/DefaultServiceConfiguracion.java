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
import com.taller.turnSystem.model.Asesor;
import com.taller.turnSystem.model.Categoria;
import com.taller.turnSystem.model.Consecutivos;
import com.taller.turnSystem.model.Turno;
import com.taller.turnSystem.repository.AsesorRepository;
import com.taller.turnSystem.repository.CategoriaRepository;
import com.taller.turnSystem.repository.ConsecutivoRepository;
import com.taller.turnSystem.repository.TurnoRepository;
import java.util.ArrayList;
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
public class DefaultServiceConfiguracion implements ConfiguracionService {

    @Autowired
    private AsesorRepository asesorRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ConsecutivoRepository consecutivoRepository;
    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private ModelMapper modMapper;

    @Override
    public List<AsesorDTO> listaAsesores() {
        List<AsesorDTO> respuesta = new ArrayList<AsesorDTO>();;
        List<Asesor> asesores = asesorRepository.findAll();
        if (asesores != null && !asesores.isEmpty()){
            for(Asesor asesor : asesores){
                respuesta.add(modMapper.map(asesor,AsesorDTO.class));
            }
            return respuesta;
        }
        return null;
    }
       
    @Override
    public AsesorDTO consultarAsesor(Integer id) {
        Optional<Asesor> asesorOptional = asesorRepository.findById(id);
        if(asesorOptional.isPresent()){
            return modMapper.map(asesorOptional.get(),AsesorDTO.class);
        }
        return null;
    }
    

    @Override
    public AsesorDTO guardarAsesor(AsesorDTO asesor) {
        Asesor representative = asesorRepository.save(modMapper.map(asesor,Asesor.class));
        return modMapper.map(representative,AsesorDTO.class);
    }
    

    @Override
    public AsesorDTO eliminarAsesor(Integer id) {
        AsesorDTO asesor = consultarAsesor(id);
        if(asesor != null){
            asesorRepository.deleteById(id);
            return asesor;
        }
        return null;
    }

    @Override
    
    public List<CategoriaDTO> listaCategorias() {
        List<CategoriaDTO> respuesta = null;
        List<Categoria> categorias = categoriaRepository.findAll();
        if (categorias != null && !categorias.isEmpty()){
            respuesta = new ArrayList<>();
            for(Categoria categoria : categorias){
                respuesta.add(modMapper.map(categoria,CategoriaDTO.class));
            }
        }
        return respuesta;
    }
    
    @Override
    public CategoriaDTO consultarCategoria(Integer id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if(categoriaOptional.isPresent()){
            return modMapper.map(categoriaOptional.get(),CategoriaDTO.class);
        }
        return null;
    }
    

    @Override
    public CategoriaDTO guardarCategoria(CategoriaDTO categoria) {
        Categoria type = categoriaRepository.save(modMapper.map(categoria,Categoria.class));
        return modMapper.map(type,CategoriaDTO.class);
    }
    

    @Override
    public CategoriaDTO eliminarCategoria(Integer id) {
        CategoriaDTO categoria = consultarCategoria(id);
        if(categoria != null){
            categoriaRepository.deleteById(id);
            return categoria;
        }
        return null;
    }
   
    @Override
    public List<ConsecutivosDTO> listaConsecutivos() {
         modMapper = new ModelMapper();
        List<ConsecutivosDTO> respuesta = null;
        List<Consecutivos> consecutivos = consecutivoRepository.findAll();
        if (consecutivos != null && !consecutivos.isEmpty()){
            respuesta = new ArrayList<>();
            for(Consecutivos consecutivo : consecutivos){
                respuesta.add(modMapper.map(consecutivo,ConsecutivosDTO.class));
            }
        }
        return respuesta;
    }
    
    @Override
    public ConsecutivosDTO consultarConsecutivo(Integer id) {
         modMapper = new ModelMapper();
        Optional<Consecutivos> consecutivoOptional = consecutivoRepository.findById(id);
        if(consecutivoOptional.isPresent()){
            return modMapper.map(consecutivoOptional.get(),ConsecutivosDTO.class);
        }
        return null;
    }
    

    @Override
    public ConsecutivosDTO guardarConsecutivo(ConsecutivosDTO consecutivo) {
         modMapper = new ModelMapper();
        Consecutivos next = consecutivoRepository.save(modMapper.map(consecutivo,Consecutivos.class));
        return modMapper.map(next,ConsecutivosDTO.class);
    }
    

    @Override
    public ConsecutivosDTO eliminarConsecutivo(Integer id) {
         modMapper = new ModelMapper();
        ConsecutivosDTO consecutivo = consultarConsecutivo(id);
        if(consecutivo != null){
            consecutivoRepository.deleteById(id);
            return consecutivo;
        }
        return null;
    }
    
    @Override
    public List<TurnoDTO> listaTurnos(){
        List<TurnoDTO> respuesta = new ArrayList<TurnoDTO>();
        List<Turno> turno = turnoRepository.findByEstado('A');
        if(turno != null && !turno.isEmpty()){
            for(Turno turnito : turno){
                respuesta.add(modMapper.map(turnito, TurnoDTO.class));
            }
            return respuesta;
        }
        return null;
    }
    
    public List<TurnoDTO> listaTurnosPen(){
        List<TurnoDTO> respuesta = new ArrayList<TurnoDTO>();
        List<Turno> turno = turnoRepository.findByEstado('E');
        if(turno != null && !turno.isEmpty()){
            for(Turno turnito : turno){
                respuesta.add(modMapper.map(turnito, TurnoDTO.class));
            }
            return respuesta;
        }
        return null;
    }
    
    @Override
    public TurnoDTO consultarTurno(Long id){
        Optional<Turno> turnOptional = turnoRepository.findById(id);
        if(turnOptional.isPresent()){
            return modMapper.map(turnOptional.get(), TurnoDTO.class);
        }
        return null;
    }

    @Override
    public List<TurnoDTO> listaTurnosById() {
        List<TurnoDTO> respuesta = new ArrayList<TurnoDTO>();
        List<Turno> turno = turnoRepository.findByEstado('E');
        if(turno != null && !turno.isEmpty()){
            for(Turno turnito : turno){
                respuesta.add(modMapper.map(turnito, TurnoDTO.class));
            }
            return respuesta;
        }
        return null;
    }
      
}
