package com.solomka.services;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.solomka.Utils;
import com.solomka.exceptions.IllegalScheduleException;
import com.solomka.models.Alarm;
import com.solomka.models.Schedule;
import com.solomka.models.dtos.CreateAlarmDto;
import com.solomka.models.dtos.CreateScheduleDto;
import com.solomka.models.dtos.UpdateAlarmDto;
import com.solomka.repositories.MongoRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class MongoService {
    private final MongoRepository mongoRepository;

    public MongoService(MongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    public List<Alarm> getAlarms() throws ParseException {
        return mongoRepository.getAlarms();
    }

    public boolean addAlarm(CreateAlarmDto createAlarmDto) {
        Alarm alarm = new Alarm();
        alarm.setName(createAlarmDto.getName());
        alarm.setDate(createAlarmDto.getDate());
        alarm.setRingtone(createAlarmDto.getRingtone());
        alarm.setDisable(false);
        alarm.setSchedule(Schedule.ONCE);
        return mongoRepository.addAlarm(alarm);
    }

    public boolean updateAlarm(String alarmId, UpdateAlarmDto updateAlarmDto) {
        return mongoRepository.updateAlarm(alarmId, updateAlarmDto);
    }

    public boolean addSchedule(String alarmId, CreateScheduleDto createScheduleDto) throws IllegalScheduleException {
        Schedule schedule = Utils.convertStringToSchedule(createScheduleDto.getSchedule());
        return mongoRepository.addSchedule(alarmId, schedule);
    }
}
