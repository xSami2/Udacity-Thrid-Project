package com.udacity.jdnd.course3.critter.Controller;

import com.udacity.jdnd.course3.critter.DTO.Customer_DTO;
import com.udacity.jdnd.course3.critter.DTO.Employee_DTO;
import com.udacity.jdnd.course3.critter.DTO.EmployeeRequest_DTO;
import com.udacity.jdnd.course3.critter.Entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.Entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.Mapper.UserMapper;
import com.udacity.jdnd.course3.critter.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/customer")
    public void saveCustomer(@RequestBody Customer_DTO customerDTO){
        CustomerEntity customerEntity =  userMapper.CustomerDTOtoEntity(customerDTO);
        userService.saveCustomer(customerEntity);
    }

    @GetMapping("/customer")
    public List<Customer_DTO> getAllCustomers(){throw new UnsupportedOperationException();}

    @GetMapping("/customer/pet/{petId}")
    public Customer_DTO getOwnerByPet(@PathVariable long petId){
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public Employee_DTO saveEmployee(@RequestBody Employee_DTO employeeDTO) {

        // Convert DTO to Entity
        EmployeeEntity employeeEntity = userMapper.EmployeeDTOtoEntity(employeeDTO);
        System.out.println(employeeDTO.toString());
        System.out.println(employeeEntity.toString());
        // Save the employee entity
        EmployeeEntity savedEmployee = userService.saveCustomer(employeeEntity);

        // Convert the saved entity back to DTO

        // Return the DTO
        return userMapper.EmployeeEntityToDTO(savedEmployee);
    }

    @PostMapping("/employee/{employeeId}")
    public Employee_DTO getEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<Employee_DTO> findEmployeesForService(@RequestBody EmployeeRequest_DTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

}
