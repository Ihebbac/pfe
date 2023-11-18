package com.pfe.pfe;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class PfeApplication {

    public static void main(String[] args) {

        SpringApplication.run(PfeApplication.class, args);
        String excelFilePath = "C:\\Users\\bouma\\Desktop\\test.xlsx";

        try (
                FileInputStream fis = new FileInputStream(new File(excelFilePath));
                Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming you want to read from the first sheet

            for (Row row : sheet) {
                for (Cell cell : row) {
                    // Depending on the cell type, do different things
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        case BLANK:
                            System.out.print("[BLANK]\t");
                            break;
                        default:
                            System.out.print("[UNKNOWN]\t");
                    }
                }
                System.out.println(); // Move to the next line after each row
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


