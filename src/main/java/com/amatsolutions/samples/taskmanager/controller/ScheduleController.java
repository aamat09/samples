package com.amatsolutions.samples.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {

    @GetMapping("/schedule")
    public String schedule() {
        return "forward:index.html";
    }

    @GetMapping("/pdf")
    public String pdfUpload() {
        return "forward:index.html";
    }
}
