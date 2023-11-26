package com.udacity.jdnd.course3.critter.Service;


import com.udacity.jdnd.course3.critter.DTO.Customer_DTO;
import com.udacity.jdnd.course3.critter.Entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.Entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;



    public void saveCustomer(CustomerEntity customerEntity){
        customerRepository.save(customerEntity);
    }

    public EmployeeEntity saveCustomer(EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
    }



}
