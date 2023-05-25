package com.conferencebackend.reservation;

import com.conferencebackend.exception.LoginAlreadyExistsException;
import com.conferencebackend.exception.ReservationNotFoundException;
import com.conferencebackend.lecture.Lecture;
import com.conferencebackend.user.User;
import com.conferencebackend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    public ReservationService(@Autowired ReservationRepository reservationRepository, @Autowired UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository= userRepository;
    }

    public Reservation createReservation(User user, Lecture lecture) {
        // Check if the user already exists with the given login
        User existingLogin = userRepository.findByLogin(user.getLogin());
        if (existingLogin != null) {
            throw new LoginAlreadyExistsException(user.getLogin());
        }

        // Create the reservation
        Reservation reservation = new Reservation(user, lecture);
        return reservationRepository.save(reservation);
    }

    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));
        reservationRepository.delete(reservation);
    }


    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getReservationsByLogin(String login) {
        User user=userRepository.findByLogin(login);
        return reservationRepository.findAllByUserLogin(user);
    }



}
