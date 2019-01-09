/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.turnSystem.repository;

import com.taller.turnSystem.model.Turno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author C-Lug
 */
@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long>{
    List<Turno> findByEstado(char estado);
    List<Turno> findByCategoriaIdAndEstado(int categoriaID, char estado);
    List<Turno> findByAsesorIdAndEstado(int asesorID, char estado);
    List<Turno> findByAsesorId(Integer asesorId);
    List<Turno> findByCategoriaId(Integer categoriaId);
}
