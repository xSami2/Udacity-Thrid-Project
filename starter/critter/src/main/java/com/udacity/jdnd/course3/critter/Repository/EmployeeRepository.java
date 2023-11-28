package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.Enum.EmployeeSkillEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity , Long> {

    @Query("SELECT DISTINCT employee " +
            "FROM EmployeeEntity employee " +
            "JOIN employee.employee_availability availableDay " +
            "JOIN employee.skills skill " +
            "WHERE availableDay = :requiredDay " +
            "AND skill IN :requiredSkills")
    List<EmployeeEntity> findEmployeesBasedOnSkillAndDate(
            @Param("requiredDay") DayOfWeek requiredDay,
            @Param("requiredSkills") Set<EmployeeSkillEnum> requiredSkills);


}
