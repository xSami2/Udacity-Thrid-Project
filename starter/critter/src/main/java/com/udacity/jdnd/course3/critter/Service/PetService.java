package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.DTO.Pet_DTO;
import com.udacity.jdnd.course3.critter.Entity.PetEntity;
import com.udacity.jdnd.course3.critter.Mapper.PetMapper;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final PetMapper  petMapper;



    public Pet_DTO savePet(Pet_DTO petDTO){
         PetEntity petEntity = petMapper.PetDTOtoEntity(petDTO);
         petRepository.save(petEntity);
         return petMapper.PetEntityToDTO(petEntity);
    }

    public Pet_DTO getPetById(Long petId){
        Optional<PetEntity> petEntity = petRepository.findById(petId);
        return petEntity.map(petMapper::PetEntityToDTO).orElse(null);
    }

    public List<Pet_DTO> getAllPets(){
        Iterable<PetEntity> petEntities = petRepository.findAll();
        return petMapper.PetEntityListToDTO(petEntities);
    }

    public List<Pet_DTO> getPetsByOwnerId(Long ownerId){
        System.out.println(petRepository.findByOwnerId(ownerId));
        Iterable<PetEntity> petEntities = petRepository.findByOwnerId(ownerId);
       return petMapper.PetEntityListToDTO(petEntities);
    }
}
