/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.turnSystem.repository;

import com.taller.turnSystem.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author C-Lug
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    
}
