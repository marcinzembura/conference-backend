package com.conferencebackend.reservation;

import com.conferencebackend.lecture.Lecture;
import com.conferencebackend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByUserLogin(String login);

    boolean existsByUserAndLectureStartTime(User user, String startTime);

    int countByLecture(Lecture lecture);


}
