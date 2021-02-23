package ru.allteran.sellpo.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import ru.allteran.sellpo.domain.RepairRequest;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ExcelService {

    private static final String NAME_MAIN_SHEET = "Акт";
    private static final String FONT_NAME = "Times New Roman";
    private static final short HEADER_FONT_HEIGHT = 16;
    private static final String CERTIFICATE_FILENAME = "CERTIFICATE.XLSX";

    public void generateAcceptanceCertificate(@NotNull RepairRequest request) {
        Workbook workbook = new XSSFWorkbook();

        Sheet main = workbook.createSheet(NAME_MAIN_SHEET);
        main.setColumnWidth(0, 6000);

        Row headerRow = main.createRow(1);
        CellStyle headerStyle = workbook.createCellStyle();

        XSSFFont headerFont = ((XSSFWorkbook) workbook).createFont();
        headerFont.setFontName(FONT_NAME);
        headerFont.setFontHeightInPoints(HEADER_FONT_HEIGHT);
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        Cell headerCell = headerRow.createCell(2);
        headerCell.setCellValue("HEADER");
        headerCell.setCellStyle(headerStyle);

        //Write file to
        String fileLocation = writeCertificateToFile(workbook);

    }

    /**
     * @param workbook is for generated XLSX file
     * @return path to workbook
     */
    private String writeCertificateToFile(Workbook workbook) {
        File currentDir = new File(".");
        String path = currentDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + CERTIFICATE_FILENAME;

        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(fileLocation);
            workbook.write(stream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileLocation;
    }

   //TODO: download generated XLSX file and delete it after
}
