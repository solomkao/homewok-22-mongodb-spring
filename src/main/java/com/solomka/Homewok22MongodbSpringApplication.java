package com.solomka;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.solomka.models.Schedule;
import lombok.SneakyThrows;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;

@SpringBootApplication
public class Homewok22MongodbSpringApplication {

    private final MongoClient mongoClient;

    @Autowired
    public Homewok22MongodbSpringApplication(@Qualifier("mymongo") MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(Homewok22MongodbSpringApplication.class, args);
    }

    @PostConstruct
    @SneakyThrows
    public void fillMongoDB() {
        var db = mongoClient.getDatabase("test");
        db.createCollection("alarms");
        MongoCollection<Document> collection = db.getCollection("alarms");
        Document document = new Document();
        document.append("date", Utils.convertDateToString(new Date()));
        document.append("disable", false);
        document.append("name", Utils.getRandomNameAlarm());
        document.append("ringtone", "Leonard Cohen - Avalanche");
        document.append("schedule", Schedule.ONCE.toString());
        collection.insertOne(document);
    }

    @PreDestroy
    public void closeConnection() {
        mongoClient.close();
    }
}
