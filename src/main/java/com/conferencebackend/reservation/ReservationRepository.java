package com.conferencebackend.reservation;

import com.conferencebackend.lecture.Lecture;
import com.conferencebackend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByUserLogin(String login);

    boolean existsByUserAndLectureStartTime(User user, String startTime);

    int countByLecture(Lecture lecture);


    @Query("SELECT COUNT(*) FROM Reservation r JOIN r.lecture l WHERE l.track = :track")
    Long countReservationsByTrack(@Param("track") String track);


}
