package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.DTO.Schedule_DTO;
import com.udacity.jdnd.course3.critter.Entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.Mapper.ScheduleMapper;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public Schedule_DTO createSchedule(Schedule_DTO scheduleDTO) {
        ScheduleEntity scheduleEntity = scheduleMapper.ScheduleDTOtoEntity(scheduleDTO);
        scheduleEntity = scheduleRepository.save(scheduleEntity);
        return scheduleMapper.ScheduleEntityToDTO(scheduleEntity);
    }

    public List<Schedule_DTO> getAllSchedules() {
        List<ScheduleEntity> scheduleEntities = scheduleRepository.findAll();
        return scheduleMapper.ScheduleEntityListToDTO(scheduleEntities);
    }

    public List<Schedule_DTO> getScheduleForPet(Long petId) {
        List<ScheduleEntity> scheduleEntities = scheduleRepository.findSchedulesByPetsId(petId);
        return scheduleMapper.ScheduleEntityListToDTO(scheduleEntities);
    }

    public List<Schedule_DTO> getScheduleForEmployee(Long employeeId) {
        List<ScheduleEntity> scheduleEntities = scheduleRepository.findSchedulesByEmployeesId(employeeId);
        return scheduleMapper.ScheduleEntityListToDTO(scheduleEntities);
    }

    public List<Schedule_DTO> getScheduleForCustomer(Long customerId) {
        List<ScheduleEntity> scheduleEntities = scheduleRepository.findSchedulesByCustomerId(customerId);
        return scheduleMapper.ScheduleEntityListToDTO(scheduleEntities);
    }
}
