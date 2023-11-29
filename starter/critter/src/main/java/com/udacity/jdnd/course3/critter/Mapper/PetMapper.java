package com.udacity.jdnd.course3.critter.Mapper;

import com.udacity.jdnd.course3.critter.DTO.Customer_DTO;
import com.udacity.jdnd.course3.critter.DTO.Pet_DTO;
import com.udacity.jdnd.course3.critter.Entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.Entity.PetEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<Pet_DTO> PetEntityListToDTO(Iterable<PetEntity> petEntities){
        List<Pet_DTO> petDTOs = new ArrayList<>();
        for (PetEntity petEntity : petEntities){
            Pet_DTO petDto = new Pet_DTO();
            BeanUtils.copyProperties(petEntity ,petDto);
            petDTOs.add(petDto);
        }
        return petDTOs;
    }
}
