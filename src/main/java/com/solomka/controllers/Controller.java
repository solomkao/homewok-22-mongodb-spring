package com.solomka.controllers;

import com.solomka.models.Alarm;
import com.solomka.models.Schedule;
import com.solomka.models.dtos.CreateAlarmDto;
import com.solomka.models.dtos.CreateScheduleDto;
import com.solomka.models.dtos.UpdateAlarmDto;
import com.solomka.models.dtos.UpdateScheduleDto;
import com.solomka.repositories.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class Controller {

    private final MongoRepository mongoRepository;

    @Autowired
    public Controller(MongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @GetMapping(value = "/alarms")
    public ResponseEntity<List<Alarm>> getAlarms() throws ParseException {
        var alarms = mongoRepository.getAlarms();
        return new ResponseEntity<>(alarms, HttpStatus.OK);
    }

    @PostMapping(value = "/alarms/add",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAlarm(
            @RequestBody CreateAlarmDto createAlarmDto
    ) {
        Alarm alarm = new Alarm();
        alarm.setName(createAlarmDto.getName());
        alarm.setDate(createAlarmDto.getDate());
        alarm.setRingtone(createAlarmDto.getRingtone());
        alarm.setDisable(false);
        alarm.setSchedule(Schedule.ONCE);
        var result = mongoRepository.addAlarm(alarm);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping(value = "/alarms/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAlarm(
            @PathVariable("id") String alarmId,
            @RequestBody UpdateAlarmDto updateAlarmDto
    ) {
        System.out.println("updateAlarmDto = " + updateAlarmDto);
        var result = mongoRepository.updateAlarm(alarmId, updateAlarmDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/alarms/schedule/add/{id}")
    public ResponseEntity<?> addSchedule(
            @PathVariable("id") String alarmId,
            @RequestBody CreateScheduleDto createScheduleDto
    ) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/alarms/schedule/{id}")
    public ResponseEntity<?> updateSchedule(
            @PathVariable("id") String alarmId,
            @RequestBody UpdateScheduleDto updateScheduleDto
    ) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
