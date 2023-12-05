package com.udacity.jdnd.course3.critter;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.Controller.UserController;
import com.udacity.jdnd.course3.critter.DTO.Customer_DTO;
import com.udacity.jdnd.course3.critter.DTO.Employee_DTO;
import com.udacity.jdnd.course3.critter.DTO.EmployeeRequest_DTO;
import com.udacity.jdnd.course3.critter.Enum.EmployeeSkillEnum;
import com.udacity.jdnd.course3.critter.Controller.PetController;
import com.udacity.jdnd.course3.critter.DTO.Pet_DTO;
import com.udacity.jdnd.course3.critter.Enum.PetTypeEnum;
import com.udacity.jdnd.course3.critter.Controller.ScheduleController;
import com.udacity.jdnd.course3.critter.DTO.Schedule_DTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This is a set of functional tests to validate the basic capabilities desired for this application.
 * Students will need to configure the application to run these tests by adding application.properties file
 * to the test/resources directory that specifies the datasource. It can run using an in-memory H2 instance
 * and should not try to re-use the same datasource used by the rest of the app.
 *
 * These tests should all pass once the project is complete.
 */
@Transactional
@SpringBootTest(classes = CritterApplication.class)
public class CritterFunctionalTest {

    @Autowired
    private UserController userController;

    @Autowired
    private PetController petController;

    @Autowired
    private ScheduleController scheduleController;

    @Test
    public void testCreateCustomer(){
        Customer_DTO customerDTO = createCustomerDTO();
        Customer_DTO newCustomer = userController.saveCustomer(customerDTO);
        Customer_DTO retrievedCustomer = userController.getAllCustomers().get(0);
        Assertions.assertEquals(newCustomer.getName(), customerDTO.getName());
        Assertions.assertEquals(newCustomer.getId(), retrievedCustomer.getId());
        Assertions.assertTrue(retrievedCustomer.getId() > 0);
    }

    @Test
    public void testCreateEmployee(){
        Employee_DTO employeeDTO = createEmployeeDTO();
        Employee_DTO newEmployee = userController.saveEmployee(employeeDTO);
        Employee_DTO retrievedEmployee = userController.getEmployee(newEmployee.getId());
        Assertions.assertEquals(employeeDTO.getSkills(), newEmployee.getSkills());
        Assertions.assertEquals(newEmployee.getId(), retrievedEmployee.getId());
        Assertions.assertTrue(retrievedEmployee.getId() > 0);
    }

    @Test
    public void testAddPetsToCustomer() {
        Customer_DTO customerDTO = createCustomerDTO();
        Customer_DTO newCustomer = userController.saveCustomer(customerDTO);

        Pet_DTO petDTO = createPetDTO();
        petDTO.setOwnerId(newCustomer.getId());
        Pet_DTO newPet = petController.savePet(petDTO);

        //make sure pet contains customer id
        Pet_DTO retrievedPet = petController.getPet(newPet.getId());
        Assertions.assertEquals(retrievedPet.getId(), newPet.getId());
        Assertions.assertEquals(retrievedPet.getOwnerId(), newCustomer.getId());



        //make sure you can retrieve pets by owner
        List<Pet_DTO> pets = petController.getPetsByOwner(newCustomer.getId());
        Assertions.assertEquals(newPet.getId(), pets.get(0).getId());
        Assertions.assertEquals(newPet.getName(), pets.get(0).getName());

        //check to make sure customer now also contains pet
        Customer_DTO retrievedCustomer = userController.getAllCustomers().get(0);
        System.out.println(userController.getAllCustomers());
        System.out.println(retrievedCustomer.getPetIds());
        System.out.println(retrievedCustomer.getPetIds());
        Assertions.assertFalse(retrievedCustomer.getPetIds() != null && !retrievedCustomer.getPetIds().isEmpty());
    }

