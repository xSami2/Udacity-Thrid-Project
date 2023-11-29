package com.udacity.jdnd.course3.critter.Mapper;

import com.udacity.jdnd.course3.critter.DTO.Schedule_DTO;
import com.udacity.jdnd.course3.critter.Entity.ScheduleEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleMapper {

    public ScheduleEntity ScheduleDTOtoEntity(Schedule_DTO scheduleDTO) {
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        BeanUtils.copyProperties(scheduleDTO, scheduleEntity);
        return scheduleEntity;
    }

    public Schedule_DTO ScheduleEntityToDTO(ScheduleEntity scheduleEntity) {
        Schedule_DTO scheduleDTO = new Schedule_DTO();
        BeanUtils.copyProperties(scheduleEntity, scheduleDTO);
        return scheduleDTO;
    }

    public List<Schedule_DTO> ScheduleEntityListToDTO(List<ScheduleEntity> scheduleEntities) {
        List<Schedule_DTO> scheduleDTOs = new ArrayList<>();
        for (ScheduleEntity scheduleEntity : scheduleEntities) {
            Schedule_DTO scheduleDTO = new Schedule_DTO();
            BeanUtils.copyProperties(scheduleEntity, scheduleDTO);
            scheduleDTOs.add(scheduleDTO);
        }
        return scheduleDTOs;
    }
}
