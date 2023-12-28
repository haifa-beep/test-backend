package com.test.backend.services.interfaces;

import com.test.backend.entities.Courses;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ICoursesService {

    List<Courses> getAllCourses();

    Courses addCourse(Courses course, MultipartFile pictureFile) throws IOException;

    Courses updateCourse(int courseId, Courses updatedCourse, MultipartFile multipartFile) throws IOException;

    void deleteCourse(int courseId);

    byte[] downloadImage(int id);
}