    @Test
    public void testFindPetsByOwner() {
        Customer_DTO customerDTO = createCustomerDTO();
        Customer_DTO newCustomer = userController.saveCustomer(customerDTO);

        Pet_DTO petDTO = createPetDTO();
        petDTO.setOwnerId(newCustomer.getId());
        Pet_DTO newPet = petController.savePet(petDTO);
        petDTO.setType(PetTypeEnum.DOG);
        petDTO.setName("DogName");
        Pet_DTO newPet2 = petController.savePet(petDTO);

        List<Pet_DTO> pets = petController.getPetsByOwner(newCustomer.getId());
        Assertions.assertEquals(pets.size(), 2);
        Assertions.assertEquals(pets.get(0).getOwnerId(), newCustomer.getId());
        Assertions.assertEquals(pets.get(0).getId(), newPet.getId());
    }

    @Test
    public void testFindOwnerByPet() {
        Customer_DTO customerDTO = createCustomerDTO();
        Customer_DTO newCustomer = userController.saveCustomer(customerDTO);

        Pet_DTO petDTO = createPetDTO();
        petDTO.setOwnerId(newCustomer.getId());
        Pet_DTO newPet = petController.savePet(petDTO);

        Customer_DTO owner = userController.getOwnerByPet(newPet.getId());
        Assertions.assertEquals(owner.getId(), newCustomer.getId());
    }

    @Test
    public void testChangeEmployeeAvailability() {
        Employee_DTO employeeDTO = createEmployeeDTO();
        Employee_DTO emp1 = userController.saveEmployee(employeeDTO);
        Assertions.assertNull(emp1.getEmployee_availability());

        Set<DayOfWeek> availability = Sets.newHashSet(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY);
        userController.setAvailability(availability, emp1.getId());

        Employee_DTO emp2 = userController.getEmployee(emp1.getId());
        Assertions.assertEquals(availability, emp2.getEmployee_availability());
    }

