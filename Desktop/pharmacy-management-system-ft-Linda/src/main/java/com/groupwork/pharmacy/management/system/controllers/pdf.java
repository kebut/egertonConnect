package com.groupwork.pharmacy.management.system.controllers;

import com.groupwork.pharmacy.management.system.model.*;
import com.groupwork.pharmacy.management.system.model.pdfmodels.AdminPDFExporter;
import com.groupwork.pharmacy.management.system.model.pdfmodels.MedicinePDFExporter;
import com.groupwork.pharmacy.management.system.model.pdfmodels.SupplierPDFExporter;
import com.groupwork.pharmacy.management.system.model.pdfmodels.UserPDFExporter;
import com.groupwork.pharmacy.management.system.service.CustomAdminDetailsService;
import com.groupwork.pharmacy.management.system.service.CustomMedicineService;
import com.groupwork.pharmacy.management.system.service.CustomSupplierDetailsService;
import com.groupwork.pharmacy.management.system.service.CustomUserDetailsService;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class pdf {
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    CustomSupplierDetailsService customSupplierDetailsService;

    @Autowired
    CustomAdminDetailsService customAdminDetailsService;

    @Autowired
    CustomMedicineService customMedicineService;

    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    String currentDateTime= formatter.format(new Date());
    String headerKey="Content-Disposition";

    @GetMapping("/users/export")
    public  void exportToPDF(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        String headerValue="attachment;filename=users_"+ currentDateTime +".pdf";

        response.setHeader(headerKey,headerValue);

        List<User> listUsers= customUserDetailsService.listAll();

        UserPDFExporter exporter=new UserPDFExporter(listUsers);
        exporter.export(response);

    }

    @GetMapping("/medicines/export")
    public  void exportMedicineToPDF(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        String headerValue="attachment;filename=medicines_"+ currentDateTime +".pdf";

        response.setHeader(headerKey,headerValue);

        List<Medicine> listMedicines= customMedicineService.listAll();

        MedicinePDFExporter exporter=new MedicinePDFExporter(listMedicines);
        exporter.export(response);

    }

    @GetMapping("/suppliers/export")
    public  void exportSuppliersToPDF(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        String headerValue="attachment;filename=suppliers_"+ currentDateTime +".pdf";

        response.setHeader(headerKey,headerValue);

        List<Suppliers> listSuppliers= customSupplierDetailsService.listAll();

        SupplierPDFExporter exporter=new SupplierPDFExporter(listSuppliers);
        exporter.export(response);

    }

    @GetMapping("/admins/export")
    public  void exportAdminToPDF(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        String headerValue="attachment;filename=admins_"+ currentDateTime +".pdf";

        response.setHeader(headerKey,headerValue);

        List<Admin> listAdmins= customAdminDetailsService.listAll();

        AdminPDFExporter exporter=new AdminPDFExporter(listAdmins);
        exporter.export(response);

    }

}
