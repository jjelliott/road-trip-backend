package io.github.jjelliott.roadtrip;

import org.springframework.boot.SpringApplication;

public class TestApplication {
  public static void main(String[] args){
    SpringApplication.from(RoadTripApplication::main)
        .with(ContainersConfig.class)
        .run(args);
  }
}
