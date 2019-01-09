/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.turnSystem.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 *
 * @author C-Lug
 */
public class TurnoDTO {
    
    
    public CategoriaDTO categoria;
    
    @JsonBackReference
    public long id;
    public int nro_turno;
    
    public AsesorDTO asesor;
  
    public char estado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    

    public TurnoDTO() {
    }

    public TurnoDTO(CategoriaDTO categoria, long id, int nro_turno, AsesorDTO asesor, char estado) {
        this.categoria = categoria;
        this.id = id;
        this.nro_turno = nro_turno;
        this.asesor = asesor;
        this.estado = estado;
    }

    public int getNro_turno() {
        return nro_turno;
    }

    public void setNro_turno(int NroTurno) {
        this.nro_turno = NroTurno;
    }

    public AsesorDTO getAsesor() {
        return asesor;
    }

    public void setAsesor(AsesorDTO asesor) {
        this.asesor = asesor;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    
    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    
}
