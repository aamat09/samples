package com.amatsolutions.samples.taskmanager.repository;

import com.amatsolutions.samples.taskmanager.model.PdfData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdfDataRepository extends JpaRepository<PdfData, Long> {
}
