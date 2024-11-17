package com.RIS.Mojirecepti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.RIS.Mojirecepti.entity.Recepti;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceptiRepository extends JpaRepository<Recepti, Long> {
    List<Recepti> findByTip(Recepti.Tip tip);
}