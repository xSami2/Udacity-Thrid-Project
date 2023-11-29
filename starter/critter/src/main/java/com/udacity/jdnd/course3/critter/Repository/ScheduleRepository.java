package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.Entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    List<ScheduleEntity> findSchedulesByPetsId(Long petId);
    List<ScheduleEntity> findSchedulesByEmployeesId(Long employeeId);
    @Query("SELECT s FROM ScheduleEntity s JOIN s.pets p WHERE p.ownerId = :customerId")
    List<ScheduleEntity> findSchedulesByCustomerId(@Param("customerId") Long customerId);}
