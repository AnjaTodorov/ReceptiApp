package com.RIS.Mojirecepti.repository;

import com.RIS.Mojirecepti.entity.NacrtObrokov;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NacrtObrokovRepository extends JpaRepository<NacrtObrokov, Long> {
    List<NacrtObrokov> findByDatum(LocalDate datum);
    boolean existsByDatum(LocalDate datum); // Check if a meal plan exists for the given date

}