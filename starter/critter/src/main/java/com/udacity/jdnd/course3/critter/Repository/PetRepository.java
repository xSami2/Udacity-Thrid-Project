package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.DTO.Pet_DTO;
import com.udacity.jdnd.course3.critter.Entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<PetEntity , Long> {
    List<PetEntity> findByOwnerId(Long ownerId);
}
