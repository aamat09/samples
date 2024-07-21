package com.amatsolutions.samples.taskmanager.service;

import com.amatsolutions.samples.taskmanager.model.*;
import com.amatsolutions.samples.taskmanager.util.ChromeDriverUtils;
import com.amatsolutions.samples.taskmanager.util.MiddleFirstSorter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class HtmlProcessingService {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    private final RestTemplate restTemplate;
    private final HtmlPreprocessorService htmlPreprocessorService;
    private final ChromeDriverUtils chromeDriverUtils;

    public HtmlProcessingService(HtmlPreprocessorService htmlPreprocessorService, ChromeDriverUtils chromeDriverUtils) {
        this.restTemplate = new RestTemplate();
        this.htmlPreprocessorService = htmlPreprocessorService;
        this.chromeDriverUtils = chromeDriverUtils;
    }

    public ProductResponse containsProductList(String html, String url) {
        String apiUrl = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openaiApiKey);
        headers.set("Content-Type", "application/json");

        List<String> htmlChunks = MiddleFirstSorter.sortMiddleFirst(htmlPreprocessorService.preprocessHtml(html));

        for (String chunk : htmlChunks) {
            String prompt = "Identify if there is at least one product data  (with at least title, price and image url) contained in the following HTML: "
                    + chunk
                    + "[end of the html]"
                    + " And extract the specific CSS attributes from the html tags that contain product information so it can be " +
                    "extracted with selenium. " +
                    "Respond in the format {response: boolean, title: 'css classes string selector of title', description: 'css classes string selector of " +
                    " desc', rating: 'css classes string selector of rating', image: 'css classes string selector of the image url', price: " +
                    "'css classes string selector of the price', parent: css classes string selector of the wrapper container of each of the " +
                    "products to create iteration}. JSOn object needs to be well escaped. The string selector with the classes need to the specific " +
                    "selector query to be used by the method By.cssSelector() in selenium. ";
            ChatMessage systemMessage = new ChatMessage("system", "You are an AI that extracts product information from HTML.");
            ChatMessage userMessage = new ChatMessage("user", prompt);

            ChatCompletionRequest request = new ChatCompletionRequest(
                    "gpt-4o-mini",
                    Arrays.asList(systemMessage, userMessage)
            );

            HttpEntity<ChatCompletionRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<ChatCompletionResponse> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    entity,
                    ChatCompletionResponse.class
            );

            ChatCompletionResponse completionResponse = response.getBody();
            String rawResponse = completionResponse.getChoices().get(0).getMessage().getContent();
            String completionContent = rawResponse.substring(rawResponse.indexOf("{"), rawResponse.lastIndexOf("}") + 1);
            ProductResponse productResponse = parseResponse(completionContent);
            if (productResponse.isResponse()) {
                return extractProducts(url, productResponse);
            }
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return new ProductResponse(false);
    }

    private ProductResponse parseResponse(String response) {
        // Implement the logic to parse the response and extract product information
        // This is a placeholder implementation
        if (response.contains("\"response\": true")) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                Map responseMap = mapper.readValue(response, Map.class);
                String titleQuery = extractQuery(responseMap, "title");
                String descriptionQuery = extractQuery(responseMap, "description");
                String ratingQuery = extractQuery(responseMap, "rating");
                String imageQuery = extractQuery(responseMap, "image");
                String priceQuery = extractQuery(responseMap, "price");
                String parentQuery = extractQuery(responseMap, "parent");

                return new ProductResponse(true, titleQuery, descriptionQuery, ratingQuery, imageQuery, priceQuery, parentQuery);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }

        return new ProductResponse(false);
    }

    private String extractQuery(Map response, String key) {
        // Extract the query string for a given key from the response
        return (String) response.get(key);
    }

    private ProductResponse extractProducts(String url, ProductResponse productResponse) {

        List<ProductInfo> products = new ArrayList<>();

        WebDriver driver = chromeDriverUtils.initializeDriver();
        driver.get(url);

        List<WebElement> productElements = driver.findElements(By.cssSelector(productResponse.getParent()));

        for (WebElement productElement : productElements) {

            String title = (productElement.findElement(By.cssSelector(productResponse.getTitle())).getText());
            String description = (productElement.findElement(By.cssSelector(productResponse.getDescription())).getText());
            String price = productElement.findElement(By.cssSelector(productResponse.getPrice())).getText();
            String rating = (productElement.findElement(By.cssSelector(productResponse.getRating())).getText());
            String image = (productElement.findElement(By.cssSelector(productResponse.getImage())).getAttribute("src"));

            products.add(new ProductInfo(title, description, rating, image, price));
            // Check for existing product and update if necessary
        }
        productResponse.setProducts(products);
        return productResponse;
    }

    @Data
    public static class ProductResponse {
        private boolean response;
        private String title;
        private String description;
        private String rating;
        private String image;
        private String price;
        private List<ProductInfo> products;
        private String parent;

        public ProductResponse(boolean response) {
            this.response = response;
        }

        public ProductResponse(boolean response, String title, String description, String rating, String image, String price, String parent) {
            this.response = response;
            this.title = title;
            this.description = description;
            this.rating = rating;
            this.image = image;
            this.products = new ArrayList<>();
            this.price = price;
            this.parent = parent;
        }

    }
}
