package com.solomka;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class SpringConfig {

    @Bean
    public @Qualifier("mymongo") MongoClient getMongoClient(){
        return MongoClients.create("mongodb://192.168.99.100");
    }
}
