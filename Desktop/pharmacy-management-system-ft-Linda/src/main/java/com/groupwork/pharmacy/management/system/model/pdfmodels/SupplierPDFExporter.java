package com.groupwork.pharmacy.management.system.model.pdfmodels;

import com.groupwork.pharmacy.management.system.model.Suppliers;
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

public class SupplierPDFExporter {
    private List<Suppliers> listSuppliers;
    public SupplierPDFExporter(List<Suppliers> listSuppliers) {
        this.listSuppliers = listSuppliers;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell= new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font= FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("SUPPLIER ID",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("NAME OF COMPANY",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("LOCATION",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("PHONE NUMBER",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("EMAIL",font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table){
        for(Suppliers suppliers:listSuppliers){
            table.addCell(String.valueOf(suppliers.getId()));
            table.addCell(suppliers.getNameOfCompany());
            table.addCell(suppliers.getLocation());
            table.addCell(suppliers.getPhoneNumber());
            table.addCell(suppliers.getEmail());
        }

    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document= new Document(PageSize.A4);

        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font font= FontFactory.getFont(FontFactory.HELVETICA_BOLD );
        font.setColor(Color.WHITE);
        font.setSize(18);

        Paragraph title=new Paragraph("List Of All Suppliers",font);

        document.add(title);

        PdfPTable table=new PdfPTable(5);

        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[]{1.5f,3.5f,3.0f,3.0f,3.0f});
        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
