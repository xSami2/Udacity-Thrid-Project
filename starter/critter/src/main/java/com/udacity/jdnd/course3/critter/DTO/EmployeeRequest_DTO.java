package com.udacity.jdnd.course3.critter.DTO;

import com.udacity.jdnd.course3.critter.Enum.EmployeeSkillEnum;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

/**
 * Represents a request to find available employees by skills. Does not map
 * to the database directly.
 */

@Data
public class EmployeeRequest_DTO {
    private Set<EmployeeSkillEnum> skills;
    private LocalDate date;
}
