package com.example.libraryusers.io;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.libraryusers.controller.response.PersonRest;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class PersonPDFExporter {
    private List<PersonRest> listUsers;

    public PersonPDFExporter(List<PersonRest> listUsers) {
        this.listUsers = listUsers;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Person ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("First Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Last Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Phone number", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Rented books", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (PersonRest person : listUsers) {
            table.addCell(person.getPersonId());
            table.addCell(person.getFirstName());
            table.addCell(person.getLastName());
            table.addCell(person.getEmail());
            table.addCell(String.valueOf(person.getPhoneNumber()));
            table.addCell(String.valueOf(person.getRentedBooks()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of customers", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2.5f, 2f, 2f, 3.0f, 3f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
