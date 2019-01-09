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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author C-Lug
 */
public class TurnoControllerTest {
    
    private ConfiguracionService configuracionService;
    private GestionTurnosService gestionService;
    private TurnoController turnoController;
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       configuracionService = Mockito.mock(ConfiguracionService.class);
       gestionService = Mockito.mock(GestionTurnosService.class);
       turnoController = new TurnoController(gestionService, configuracionService);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAll method, of class TurnoController.
     */
    @Test
    public void testGetAll() {
        when(configuracionService.listaTurnos()).thenReturn(null);
        ResponseEntity<?> result = turnoController.getAll();
        Assert.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
    
  
    @Test
    public void testGetAll_Empty() {
        when(configuracionService.listaTurnos()).thenReturn(new ArrayList<>());
        
        ResponseEntity<?> result = turnoController.getAll();
        Assert.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
    
    @Test
    public void testGetAll_HasContent() {
        List<TurnoDTO> lista = new ArrayList<>();
        lista.add( new TurnoDTO(new CategoriaDTO(1,"Externo"),1,1,new AsesorDTO(1,"Carlos"),'A'));
        lista.add( new TurnoDTO(new CategoriaDTO(1,"Externo"),1,1,new AsesorDTO(1,"Carlos"),'A'));
        
        when(configuracionService.listaTurnos()).thenReturn(lista);
               
        
        ResponseEntity<?> result = turnoController.getAll();
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assert.assertNotNull(result.getBody());
    }
    
    @Test
    public void testGetTurno_null() {
        when(configuracionService.consultarTurno(-1L)).thenReturn(null);
        ResponseEntity<?> result = turnoController.getTurno(-1L);
        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
    
    @Test
    public void testGetTurno_Exist() {
        when(configuracionService.consultarTurno(1L)).thenReturn(new TurnoDTO(new CategoriaDTO(1,"Externo"),1,1,new AsesorDTO(1,"Carlos"),'A'));
        ResponseEntity<?> result = turnoController.getTurno(1L);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assert.assertNotNull(result.getBody());
    }
    
    @Test
    public void testAsignarsesor_null(){
        when(gestionService.llamarTurnoAsesor(new AsesorDTO(1,"Carlos"))).thenReturn(null);
        ResponseEntity<?> result = turnoController.asignarAsesor(new AsesorDTO(1,"Carlos"));
        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
    
    @Test
    public void testAsignarsesor_turno(){
        AsesorDTO asesor = new AsesorDTO(1,"Carlos");
        when(gestionService.llamarTurnoAsesor(asesor)).thenReturn(new TurnoDTO(new CategoriaDTO(1,"Externo"),1,1,asesor,'A'));
        ResponseEntity<?> result = turnoController.asignarAsesor(asesor);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        TurnoDTO dto = (TurnoDTO) result.getBody();
        assertNotNull(dto);
        Assert.assertEquals(asesor, dto.getAsesor());
    }
    
    @Test
    public void testReLlamar_null(){
        when(gestionService.rellamar(new AsesorDTO(1,"Carlos"))).thenReturn(null);
        ResponseEntity<?> result = turnoController.rellamar(new AsesorDTO(1,"Carlos"));
        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
    
    @Test
    public void testReLLamar_turno(){
        AsesorDTO asesor = new AsesorDTO(1,"Carlos");
        when(gestionService.rellamar(asesor)).thenReturn(new TurnoDTO(new CategoriaDTO(1,"Externo"),1,1,asesor,'A'));
        ResponseEntity<?> result = turnoController.rellamar(asesor);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        TurnoDTO dto = (TurnoDTO) result.getBody();
        assertNotNull(dto);
        Assert.assertEquals(asesor, dto.getAsesor());
    }
    
    @Test
    public void testEstadoPendiente(){
        char estado = 'P';
        int asesorId = 1;
        when(gestionService.cambiarEstado(asesorId, estado)).thenReturn(new TurnoDTO(new CategoriaDTO(1,"Externo"),1,1,new AsesorDTO(asesorId,"Carlos"),'P'));
        ResponseEntity<?> result = turnoController.AsignarEstadoPendiente(asesorId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        TurnoDTO dto = (TurnoDTO) result.getBody();
        assertNotNull(dto);
        assertEquals(estado, dto.getEstado());
        
    }
    
    @Test
    public void testEstadoTerminado(){
        char estado = 'T';
        int asesorId = 1;
        when(gestionService.cambiarEstado(asesorId, estado)).thenReturn(new TurnoDTO(new CategoriaDTO(1,"Externo"),1,1,new AsesorDTO(asesorId,"Carlos"),'T'));
        ResponseEntity<?> result = turnoController.AsignarEstadoTerminado(asesorId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        TurnoDTO dto = (TurnoDTO) result.getBody();
        assertNotNull(dto);
        assertEquals(estado, dto.getEstado());
        
    }
    
    @Test
    public void testGenerateTurno(){
        CategoriaDTO categoria = new CategoriaDTO(1,"Externo");
        when(gestionService.generarTurno(categoria)).thenReturn(new TurnoDTO(categoria,1,1,null,'E'));
        ResponseEntity<?> result = turnoController.generateTurn(categoria);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        TurnoDTO dto = (TurnoDTO) result.getBody();
        assertNotNull(dto);
        assertEquals(categoria.getId(), dto.getCategoria().getId());
        assertEquals('E', dto.getEstado());
    }
    

}