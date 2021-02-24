package com.solomka.repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.solomka.Utils;
import com.solomka.models.Alarm;
import com.solomka.models.Schedule;
import com.solomka.models.dtos.UpdateAlarmDto;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MongoRepository {

    private final MongoClient mongoClient;

    public MongoRepository(@Qualifier("mymongo") MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public List<Alarm> getAlarms() throws ParseException {
        var db = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = db.getCollection("alarms");
        List<Alarm> alarms = new ArrayList<>();
        for (Document document : collection.find()) {
            Alarm alarm = new Alarm();
            alarm.setId(document.get("_id", ObjectId.class).toString());
            alarm.setName(document.get("name", String.class));
            alarm.setDate(Utils.convertStringToDate(document.get("date", String.class)));
            alarm.setRingtone(document.get("ringtone", String.class));
            alarm.setDisable(document.get("disable", Boolean.class));
            alarm.setSchedule(Utils.convertStringToSchedule(document.get("schedule", String.class)));
            alarms.add(alarm);
        }
        return alarms;
    }

    public boolean addAlarm(Alarm alarm) {
        var db = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = db.getCollection("alarms");
        Document document = new Document();
        document.append("date", Utils.convertDateToString(alarm.getDate()));
        document.append("disable", false);
        document.append("name", alarm.getName());
        document.append("ringtone", alarm.getRingtone());
        document.append("schedule", alarm.getSchedule().toString());
        var result = collection.insertOne(document);
        return result.wasAcknowledged();

    }

    public boolean updateAlarm(String alarmId, UpdateAlarmDto updateAlarmDto) {
        var db = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = db.getCollection("alarms");
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(alarmId));
        Document found = collection.find(query).first();
        if (found != null) {
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("name", updateAlarmDto.getName());
            newDocument.put("date", Utils.convertDateToString(updateAlarmDto.getDate()));
            newDocument.put("disable", updateAlarmDto.isDisable());
            newDocument.put("ringtone", updateAlarmDto.getRingtone());
            Bson updateOperation = new Document("$set", newDocument);
            var result = collection.updateOne(found, updateOperation);
            return result.getMatchedCount() > 0;
        }
        return false;
    }

    public boolean addSchedule(String alarmId, Schedule schedule) {
        var db = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = db.getCollection("alarms");
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(alarmId));
        Document found = collection.find(query).first();
        if (found != null) {
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("schedule", schedule.getValue());
            Bson updateOperation = new Document("$set", newDocument);
            var result = collection.updateOne(found, updateOperation);
            return result.getMatchedCount() > 0;
        }
        return false;
    }
}
