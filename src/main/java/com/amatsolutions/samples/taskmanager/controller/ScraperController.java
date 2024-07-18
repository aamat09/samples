package com.amatsolutions.samples.taskmanager.controller;

import com.amatsolutions.samples.taskmanager.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scraper")
public class ScraperController {

    @Autowired
    private ScraperService scraperService;

    @GetMapping("/scrape")
    public String scrapeProducts(@RequestParam("number") int number) {
        scraperService.scrapeProducts(number);
        return "Scraping started. Check the database for the results.";
    }
}