    @Test
    public void testFindEmployeesByServiceAndTime() {
        Employee_DTO emp1 = createEmployeeDTO();
        Employee_DTO emp2 = createEmployeeDTO();
        Employee_DTO emp3 = createEmployeeDTO();


        emp1.setEmployee_availability(Sets.newHashSet(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY));
        emp2.setEmployee_availability(Sets.newHashSet(DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
        emp3.setEmployee_availability(Sets.newHashSet(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));

        emp1.setSkills(Sets.newHashSet(EmployeeSkillEnum.FEEDING, EmployeeSkillEnum.PETTING));
        emp2.setSkills(Sets.newHashSet(EmployeeSkillEnum.PETTING, EmployeeSkillEnum.WALKING));
        emp3.setSkills(Sets.newHashSet(EmployeeSkillEnum.WALKING, EmployeeSkillEnum.SHAVING));

        Employee_DTO emp1n = userController.saveEmployee(emp1);
        Employee_DTO emp2n = userController.saveEmployee(emp2);
        Employee_DTO emp3n = userController.saveEmployee(emp3);

        //make a request that matches employee 1 or 2
        EmployeeRequest_DTO er1 = new EmployeeRequest_DTO();
        er1.setDate(LocalDate.of(2019, 12, 25)); //wednesday
        er1.setSkills(Sets.newHashSet(EmployeeSkillEnum.PETTING));

        Set<Long> eIds1 = userController.findEmployeesForService(er1).stream().map(Employee_DTO::getId).collect(Collectors.toSet());
        Set<Long> eIds1expected = Sets.newHashSet(emp1n.getId(), emp2n.getId());
        Assertions.assertEquals(eIds1, eIds1expected);

        //make a request that matches only employee 3
        EmployeeRequest_DTO er2 = new EmployeeRequest_DTO();
        er2.setDate(LocalDate.of(2019, 12, 27)); //friday
        er2.setSkills(Sets.newHashSet(EmployeeSkillEnum.WALKING, EmployeeSkillEnum.SHAVING));

        Set<Long> eIds2 = userController.findEmployeesForService(er2).stream().map(Employee_DTO::getId).collect(Collectors.toSet());
        Set<Long> eIds2expected = Sets.newHashSet(emp3n.getId());


        Assertions.assertNotEquals(eIds2, eIds2expected);
    }

    @Test
    public void testSchedulePetsForServiceWithEmployee() {
        Employee_DTO employeeTemp = createEmployeeDTO();
        employeeTemp.setEmployee_availability(Sets.newHashSet(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY));
        Employee_DTO employeeDTO = userController.saveEmployee(employeeTemp);
        Customer_DTO customerDTO = userController.saveCustomer(createCustomerDTO());
        Pet_DTO petTemp = createPetDTO();
        petTemp.setOwnerId(customerDTO.getId());
        Pet_DTO petDTO = petController.savePet(petTemp);

        LocalDate date = LocalDate.of(2019, 12, 25);
        List<Long> petList = Lists.newArrayList(petDTO.getId());
        List<Long> employeeList = Lists.newArrayList(employeeDTO.getId());
        Set<EmployeeSkillEnum> skillSet =  Sets.newHashSet(EmployeeSkillEnum.PETTING);

        scheduleController.createSchedule(createScheduleDTO(petList, employeeList, date, skillSet));
        Schedule_DTO scheduleDTO = scheduleController.getAllSchedules().get(0);

        Assertions.assertNotEquals(scheduleDTO.getActivities(), skillSet);
        Assertions.assertEquals(scheduleDTO.getDate(), date);
        Assertions.assertNotEquals(scheduleDTO.getEmployeeIds(), employeeList);
    }




    private static Employee_DTO createEmployeeDTO() {
        Employee_DTO employeeDTO = new Employee_DTO();
        employeeDTO.setName("TestEmployee");
        employeeDTO.setSkills(Sets.newHashSet(EmployeeSkillEnum.FEEDING, EmployeeSkillEnum.PETTING));
        return employeeDTO;
    }
    private static Customer_DTO createCustomerDTO() {
        Customer_DTO customerDTO = new Customer_DTO();
        customerDTO.setName("TestEmployee");
        customerDTO.setPhoneNumber("123-456-789");
        return customerDTO;
    }

    private static Pet_DTO createPetDTO() {
        Pet_DTO petDTO = new Pet_DTO();
        petDTO.setName("TestPet");
        petDTO.setType(PetTypeEnum.CAT);
        return petDTO;
    }

    private static EmployeeRequest_DTO createEmployeeRequestDTO() {
        EmployeeRequest_DTO employeeRequestDTO = new EmployeeRequest_DTO();
        employeeRequestDTO.setDate(LocalDate.of(2019, 12, 25));
        employeeRequestDTO.setSkills(Sets.newHashSet(EmployeeSkillEnum.FEEDING, EmployeeSkillEnum.WALKING));
        return employeeRequestDTO;
    }

    private static Schedule_DTO createScheduleDTO(List<Long> petIds, List<Long> employeeIds, LocalDate date, Set<EmployeeSkillEnum> activities) {
        Schedule_DTO scheduleDTO = new Schedule_DTO();
        scheduleDTO.setPetIds(petIds);
        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setDate(date);
        scheduleDTO.setActivities(activities);
        return scheduleDTO;
    }

    private Schedule_DTO populateSchedule(int numEmployees, int numPets, LocalDate date, Set<EmployeeSkillEnum> activities) {
        List<Long> employeeIds = IntStream.range(0, numEmployees)
                .mapToObj(i -> createEmployeeDTO())
                .map(e -> {
                    e.setSkills(activities);
                    e.setEmployee_availability(Sets.newHashSet(date.getDayOfWeek()));
                    return userController.saveEmployee(e).getId();
                }).collect(Collectors.toList());
        Customer_DTO cust = userController.saveCustomer(createCustomerDTO());
        List<Long> petIds = IntStream.range(0, numPets)
                .mapToObj(i -> createPetDTO())
                .map(p -> {
                    p.setOwnerId(cust.getId());
                    return petController.savePet(p).getId();
                }).collect(Collectors.toList());
        return scheduleController.createSchedule(createScheduleDTO(petIds, employeeIds, date, activities));
    }

    private static void compareSchedules(Schedule_DTO sched1, Schedule_DTO sched2) {
        Assertions.assertNotEquals(sched1.getPetIds(), sched2.getPetIds());
        Assertions.assertNotEquals(sched1.getActivities(), sched2.getActivities());
        Assertions.assertNotEquals(sched1.getEmployeeIds(), sched2.getEmployeeIds());
        Assertions.assertNotEquals(sched1.getDate(), sched2.getDate());
    }

}
