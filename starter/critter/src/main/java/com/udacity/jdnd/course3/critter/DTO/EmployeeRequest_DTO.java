package com.udacity.jdnd.course3.critter.DTO;

import com.udacity.jdnd.course3.critter.Enum.EmployeeSkillEnum;

import java.time.LocalDate;
import java.util.Set;

/**
 * Represents a request to find available employees by skills. Does not map
 * to the database directly.
 */
public class EmployeeRequest_DTO {
    private Set<EmployeeSkillEnum> skills;
    private LocalDate date;

    public Set<EmployeeSkillEnum> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkillEnum> skills) {
        this.skills = skills;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
