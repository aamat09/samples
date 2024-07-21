package com.amatsolutions.samples.taskmanager.controller;

import com.amatsolutions.samples.taskmanager.service.HtmlExtractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class HtmlExtractorController {

    private final HtmlExtractorService htmlExtractorService;

    @Autowired
    public HtmlExtractorController(HtmlExtractorService htmlExtractorService) {
        this.htmlExtractorService = htmlExtractorService;
    }

    @GetMapping("/api/extract")
    public String extractHtml(@RequestParam String url) {
        return htmlExtractorService.extractHtml(url);
    }
}
