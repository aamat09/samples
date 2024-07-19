package com.amatsolutions.samples.taskmanager.controller;

import com.amatsolutions.samples.taskmanager.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scraper")
@CrossOrigin(origins = "*")
public class ScraperController {

    @Autowired
    private ScraperService scraperService;

    @GetMapping("/scrape")
    public String scrapeProducts(@RequestParam("number") int number) {
        scraperService.scrapeProducts(number);
        return "Scraping started. Check the database for the results.";
    }
}
