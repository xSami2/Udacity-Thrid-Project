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
    public Pet_DTO savePet(@RequestBody Pet_DTO petDTO) {
       return petService.savePet(petDTO);
    }

    @GetMapping("/{petId}")
    public Pet_DTO getPet(@PathVariable long petId) {
       return petService.getPetById(petId);
    }

    @GetMapping
    public List<Pet_DTO> getPets(){
        return petService.getAllPets();
    }

    @GetMapping("/owner/{ownerId}")
    public List<Pet_DTO> getPetsByOwner(@PathVariable long ownerId) {
       return petService.getPetsByOwnerId(ownerId);
    }
}
