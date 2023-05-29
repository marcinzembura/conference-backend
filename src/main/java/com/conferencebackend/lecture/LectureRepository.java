package com.conferencebackend.lecture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    @Modifying
    @Query("UPDATE Lecture l SET l.capacity = l.capacity - 1 WHERE l.id = :lectureId AND l.capacity > 0")
    void decreaseCapacity(@Param("lectureId") Long lectureId);


}