package com.solomka.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAlarmDto {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date date;

    private String name;

    private String ringtone;
}
