package com.udacity.jdnd.course3.critter.Service;


import com.udacity.jdnd.course3.critter.DTO.Customer_DTO;
import com.udacity.jdnd.course3.critter.DTO.Employee_DTO;
import com.udacity.jdnd.course3.critter.Entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.Entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.Entity.PetEntity;
import com.udacity.jdnd.course3.critter.Enum.EmployeeSkillEnum;
import com.udacity.jdnd.course3.critter.Mapper.UserMapper;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final PetRepository petRepository;
    private final UserMapper userMapper;



    public Customer_DTO saveCustomer(Customer_DTO customerDto){
        CustomerEntity customerEntity = userMapper.CustomerDTOtoEntity(customerDto);
        customerRepository.save(customerEntity);
       return userMapper.CustomerEntityToDTO(customerEntity);
    }

    public Employee_DTO saveEmployee(Employee_DTO employeeDTO){
        EmployeeEntity employeeEntity = userMapper.EmployeeDTOtoEntity(employeeDTO);
        employeeRepository.save(employeeEntity);
        return  userMapper.EmployeeEntityToDTO(employeeEntity);

    }

    public EmployeeEntity setEmployeeAvailability(Set<DayOfWeek> daysAvailable , Long employeeId ) {
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(employeeId);
        EmployeeEntity employeeEntity = new EmployeeEntity();
        if (optionalEmployeeEntity.isPresent()) {
            employeeEntity = optionalEmployeeEntity.get();
            employeeEntity.setEmployee_availability(daysAvailable);
            employeeRepository.save(employeeEntity);
            return employeeEntity;
        }
        return employeeEntity;
    }


    public List<Employee_DTO> findEmployeesForService(LocalDate date , Set<EmployeeSkillEnum> skills){
        List<EmployeeEntity> employeeEntities = employeeRepository.findEmployeesBasedOnSkillAndDate(date.getDayOfWeek() , skills);
        return userMapper.EmployeeEntityListToDTO(employeeEntities);
    }
    public Employee_DTO getEmployee(Long employeeId){
        Optional <EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
        return employeeEntity.map(userMapper::EmployeeEntityToDTO).orElse(null);
    }

    public List<Customer_DTO> getAllCustomers(){
        Iterable<CustomerEntity> customerEntities =  customerRepository.findAll();
        return userMapper.CustomerEntityListToDTO(customerEntities);
    }
    public Customer_DTO getOwnerByPetId(Long petId){

        Optional<PetEntity> petEntity = petRepository.findById(petId);
        if (petEntity.isPresent()) {
            long ownerId = petEntity.get().getOwnerId();
            return customerRepository.findById(ownerId)
                    .map(userMapper::CustomerEntityToDTO)
                    .orElse(null);
        }

        return null;
    }


}
