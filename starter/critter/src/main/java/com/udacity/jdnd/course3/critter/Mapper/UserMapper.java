package com.udacity.jdnd.course3.critter.Mapper;

import com.udacity.jdnd.course3.critter.DTO.Customer_DTO;
import com.udacity.jdnd.course3.critter.DTO.Employee_DTO;
import com.udacity.jdnd.course3.critter.Entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.Entity.EmployeeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Employee_DTO> EmployeeEntityListToDTO(List<EmployeeEntity> employeeEntities){
        List<Employee_DTO> employeeDTOs = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeEntities){
            Employee_DTO employeeDTO = new Employee_DTO();
            BeanUtils.copyProperties(employeeEntity ,employeeDTO);
            employeeDTOs.add(employeeDTO);
        }
        return employeeDTOs;
    }

    public List<Customer_DTO> CustomerEntityListToDTO(Iterable<CustomerEntity> customerEntities){
        List<Customer_DTO> customerDTOs = new ArrayList<>();
        for (CustomerEntity customerEntity : customerEntities){
            Customer_DTO customerDTO = new Customer_DTO();
            BeanUtils.copyProperties(customerEntity ,customerDTO);
            customerDTOs.add(customerDTO);
        }
        return customerDTOs;
    }

    public Customer_DTO CustomerEntityToDTO(CustomerEntity customerEntity){
        Customer_DTO customerDto = new Customer_DTO();
        BeanUtils.copyProperties(customerEntity , customerDto);
        return customerDto;
    }
}
