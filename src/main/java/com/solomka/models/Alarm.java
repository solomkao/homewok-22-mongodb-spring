package com.solomka.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alarm {

    private String id;

    private Date date;

    private boolean disable;

    private String name;

    private String ringtone;

    private Schedule schedule;
}
