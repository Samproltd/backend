package com.exam.service.impl;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PdfService {

    public Path generatePdf(String studentName, String examName, int score) throws IOException {
        // Create a new PDF document
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Write content to the PDF
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Exam Result");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Student Name: " + studentName);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Exam Name: " + examName);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Score: " + score);
            contentStream.endText();
        }

        // Save the PDF to a temporary file
        Path pdfPath = Files.createTempFile("exam-result-", ".pdf");
        document.save(pdfPath.toFile());
        document.close();

        return pdfPath;
    }
}
