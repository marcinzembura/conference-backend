package com.conferencebackend.lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lectures")
public class LectureApi {

    private final LectureService lectureService;

    @Autowired
    public LectureApi(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping
    public ResponseEntity<List<Lecture>> getAllLectures() {
        List<Lecture> lectures = lectureService.getAllLectures();
        return ResponseEntity.ok(lectures);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lecture> getLectureById(@PathVariable Long id) {
        Lecture lecture = lectureService.getLectureById(id);
        return ResponseEntity.ok(lecture);
    }

    @GetMapping("/stats-interest")
    public  Map<Lecture, Double> getLectureInterestStats() {
       return lectureService.getLectureInterestStats();
    }

    @GetMapping("/stats-track")
    public  Map<String, Double> getTrackInterestStats() {
        return lectureService.getTrackInterestStats();
    }

}