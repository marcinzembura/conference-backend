package com.conferencebackend.lecture;

import com.conferencebackend.exception.LectureNotFoundException;
import com.conferencebackend.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository,
                          ReservationRepository reservationRepository) {
        this.lectureRepository = lectureRepository;
        this.reservationRepository = reservationRepository;
    }

    public Lecture getLectureById(Long id) {
        Optional<Lecture> optionalLecture = lectureRepository.findById(id);
        return optionalLecture.orElseThrow(() -> new LectureNotFoundException(id));
    }

    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    public void increaseLectureCapacity(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new LectureNotFoundException(lectureId));

        lecture.setCapacity(lecture.getCapacity() + 1);
        lectureRepository.save(lecture);
    }

    public void decreaseLectureCapacity(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new LectureNotFoundException(lectureId));

        lecture.setCapacity(lecture.getCapacity() - 1);
        lectureRepository.save(lecture);
    }


    public Map<Lecture, Double> getLectureInterestStats() {
        List<Lecture> lectures = lectureRepository.findAll();
        Map<Lecture, Double> stats = new LinkedHashMap<>();
        long totalAllReservations = reservationRepository.count();

        for (Lecture lecture : lectures) {
            int totalReservations = reservationRepository.countByLecture(lecture);
            double interest = (totalReservations / (double) totalAllReservations) * 100;
            stats.put(lecture, interest);
        }
        return stats;
    }

    public Map<String, Double> getTrackInterestStats() {
        List<Lecture> lectures = lectureRepository.findAll();
        long countOfReservation = reservationRepository.count();
        Map<String, Double> trackInterestMap = new LinkedHashMap<>();
        for (Lecture lecture : lectures) {
            String track = lecture.getTrack();
            Long countOfTrack=reservationRepository.countReservationsByTrack(track);
            double interest = (countOfTrack / (double) countOfReservation) * 100;
            trackInterestMap.put(track, interest);
        }
        return trackInterestMap;
    }

    @EventListener(ApplicationReadyEvent.class)
    private void initializeLectures() {
        List<Lecture> lectures = createSampleLectures();
        lectureRepository.saveAll(lectures);
    }


    private List<Lecture> createSampleLectures() {
        List<Lecture> lectures = new ArrayList<>();

        lectures.add(new Lecture("Introduction to Machine Learning", "Learn the basics of machine learning", "Track A", "2023-06-01 10:00:00", "2023-06-01 11:45:00", 5));
        lectures.add(new Lecture("Web Development Best Practices", "Discover the best practices in web development", "Track B", "2023-06-01 10:00:00", "2023-06-01 11:45:00", 5));
        lectures.add(new Lecture("Cloud Computing and Infrastructure", "Explore the world of cloud computing and infrastructure", "Track C", "2023-06-01 10:00:00", "2023-06-01 11:45:00", 5));
        lectures.add(new Lecture("Data Science: From Theory to Practice", "Learn how to apply data science in real-world scenarios", "Track A", "2023-06-01 12:00:00", "2023-06-01 13:45:00", 5));
        lectures.add(new Lecture("Cybersecurity Essentials", "Discover the essentials of cybersecurity", "Track B", "2023-06-01 12:00:00", "2023-06-01 13:45:00", 5));
        lectures.add(new Lecture("Artificial Intelligence in Business", "Explore the impact of AI in the business world", "Track C", "2023-06-01 12:00:00", "2023-06-01 13:45:00", 5));
        lectures.add(new Lecture("Software Testing Techniques", "Learn effective software testing techniques", "Track A", "2023-06-01 14:00:00", "2023-06-01 15:45:00", 5));
        lectures.add(new Lecture("DevOps and Continuous Integration", "Discover the principles of DevOps and continuous integration", "Track B", "2023-06-01 14:00:00", "2023-06-01 15:45:00", 5));
        lectures.add(new Lecture("Data Privacy and GDPR Compliance", "Understand data privacy regulations and GDPR compliance", "Track C", "2023-06-01 14:00:00", "2023-06-01 15:45:00", 5));

        return lectures;
    }
}
