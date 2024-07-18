package com.amatsolutions.samples.taskmanager.service;

import com.amatsolutions.samples.taskmanager.model.NikeShoeProduct;
import com.amatsolutions.samples.taskmanager.repository.NikeShoeProductRepository;
import com.amatsolutions.samples.taskmanager.util.ChromeDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScraperService {

    @Autowired
    private NikeShoeProductRepository shoeProductRepository;

    @Autowired
    private Environment environment;

    @Autowired
    ChromeDriverUtils chromeDriverUtils;



    public void scrapeProducts(int number) {;
        WebDriver driver = chromeDriverUtils.initializeDriver();
        String productUrl = environment.getProperty("product.url"+number);
        driver.navigate().to(productUrl);

        List<WebElement> productCards = driver.findElements(By.cssSelector(".product-card"));

        for (WebElement card : productCards) {
            String title = card.findElement(By.cssSelector(".product-card__title")).getText();
            String price = card.findElement(By.cssSelector(".product-price")).getText();
            String description = card.findElement(By.cssSelector(".product-card__subtitle")).getText();
            String imageUrl = card.findElement(By.cssSelector(".product-card__hero-image")).getAttribute("src");

            NikeShoeProduct existingProduct = shoeProductRepository.findByTitle(title);
            if (existingProduct == null) {
                // Insert new product
                NikeShoeProduct newProduct = new NikeShoeProduct(null, title, price, description, imageUrl, number);
                shoeProductRepository.save(newProduct);
            } else if (!existingProduct.getPrice().equals(price)) {
                // Update existing product's price
                existingProduct.setPrice(price);
                shoeProductRepository.save(existingProduct);
            }
        }

        driver.quit();
    }
}
