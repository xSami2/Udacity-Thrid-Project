package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.Enum.EmployeeSkillEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "employees")
@DynamicUpdate
@Data
public class EmployeeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private long id;

    @Column(name="employee_name")
    private String name;

    @ElementCollection(targetClass = EmployeeSkillEnum.class)  // Chose the Enum Class
    @Enumerated(EnumType.STRING) // Chose to Be String or Numbers
    @CollectionTable(name = "employee_skills" , joinColumns = @JoinColumn(name ="employee_id")) // Chose table name + Link
    @Column(name = "employee_skill")
    private Set<EmployeeSkillEnum> skills;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ElementCollection(targetClass = DayOfWeek.class)  // Chose the Enum Class
    @Enumerated(EnumType.STRING) // Chose to Be String or Numbers
    @CollectionTable(name = "employee_availability" , joinColumns = @JoinColumn(name ="employee_id")) // Chose table name + Link
    @Column(name = "employee_availability")
    private Set<DayOfWeek> employee_availability;



    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }


}
