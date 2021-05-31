package com.groupwork.pharmacy.management.system.model.pdfmodels;

import javax.servlet.http.HttpServletResponse;

import com.groupwork.pharmacy.management.system.model.User;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.dom4j.DocumentException;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class UserPDFExporter {
    private List<User> listUsers;

    public UserPDFExporter(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell= new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font= FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("USER ID",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("E-MAIL",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("First Name",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Last Name",font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table){
        for(User user:listUsers){
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getEmail());
            table.addCell(user.getFirstName());
            table.addCell(user.getLastName());
        }

    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document= new Document(PageSize.A4);

        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font font= FontFactory.getFont(FontFactory.HELVETICA_BOLD );
        font.setColor(Color.WHITE);
        font.setSize(18);

        Paragraph title=new Paragraph("List Of All Users",font);

        document.add(title);

        PdfPTable table=new PdfPTable(4);

        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[]{1.5f,3.5f,3.0f,3.0f});
        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
