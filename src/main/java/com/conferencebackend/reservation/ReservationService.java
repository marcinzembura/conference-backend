package com.conferencebackend.reservation;

import com.conferencebackend.exception.LectureIsFullException;
import com.conferencebackend.exception.ReservationConflictException;
import com.conferencebackend.exception.ReservationNotFoundException;
import com.conferencebackend.exception.UserNotFoundException;
import com.conferencebackend.lecture.Lecture;
import com.conferencebackend.lecture.LectureService;
import com.conferencebackend.user.User;
import com.conferencebackend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    private LectureService lectureService;

    public ReservationService(@Autowired ReservationRepository reservationRepository,
                              @Autowired UserRepository userRepository,
                              @Autowired LectureService lectureService) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.lectureService = lectureService;
    }

    public Reservation createReservation(User user, Lecture lecture) {
        if (user != null) {
            if (lecture.getCapacity() > 0) {
                boolean hasConflict = reservationRepository.existsByUserAndLectureStartTime(user, lecture.getStartTime());
                if (hasConflict) {
                    throw new ReservationConflictException(lecture.getStartTime());
                }
                Reservation reservation = new Reservation(user, lecture);
                Reservation savedReservation = reservationRepository.save(reservation);
                lectureService.decreaseLectureCapacity(lecture.getId());
                sendMessageToFile(savedReservation.getLecture().getStartTime(), user.getLogin(), lecture.getTitle());
                return savedReservation;
            } else {
                throw new LectureIsFullException(lecture.getId());
            }
        } else {
            throw new UserNotFoundException("!");
        }
    }

    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));
        reservationRepository.delete(reservation);
        lectureService.increaseLectureCapacity(reservation.getLecture().getId());
    }


    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getReservationsByLogin(String login) {
        try {
            return reservationRepository.findAllByUserLogin(login);
        }catch (UserNotFoundException exc){
            throw new UserNotFoundException(login);
        }
    }

    public void sendMessageToFile(String date, String recipient, String content) {
        try {
            FileWriter writer = new FileWriter("powiadomienia", true);
            writer.write("Lecture date: " + date + "\n");
            writer.write("User login: " + recipient + "\n");
            writer.write("Lecture title: " + content + "\n\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
