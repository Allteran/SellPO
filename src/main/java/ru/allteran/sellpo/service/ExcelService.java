package ru.allteran.sellpo.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.allteran.sellpo.models.PointOfSales;
import ru.allteran.sellpo.models.RepairRequest;
import ru.allteran.sellpo.models.User;
import ru.allteran.sellpo.repo.POSRepository;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
public class ExcelService {
    @Value("${excel.header.template}")
    private String headerRowTemplate;

    private static final String CERTIFICATE_FILENAME_PREFIX = "CERTIFICATE_";
    private static final String XLSX_DEFAULT_DIR = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "downloads" + File.separator;
    private static final String XLSX_TEMPLATE_DIR = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "files" + File.separator;
    private static final String XLSX_TEMPLATE_NAME = "CERTIFICATE_TEMPLATE.xlsx";

    @Autowired
    private POSRepository posRepo;

    /**
     * Next method generate acceptance certificate and fill it with actual client's data
     *
     * @param request gives us info about request with all that data that we can put in XLSX file
     * @return path to generated certificate so we can give it to downloadMethod
     */
    public String generateAcceptanceCertificate(@NotNull RepairRequest request, User user, Map<String, String> form) {
        File currentDir = new File(".");
        String path = currentDir.getAbsolutePath().substring(0, currentDir.getAbsolutePath().length() - 1) +
                XLSX_TEMPLATE_DIR + XLSX_TEMPLATE_NAME;
        FileInputStream file = null;
        Workbook workbook = null;
        try {
            file = new FileInputStream(new File(path));
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert workbook != null;
        Sheet mainSheet = workbook.getSheetAt(0);
        Cell headerCell = mainSheet.getRow(6).getCell(1);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyy");
        String currentDate = dateFormatter.format(new Date());
        headerCell.setCellValue(headerRowTemplate + " " + currentDate);

        PointOfSales pos = null;
        for (String posId : form.values()) {
             pos = posRepo.findFirstById(posId);
            if (pos != null) {
//                request.setPosId(pos.getId());
                break;
            }
        }
        //set pos name to cell
        mainSheet.getRow(2).getCell(1).setCellValue(Objects.requireNonNull(pos).getId() + ", " + pos.getCity() + 
                ", " + pos.getStreet());

        //set full name of user to cell
        mainSheet.getRow(4).getCell(2).setCellValue(user.getFullName());

        //set name of client to cell
        mainSheet.getRow(7).getCell(2).setCellValue(request.getClientName());

        //set phone of client to cell
        mainSheet.getRow(8).getCell(2).setCellValue(request.getClientPhone());

        //set type of device to cell
        mainSheet.getRow(9).getCell(2).setCellValue(request.getProductType());

        //set model of device to cell
        mainSheet.getRow(10).getCell(2).setCellValue(request.getProductName());

        //set request reason to cell
        mainSheet.getRow(11).getCell(2).setCellValue(request.getRequestReason());

        //set equip set to cell
        mainSheet.getRow(12).getCell(2).setCellValue(request.getEquipSet());

        //set device state to cell
        mainSheet.getRow(13).getCell(2).setCellValue(request.getDeviceState());

        //set client name to sign side of certificate
        mainSheet.getRow(31).getCell(4).setCellValue(request.getClientName());

        //set users name to sign side
        mainSheet.getRow(34).getCell(4).setCellValue(user.getFullName());

        return writeCertificateToFile(workbook);
    }

    /**
     * @param workbook is for generated XLSX file
     * @return path to generated certificate on server
     */
    private String writeCertificateToFile(Workbook workbook) {
        String currentDateAndTime = LocalDateTime.now().toString();
        currentDateAndTime = currentDateAndTime.replaceAll("[-:]", "");

        File currentDir = new File(".");
        String path = currentDir.getAbsolutePath().substring(0, currentDir.getAbsolutePath().length() - 1) + XLSX_DEFAULT_DIR;
        String fileLocation = path + CERTIFICATE_FILENAME_PREFIX + currentDateAndTime + ".xlsx";

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
}
