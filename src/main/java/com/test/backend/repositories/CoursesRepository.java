package com.test.backend.repositories;

import com.test.backend.entities.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Courses, Integer> {
}
