package com.conferencebackend.reservation;

import com.conferencebackend.lecture.Lecture;
import com.conferencebackend.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "reservations")
@Getter
@Setter
@ToString
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "lecture_id", referencedColumnName = "id")
    private Lecture lecture;

    public Reservation() {
    }

    public Reservation(User user, Lecture lecture) {
        this.user = user;
        this.lecture = lecture;
    }
}
