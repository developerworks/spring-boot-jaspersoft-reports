package com.example.demo7springbootjaspersoftreports.controller;

import com.example.demo7springbootjaspersoftreports.entity.Product;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    String generateReport(LocalDate localDate, String fileFromat) throws JRException, IOException;

    List<Product> findAllProducts();

}
