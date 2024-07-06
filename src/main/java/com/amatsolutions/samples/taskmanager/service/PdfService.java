package com.amatsolutions.samples.taskmanager.service;

import com.amatsolutions.samples.taskmanager.model.PdfData;
import com.amatsolutions.samples.taskmanager.repository.PdfDataRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PdfService {

    @Autowired
    private PdfDataRepository pdfDataRepository;

    public void savePdfData(Long id, MultipartFile file) throws IOException {
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        System.out.println(text);
        document.close();

        PdfData pdfData = new PdfData();
        pdfData.setId(id);
        pdfData.setText(text);

        pdfDataRepository.save(pdfData);
    }

    public String getPdfText(Long id) {
        return pdfDataRepository.findById(id).map(PdfData::getText).orElse(null);
    }
}
