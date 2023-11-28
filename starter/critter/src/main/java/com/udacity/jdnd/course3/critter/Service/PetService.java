package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.DTO.Pet_DTO;
import com.udacity.jdnd.course3.critter.Entity.PetEntity;
import com.udacity.jdnd.course3.critter.Mapper.PetMapper;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final PetMapper  petMapper;



    public Pet_DTO savePet(PetEntity petEntity){
         Pet_DTO petDto = petMapper.PetEntityToDTO(petEntity);
         petRepository.save(petEntity);
         return petDto;
    }
}
