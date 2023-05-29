package com.conferencebackend.lecture;

import com.conferencebackend.exception.LectureNotFoundException;
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

        lecture.setCapacity(lecture.getCapacity() -1);
        lectureRepository.save(lecture);
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
