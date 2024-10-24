package com.RIS.Mojirecepti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.RIS.Mojirecepti.entity.Recepti;

public interface ReceptiRepository extends JpaRepository<Recepti, Long> {
    // Additional custom query methods (if needed) can be added here
}