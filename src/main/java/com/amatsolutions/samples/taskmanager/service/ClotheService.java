package com.amatsolutions.samples.taskmanager.service;

import com.amatsolutions.samples.taskmanager.model.Clothe;
import com.amatsolutions.samples.taskmanager.repository.ClotheRepository;
import com.amatsolutions.samples.taskmanager.util.ChromeDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClotheService {
    @Autowired
    private ClotheRepository clotheRepository;

    private WebDriver driver;

    @Autowired
    ChromeDriverUtils chromeDriverUtils;
    @Autowired
    private Environment environment;

    @PostConstruct
    public void init() {
        driver = chromeDriverUtils.initializeDriver();
    }

    @PreDestroy
    public void destroy() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void scrapeClothes() {
        String url = environment.getProperty("clothes.url");
        driver.get(url);
        List<Clothe> clothes = new ArrayList<>();
        List<WebElement> productElements = driver.findElements(By.cssSelector(".products .product"));

        for (WebElement productElement : productElements) {
            Clothe clothe = new Clothe();
            clothe.setType(productElement.findElement(By.cssSelector(".ast-woo-product-category")).getText());
            clothe.setTitle(productElement.findElement(By.cssSelector(".woocommerce-loop-product__title")).getText());
            clothe.setPrice(productElement.findElement(By.cssSelector(".price")).getText());
            clothe.setRating(productElement.findElement(By.cssSelector(".star-rating .rating")).getText());
            clothe.setImageUrl(productElement.findElement(By.cssSelector(".woocommerce-loop-product__link img")).getAttribute("src"));

            // Check for existing product and update if necessary
            Clothe existingClothe = clotheRepository.findByTitle(clothe.getTitle());
            if (existingClothe != null) {
                if (!existingClothe.getPrice().equals(clothe.getPrice())) {
                    existingClothe.setPrice(clothe.getPrice());
                    clotheRepository.save(existingClothe);
                }
            } else {
                clothes.add(clothe);
            }
        }

        clotheRepository.saveAll(clothes);
    }

    public List<Clothe> getAllClothes() {
        return clotheRepository.findAll();
    }
}
