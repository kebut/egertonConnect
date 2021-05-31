package com.groupwork.pharmacy.management.system.model.pdfmodels;

import com.groupwork.pharmacy.management.system.model.Medicine;
import com.groupwork.pharmacy.management.system.model.User;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.dom4j.DocumentException;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class MedicinePDFExporter {
    private List<Medicine> listMedicines;

    public MedicinePDFExporter(List<Medicine> listMedicines) {
        this.listMedicines = listMedicines;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell= new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font= FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("NAME",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("PRICE",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("QUANTITY",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("EXPIRY DATE",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("STATUS",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("CATEGORY",font));
        table.addCell(cell);


    }

    private void writeTableData(PdfPTable table){
        for(Medicine medicine:listMedicines){
            table.addCell(String.valueOf(medicine.getId()));
            table.addCell(medicine.getName());
            table.addCell(String.valueOf(medicine.getPrice()));
            table.addCell(String.valueOf(medicine.getQuantity()));
            table.addCell(String.valueOf(medicine.getDateOfExpiry()));
            table.addCell(String.valueOf(medicine.getDrugStatus()));
            table.addCell(String.valueOf(medicine.getCategory().getName()));
        }

    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document= new Document(PageSize.A4);

        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font font= FontFactory.getFont(FontFactory.HELVETICA_BOLD );
        font.setColor(Color.WHITE);
        font.setSize(18);

        Paragraph title=new Paragraph("List Of All Medicine",font);

        document.add(title);

        PdfPTable table=new PdfPTable(7);

        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[]{1.0f,3.5f,1.5f,1.5f,3.0f,1.5f,3.0f});
        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
