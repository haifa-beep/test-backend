package com.test.backend.Controller;

import com.test.backend.entities.Courses;
import com.test.backend.services.interfaces.ICoursesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/course")
@CrossOrigin("*")
public class CoursesController {
    @Autowired
    private ICoursesService coursesService;

    @GetMapping
    public ResponseEntity<List<Courses>> getAllCourses() {
        List<Courses> courses = coursesService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

  @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Courses> addCourse(@ModelAttribute Courses course,
                                             @RequestPart("pictureFile") MultipartFile pictureFile) throws IOException, IOException {
        Courses addedCourse = coursesService.addCourse(course, pictureFile);
        return new ResponseEntity<>(addedCourse, HttpStatus.CREATED);
    }

    @RequestMapping(
            path = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Courses> updateCourse(@PathVariable int id,
                                                @ModelAttribute Courses updatedCourses,
                                                @RequestPart("pictureFile") MultipartFile pictureFile)
            throws IOException {
        Courses updatedCourse = coursesService.updateCourse(id, updatedCourses, pictureFile);
        return updatedCourse != null ?
                new ResponseEntity<>(updatedCourse, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        coursesService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/picture/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable int id) {
        byte[] image = coursesService.downloadImage(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("image/png"));
        return ResponseEntity.ok().headers(headers).body(image);
    }
}
