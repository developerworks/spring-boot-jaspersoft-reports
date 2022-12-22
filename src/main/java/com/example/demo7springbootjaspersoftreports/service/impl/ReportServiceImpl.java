package com.example.demo7springbootjaspersoftreports.service.impl;

import com.example.demo7springbootjaspersoftreports.entity.Product;
import com.example.demo7springbootjaspersoftreports.repository.ProductRepository;
import com.example.demo7springbootjaspersoftreports.controller.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 创建一个名为ReportServiceImpl实现ReportService接口的类。
     * 实施generateReport()和findAllProducts()方法。该getJasperPrint()方法处理编译 jasper 报告文件的功能。
     *
     * 此方法接受我们用来填充报告和字符串的数据集合。该字符串指示 Jasper 报告源文件的位置。运行时，该函数返回一个实例JasperPrint：
     *
     * @param phoneCollection
     * @param resourceLocation
     * @return
     * @throws FileNotFoundException
     * @throws JRException
     */

    private JasperPrint getJasperPrint(List<Product> phoneCollection, String resourceLocation) throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile(resourceLocation);
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(phoneCollection);
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "David");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        return jasperPrint;
    }

    private Path getUploadPath(String fileFormat, JasperPrint jasperPrint, String fileName) throws IOException, JRException {
        String s = StringUtils.cleanPath("./generated-reports");
        Path path = Paths.get(s);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        if ("pdf".equalsIgnoreCase(fileFormat)) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + fileName);
        }
        return path;
    }

    private String getPdfFileLink(String uploadPath) {
        return uploadPath + "/products.pdf";
    }


    @Override
    public String generateReport(LocalDate localDate, String fileFromat) throws JRException, IOException {
        List<Product> allByCreatedAt = productRepository.findAllByCreatedAt(localDate);
        String resourceLocation = "classpath:products.jrxml";
        JasperPrint jasperPrint = getJasperPrint(allByCreatedAt, resourceLocation);
        String fileName = "/" + "products.pdf";
        Path uploadPath = getUploadPath(fileFromat, jasperPrint, fileName);

        return getPdfFileLink(uploadPath.toString());
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
}
