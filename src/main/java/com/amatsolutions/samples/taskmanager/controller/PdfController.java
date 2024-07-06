package com.amatsolutions.samples.taskmanager.controller;

import com.amatsolutions.samples.taskmanager.service.PdfService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/pdf")
@CrossOrigin(origins = "*")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdf(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file) throws JsonProcessingException {
        try {
            pdfService.savePdfData(id, file);
            ObjectMapper objectMapper = new ObjectMapper();
            Map response = Map.of("message", "PDF uploaded and text extracted successfully.");
            return ResponseEntity.ok(objectMapper.writeValueAsString(response));
        } catch (IOException e) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map response = Map.of("message", "Failed to upload PDF: " + e.getMessage());
            return ResponseEntity.status(500).body(objectMapper.writeValueAsString(response));
        }
    }

    @GetMapping("/getText")
    public ResponseEntity<String> getPdfText(@RequestParam("id") Long id) throws JsonProcessingException {
        try {
            String text = pdfService.getPdfText(id);
            ObjectMapper objectMapper = new ObjectMapper();
            Map response = Map.of("text", text);
            return ResponseEntity.ok(objectMapper.writeValueAsString(response));
        } catch (IOException e) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map response = Map.of("message", "Failed to get PDF text: " + e.getMessage());
            return ResponseEntity.status(500).body(objectMapper.writeValueAsString(response));
        }
    }
}
