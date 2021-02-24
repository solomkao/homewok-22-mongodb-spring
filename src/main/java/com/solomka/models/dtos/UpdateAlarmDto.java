package com.solomka.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAlarmDto {

    private String name;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date date;

    private boolean disable;

    private String ringtone;
}
