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

    @GetMapping("/scraping")
    public String scraper() {
        return "forward:index.html";
    }

    @GetMapping("/marketplace")
    public String login() {
        return "forward:index.html";
    }

    @GetMapping("/marketplace/customer-dashboard")
    public String customer() {
        return "forward:index.html";
    }

    @GetMapping("/marketplace/dealer-dashboard")
    public String dealer() {
        return "forward:index.html";
    }

    @GetMapping("/game")
    public String game() {
        return "forward:index.html";
    }

}
