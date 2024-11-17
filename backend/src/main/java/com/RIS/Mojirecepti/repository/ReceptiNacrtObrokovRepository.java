package com.RIS.Mojirecepti.repository;

import com.RIS.Mojirecepti.entity.ReceptiNacrtObrokov;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptiNacrtObrokovRepository extends JpaRepository<ReceptiNacrtObrokov, Long> {}