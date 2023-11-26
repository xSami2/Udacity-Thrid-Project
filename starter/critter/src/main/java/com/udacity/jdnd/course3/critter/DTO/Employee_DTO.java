package com.udacity.jdnd.course3.critter.DTO;

import com.udacity.jdnd.course3.critter.Enum.EmployeeSkillEnum;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.Set;

/**
 * Represents the form that employee request and response data takes. Does not map
 * to the database directly.
 */
@Data
public class Employee_DTO {
    private long id;
    private String name;
    private Set<EmployeeSkillEnum> skills;
    private Set<DayOfWeek> daysAvailable;


}
