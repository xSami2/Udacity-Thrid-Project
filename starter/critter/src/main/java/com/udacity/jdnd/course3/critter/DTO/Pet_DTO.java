package com.udacity.jdnd.course3.critter.DTO;

import com.udacity.jdnd.course3.critter.Enum.PetTypeEnum;
import lombok.Data;

import java.time.LocalDate;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */

@Data
public class Pet_DTO {
    private long id;
    private PetTypeEnum type;
    private String name;
    private LocalDate birthDate;
    private String notes;
    private long ownerId;





}
