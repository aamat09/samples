package com.amatsolutions.samples.taskmanager.service;

import com.amatsolutions.samples.taskmanager.model.ProductInfo;
import com.amatsolutions.samples.taskmanager.util.ChromeDriverUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class HtmlExtractorServiceImpl implements HtmlExtractorService {

    @Autowired
    ChromeDriverUtils chromeDriverUtils;

    @Autowired
    HtmlProcessingService htmlProcessingService;

    @Autowired
    ClotheService clotheService;

    @Value("${openai.api.key}")
    private String openaiApiKey;

    private final RestTemplate restTemplate;

    public HtmlExtractorServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public String extractHtml(String url) {
        WebDriver driver = chromeDriverUtils.initializeDriver();
        driver.get(url);
        String pageSource = driver.getPageSource();
        driver.quit();
        HtmlProcessingService.ProductResponse productResponse = htmlProcessingService.containsProductList(pageSource, url);

        if (productResponse.isResponse()) {
            System.out.println(productResponse.getProducts().toString());
            clotheService.saveAll(productResponse.getProducts());
        } else {
            return "No product information found.";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(productResponse.getProducts());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Failed to convert product information to JSON";
        }
    }
}
