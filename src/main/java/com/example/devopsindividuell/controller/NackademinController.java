package com.example.devopsindividuell.controller;

import com.example.devopsindividuell.dto.NackademinDTO;
import com.example.devopsindividuell.service.NackademinService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("SameReturnValue")
@Controller
public class NackademinController {

    private final NackademinService nackademinService;

    public NackademinController(NackademinService nackademinService) {
        this.nackademinService = nackademinService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<String> uniqueCourseNames = nackademinService.getAllCourses().stream()
                .map(NackademinDTO::getCourseName)
                .distinct()
                .collect(Collectors.toList());
        model.addAttribute("courseNames", uniqueCourseNames);
        return "index";
    }

    @GetMapping("/details")
    public String courseDetails(@RequestParam("courseName") String courseName, Model model){
        List<NackademinDTO> courses = nackademinService.getAllCourses();
        List<String> solutions = new ArrayList<>();

        for (NackademinDTO course : courses) {
            if (course.getCourseName().equals(courseName))
                solutions.add(course.getSolution());
        }
        model.addAttribute("courseName", courseName);
        model.addAttribute("solutions", solutions);

        return "course-details";
    }

}
