package com.RIS.Mojirecepti.repository;

import com.RIS.Mojirecepti.entity.HranilneVrednosti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HranilneVrednostiRepository extends JpaRepository<HranilneVrednosti, Long> {
    List<HranilneVrednosti> findByRecepti_IdRecepti(int idRecepti);
}
