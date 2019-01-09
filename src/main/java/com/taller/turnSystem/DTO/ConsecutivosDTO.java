/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.turnSystem.DTO;

/**
 *
 * @author C-Lug
 */
public class ConsecutivosDTO {
    
    private Integer id;
    private String categoria;
    public Integer consecutivo;

    public ConsecutivosDTO() {
    }

    public ConsecutivosDTO( Integer id, String categoria, Integer consecutivo) {
        this.id = id;
        this.categoria = categoria;
        this.consecutivo = consecutivo;
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }
    
    
}
