package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    // LIST
    @GetMapping
    public String home(Model model) {
        model.addAttribute("courses", service.getAll());
        return "home";
    }

    // UPCOMING
    @GetMapping("/upcoming")
    public String upcoming(Model model) {
        model.addAttribute("courses", service.getUpcoming());
        return "upcoming";
    }

    // CREATE FORM
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("course", new Course());
        return "add";
    }

    // SAVE
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Course course,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        }
        service.save(course);
        return "redirect:/courses";
    }

    // EDIT FORM
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("course", service.getById(id));
        return "edit";
    }

    // UPDATE
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute Course course,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        }
        service.save(course);
        return "redirect:/courses";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/courses";
    }
}