package com.amatsolutions.samples.taskmanager.controller;

import com.amatsolutions.samples.taskmanager.service.ClotheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClotheController {
    @Autowired
    private ClotheService clotheService;

    @GetMapping("/api/scraper/scrapeClothes")
    public String scrapeClothes() {
        clotheService.scrapeClothes();
        return "Scraping and saving clothes completed successfully";
    }
}
