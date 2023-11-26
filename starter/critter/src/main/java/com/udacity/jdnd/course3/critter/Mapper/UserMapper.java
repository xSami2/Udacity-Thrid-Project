package com.udacity.jdnd.course3.critter.Mapper;

import com.udacity.jdnd.course3.critter.DTO.Customer_DTO;
import com.udacity.jdnd.course3.critter.DTO.Employee_DTO;
import com.udacity.jdnd.course3.critter.Entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.Entity.EmployeeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {


    public CustomerEntity CustomerDTOtoEntity(Customer_DTO customerDto){
        CustomerEntity customerEntity = new CustomerEntity();
        BeanUtils.copyProperties(customerDto , customerEntity);
        return customerEntity;
    }

    public EmployeeEntity EmployeeDTOtoEntity(Employee_DTO employeeDto){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employeeDto , employeeEntity);
        return employeeEntity;
    }

    public Employee_DTO EmployeeEntityToDTO(EmployeeEntity employeeEntity){
        Employee_DTO employeeDto = new Employee_DTO();
        BeanUtils.copyProperties(employeeEntity , employeeDto);
        return employeeDto;
    }

    public Customer_DTO CustomerEntityToDTO(CustomerEntity customerEntity){
        Customer_DTO customerDto = new Customer_DTO();
        BeanUtils.copyProperties(customerEntity , customerDto);
        return customerDto;
    }
}
