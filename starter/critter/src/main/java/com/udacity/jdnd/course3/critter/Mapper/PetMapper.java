package com.udacity.jdnd.course3.critter.Mapper;

import com.udacity.jdnd.course3.critter.DTO.Customer_DTO;
import com.udacity.jdnd.course3.critter.DTO.Pet_DTO;
import com.udacity.jdnd.course3.critter.Entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.Entity.PetEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PetMapper {

    public PetEntity  PetDTOtoEntity(Pet_DTO petDto){
        PetEntity petEntity = new PetEntity();
        BeanUtils.copyProperties(petDto , petEntity);
        return petEntity;
    }

    public Pet_DTO  PetEntityToDTO(PetEntity petEntity){
        Pet_DTO petDto = new Pet_DTO();
        BeanUtils.copyProperties(petEntity , petDto);
        return petDto;
    }
}
