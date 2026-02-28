package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    public List<Course> getAll() {
        return repo.findAll();
    }

    public List<Course> getUpcoming() {
        return repo.findByStartDateAfter(LocalDateTime.now());
    }

    public Course getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void save(Course course) {
        repo.save(course);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}