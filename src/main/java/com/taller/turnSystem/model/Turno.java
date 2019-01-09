/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.turnSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

/**
 *
 * @author C-Lug
 */
@Entity
@Table (name = "turnos")
public class Turno implements Serializable{
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name ="nro_turno")
    private Integer nro_turno;
    
    @NotNull
    @Column(name = "estado_id")
    private Character estado;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tiempo_solicitud", nullable = false, updatable = false)
    @CreatedDate
    private Date tiempo_solicitud;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tiempo_atencion")
    private Date tiempo_atencion;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tiempo_finalizacion")
    private Date tiempo_finalizacion;
    
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="categoria_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Categoria categoria;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="asesor_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Asesor asesor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNro_turno() {
        return nro_turno;
    }

    public void setNro_turno(Integer nro_turno) {
        this.nro_turno = nro_turno;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Date getTiempo_solicitud() {
        return tiempo_solicitud;
    }

    public void setTiempo_solicitud(Date tiempo_solicitud) {
        this.tiempo_solicitud = tiempo_solicitud;
    }

    public Date getTiempo_atencion() {
        return tiempo_atencion;
    }

    public void setTiempo_atencion(Date tiempo_atencion) {
        this.tiempo_atencion = tiempo_atencion;
    }

    public Date getTiempo_finalizacion() {
        return tiempo_finalizacion;
    }

    public void setTiempo_finalizacion(Date tiempo_finalizacion) {
        this.tiempo_finalizacion = tiempo_finalizacion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Asesor getAsesor() {
        return asesor;
    }

    public void setAsesor(Asesor asesor) {
        this.asesor = asesor;
    }
    
    
    
    
    
}
