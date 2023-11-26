package com.udacity.jdnd.course3.critter.Controller;

import com.udacity.jdnd.course3.critter.DTO.Pet_DTO;
import com.udacity.jdnd.course3.critter.Entity.PetEntity;
import com.udacity.jdnd.course3.critter.Mapper.PetMapper;
import com.udacity.jdnd.course3.critter.Service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/pet")
public class PetController {

    private final PetMapper petMapper;
    private final PetService petService;



    @PostMapping
    public void savePet(@RequestBody Pet_DTO petDTO) {
        PetEntity petEntity = petMapper.PetDTOtoEntity(petDTO);
        petService.savePet(petEntity);
    }

    @GetMapping("/{petId}")
    public Pet_DTO getPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<Pet_DTO> getPets(){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/owner/{ownerId}")
    public List<Pet_DTO> getPetsByOwner(@PathVariable long ownerId) {
        throw new UnsupportedOperationException();
    }
}
