package com.conferencebackend.lecture;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "lectures")
@Getter
@Setter
@ToString
public class Lecture {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String track;
    private String startTime;
    private String endTime;
    private Integer capacity;


    public Lecture() {
    }

    public Lecture(String title, String description, String track, String startTime, String endTime, Integer capacity) {
        this.title = title;
        this.description = description;
        this.track = track;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
    }

}
