package com.conferencebackend.lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    @PostMapping("/{id}/reserve")
//    public ResponseEntity<String> reserveLecture(@PathVariable Long id, @RequestParam String login, @RequestParam String email) {
//        boolean reservationSuccess = lectureService.reserveLecture(id, login, email);
//        if (reservationSuccess) {
//            String message = "Reservation successful. Confirmation email sent to " + email;
//            return ResponseEntity.ok(message);
//        } else {
//            String message = "Failed to reserve lecture. Please check your login and email.";
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//        }
//    }
//
//    @DeleteMapping("/{id}/cancel")
//    public ResponseEntity<String> cancelLectureReservation(@PathVariable Long id, @RequestParam String login) {
//        boolean cancellationSuccess = lectureService.cancelLectureReservation(id, login);
//        if (cancellationSuccess) {
//            String message = "Lecture reservation cancelled successfully.";
//            return ResponseEntity.ok(message);
//        } else {
//            String message = "Failed to cancel lecture reservation. Please check your login.";
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//        }
//    }
//
//    @PutMapping("/{id}/update-email")
//    public ResponseEntity<String> updateEmail(@PathVariable Long id, @RequestParam String login, @RequestParam String newEmail) {
//        boolean updateSuccess = lectureService.updateEmail(id, login, newEmail);
//        if (updateSuccess) {
//            String message = "Email updated successfully.";
//            return ResponseEntity.ok(message);
//        } else {
//            String message = "Failed to update email. Please check your login.";
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//        }
//    }
//
//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = lectureService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }
//
//    @GetMapping("/report/lecture-interest")
//    public ResponseEntity<String> generateLectureInterestReport() {
//        String report = lectureService.generateLectureInterestReport();
//        return ResponseEntity.ok(report);
//    }
//
//    @GetMapping("/report/track-interest")
//    public ResponseEntity<String> generateTrackInterestReport() {
//        String report = lectureService.generateTrackInterestReport();
//        return ResponseEntity.ok(report);
//    }
}