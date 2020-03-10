package com.cpd;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.annotation.EnableNeo4jAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableNeo4jAuditing
@EnableScheduling
@EntityScan(basePackages = {"com.cpd.entity.nodes","com.cpd.entity.arrows","com.cpd.model"})
public class Gades {
    public static void main(String[] args) {
        SpringApplication.run(Gades.class, args);
    }

    @Bean
    public Driver neo4jDriver() {
        return GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic( "neo4j", "tecanexo123" ));
    }
}