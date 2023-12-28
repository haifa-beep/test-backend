package com.test.backend.services.impl;

import com.test.backend.config.FilenameUtils;
import com.test.backend.entities.Courses;
import com.test.backend.repositories.CoursesRepository;
import com.test.backend.services.interfaces.ICoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CoursesService implements ICoursesService {

    @Autowired
    CoursesRepository coursesRepository;

    @Override
    public List<Courses> getAllCourses() {
        return coursesRepository.findAll();
    }

    @Override
    public Courses addCourse(Courses course, MultipartFile pictureFile) throws IOException {
        course.setPicture(FilenameUtils.compressImage(pictureFile.getBytes()));
        return coursesRepository.save(course);
    }

    @Override
    public Courses updateCourse(int courseId, Courses updatedCourse, MultipartFile multipartFile) throws IOException {
        Optional<Courses> existingCourse = coursesRepository.findById(courseId);

        if (existingCourse.isPresent()) {
            Courses courseToUpdate = existingCourse.get();
            courseToUpdate.setTitle(updatedCourse.getTitle());
            courseToUpdate.setPrice(updatedCourse.getPrice());
            courseToUpdate.setPicture(FilenameUtils.compressImage(multipartFile.getBytes()));
            return coursesRepository.save(courseToUpdate);
        }
            return null;

    }

    @Override
    public void deleteCourse(int courseId) {
        coursesRepository.deleteById(courseId);
    }

    @Override
    public byte[] downloadImage(int id) {

        Courses course = coursesRepository.findById(id).orElse(null);

        assert course != null;
        return FilenameUtils.decompressImage(course.getPicture());

    }
}
