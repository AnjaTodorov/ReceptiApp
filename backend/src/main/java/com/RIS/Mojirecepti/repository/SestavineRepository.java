package com.RIS.Mojirecepti.repository;

import com.RIS.Mojirecepti.entity.Recepti;
import com.RIS.Mojirecepti.entity.Sestavine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SestavineRepository extends JpaRepository<Sestavine, Integer> {
    // Custom query to find all ingredients for a specific recipe by Recipe ID
    List<Sestavine> findByRecepti_IdRecepti(int idRecepti);
    List<Sestavine> findByRecepti(Recepti recepti);

}
