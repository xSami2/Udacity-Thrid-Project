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
    public Customer_DTO saveCustomer(@RequestBody Customer_DTO customerDTO){
      return   userService.saveCustomer(customerDTO);
    }

    @GetMapping("/customer")
    public List<Customer_DTO> getAllCustomers(){
        return userService.getAllCustomers();
    }

    @GetMapping("/customer/pet/{petId}")
    public Customer_DTO getOwnerByPet(@PathVariable long petId){
        return userService.getOwnerByPetId(petId);
    }

    @PostMapping("/employee")
    public Employee_DTO saveEmployee(@RequestBody Employee_DTO employeeDTO) {
        return userService.saveEmployee(employeeDTO);
    }

    @PostMapping("/employee/{employeeId}")
    public Employee_DTO getEmployee(@PathVariable long employeeId) {
       return userService.getEmployee(employeeId);
    }

    @PutMapping("/employee/{employeeId}")
    public Employee_DTO setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        EmployeeEntity employeeEntity = userService.setEmployeeAvailability(daysAvailable , employeeId);
        return userMapper.EmployeeEntityToDTO(employeeEntity);
    }

    @GetMapping("/employee/availability")
    public List<Employee_DTO> findEmployeesForService(@RequestBody EmployeeRequest_DTO employeeDTO) {
        return userService.findEmployeesForService(employeeDTO.getDate() , employeeDTO.getSkills());
    }

}
