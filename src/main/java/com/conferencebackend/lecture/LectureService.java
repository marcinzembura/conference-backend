package com.conferencebackend.lecture;

import com.conferencebackend.exception.LectureNotFoundException;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public Lecture createLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public Lecture getLectureById(Long id) {
        Optional<Lecture> optionalLecture = lectureRepository.findById(id);
        return optionalLecture.orElseThrow(() -> new LectureNotFoundException(id));
    }

    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    public Lecture updateLecture(Lecture lecture) {
        if (!lectureRepository.existsById(lecture.getId())) {
            throw new LectureNotFoundException(lecture.getId());
        }
        return lectureRepository.save(lecture);
    }

    public void deleteLecture(Long id) {
        if (!lectureRepository.existsById(id)) {
            throw new LectureNotFoundException(id);
        }
        lectureRepository.deleteById(id);
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
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class LectureService {
//
//    private final LectureRepository lectureRepository;
//
//    @Autowired
//    public LectureService(LectureRepository lectureRepository) {
//        this.lectureRepository = lectureRepository;
//    }
//
//    public List<Lecture> getAllLectures() {
//        return lectureRepository.findAll();
//    }
//
//    public Lecture getLectureById(Long id) {
//        return lectureRepository.findById(id).orElse(null);
//    }
//
//    public Lecture getLectureByTitle(String title) {
//        return lectureRepository.findByTitle(title);
//    }
//
//    public boolean isLectureAvailable(Long id) {
//        Lecture lecture = lectureRepository.findByIdAndCapacityGreaterThan(id, 0);
//        return lecture != null;
//    }
//
//    public boolean isLoginAvailable(String login) {
//        return !lectureRepository.existsByTitleAndIdNot(login, null);
//    }
//
//    public void reserveLecture(Long id, String login, String email) throws LectureNotFoundException, LectureCapacityExceededException {
//        Lecture lecture = lectureRepository.findByIdAndCapacityGreaterThan(id, 0);
//        if (lecture == null) {
//            throw new LectureNotFoundException("Lecture not found or no available seats");
//        }
//
//        if (isLoginAvailable(login)) {
//            lecture.setCapacity(lecture.getCapacity() - 1);
//            lectureRepository.save(lecture);
//            sendReservationNotification(login, email);
//        } else {
//            throw new LectureCapacityExceededException("Login already taken");
//        }
//    }
//
//    public void cancelLectureReservation(Long id, String login) throws LectureNotFoundException {
//        Lecture lecture = lectureRepository.findById(id).orElseThrow(() -> new LectureNotFoundException("Lecture not found"));
//        if (login.equals(lecture.getTitle())) {
//            lecture.setCapacity(lecture.getCapacity() + 1);
//            lectureRepository.save(lecture);
//        }
//    }
//
//    public void updateEmail(String login, String newEmail) throws LectureNotFoundException {
//        Lecture lecture = lectureRepository.findByTitle(login);
//        if (lecture != null) {
//            lecture.setEmail(newEmail);
//            lectureRepository.save(lecture);
//        } else {
//            throw new LectureNotFoundException("Lecture not found");
//        }
//    }
//
//    public List<User> getAllRegisteredUsers() {
//        return lectureRepository.findAllUsers();
//    }
//
//    public List<LectureStatistics> generateLectureStatistics() {
//        return lectureRepository.getLectureStatistics();
//    }
//
//    public List<TrackStatistics> generateTrackStatistics() {
//        return lectureRepository.getTrackStatistics();
//    }
//
//    private void sendReservationNotification(String login, String email) {
//        // Metoda do wysyłania powiadomienia o rezerwacji
//        // Implementacja zależy od wymagań aplikacji
//    }
//}