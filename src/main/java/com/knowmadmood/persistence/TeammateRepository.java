package com.knowmadmood.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowmadmood.models.Teammate;

@Repository
public interface TeammateRepository extends JpaRepository<Teammate, UUID> {
	
	Optional<Teammate> findById (UUID id);
	
	void deleteById(UUID id);

}
