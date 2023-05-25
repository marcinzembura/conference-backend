package com.conferencebackend.reservation;

import com.conferencebackend.lecture.Lecture;
import com.conferencebackend.lecture.LectureService;
import com.conferencebackend.user.User;
import com.conferencebackend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationApi {
    private final ReservationService reservationService;
    private final LectureService lectureService;
    private final UserService userService;


    public ReservationApi(@Autowired  ReservationService reservationService,
                          @Autowired LectureService lectureService,
                          @Autowired UserService userService) {
        this.reservationService = reservationService;
        this.lectureService = lectureService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestParam String login, @RequestParam String email, @RequestParam Long lectureId) {
        Lecture lecture = lectureService.getLectureById(lectureId);
        User user= userService.getUserByLogin(login);
        Reservation reservation = reservationService.createReservation(user, lecture);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{login}")
    public ResponseEntity<List<Reservation>> getReservationsByLogin(@PathVariable String login) {
        List<Reservation> reservations = reservationService.getReservationsByLogin(login);
        return ResponseEntity.ok(reservations);
    }


}
