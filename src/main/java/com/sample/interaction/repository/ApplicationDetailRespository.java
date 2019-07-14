package com.sample.interaction.repository;

import com.sample.interaction.entity.ApplicationDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationDetailRespository extends JpaRepository<ApplicationDetailEntity, String>{
	
	Optional<ApplicationDetailEntity> findBySubType(String subType);

}
