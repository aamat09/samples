package com.amatsolutions.samples.taskmanager.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HtmlPreprocessorService {

    private static final int CHUNK_SIZE = 20000;

    public List<String> preprocessHtml(String html) {
        List<String> chunks = new ArrayList<>();

        for (int start = 0; start < html.length(); start += CHUNK_SIZE) {
            int end = Math.min(start + CHUNK_SIZE, html.length());
            chunks.add(html.substring(start, end));
        }

        return chunks;
    }
}
