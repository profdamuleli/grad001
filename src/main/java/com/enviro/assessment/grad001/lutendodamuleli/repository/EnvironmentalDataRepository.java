package com.enviro.assessment.grad001.lutendodamuleli.repository;

import com.enviro.assessment.grad001.lutendodamuleli.model.EnvironmentalData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface EnvironmentalDataRepository extends JpaRepository<EnvironmentalData, Long> {
    Set<EnvironmentalData> findByFileInformationId(Long fileInformationId);

}
