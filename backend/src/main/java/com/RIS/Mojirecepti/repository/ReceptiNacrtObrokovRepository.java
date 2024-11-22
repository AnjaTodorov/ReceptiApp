package com.RIS.Mojirecepti.repository;

import com.RIS.Mojirecepti.entity.NacrtObrokov;
import com.RIS.Mojirecepti.entity.ReceptiNacrtObrokov;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceptiNacrtObrokovRepository extends JpaRepository<ReceptiNacrtObrokov, Long> {
    // Custom method to fetch recipes linked to a specific meal plan
    List<ReceptiNacrtObrokov> findByNacrtObrokov(NacrtObrokov nacrtObrokov);
}