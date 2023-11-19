package com.pfe.pfe;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;


@Controller
public class PrintController {
    @Autowired // injectih
    ReadConfigFile readConfigFile;

    private static List<List<String>> respenseAndPeoples;

    private final static String AUDITIF = "auditif";
    private final static String VISUEL = "visuel";
    private final static String KINESTHESIQUE = "kinesthesique";
    private static List<StudentScor> Group_auditif;
    private static List<StudentScor> Group_visuel;
    private static List<StudentScor> Group_kinesthesique;

    @GetMapping("/") // ki etjik get jaweb heka
    public ModelAndView getList() {
        Group_auditif = new ArrayList<>();
        Group_visuel = new ArrayList<>();
        Group_kinesthesique = new ArrayList<>();
        readExelData();
        respenseAndPeoples.subList(1, respenseAndPeoples.size()).forEach(this::findGroup);
     
        ModelAndView modelAndView = new ModelAndView();

        Map<String, Object> model = new HashMap<>();
        model.put("Group_auditif",Group_auditif);
        model.put("Group_visuel",Group_visuel);
        model.put("Group_kinesthesique",Group_kinesthesique);
        model.put("size",respenseAndPeoples.size()-1);


        Map<String, Integer> graphData = new TreeMap<>();
        graphData.put("auditif", (Group_auditif.size()));
        graphData.put("visuel", (Group_visuel.size()));
        graphData.put("kinesthesique",  (Group_kinesthesique.size()));
        model.put("chartData", graphData);

        modelAndView.addAllObjects(model);

        // Set the view name
        modelAndView.setViewName("index"); // "exampleView" is the logical view name

        return modelAndView;
    }

    private void findGroup(List<String> studentRespenses) {
        var auditifScore = 0;
        var visuelScore = 0;
        var kinesthesiqueScore = 0;
        var mail = studentRespenses.get(0);
        var questionNumber = readConfigFile.getQuestions().size();
        List<String> studentRespensestmp = studentRespenses.subList(1, studentRespenses.size());
        for (int i = 0; i < studentRespensestmp.size(); i++) {
            var choixStudent = studentRespensestmp.get(i);
            Question questioni = readConfigFile.getQuestions().get(i);
            var style = questioni.getStyle();
            List<Respense> repenseList = questioni.getRespense();
            Integer StudentScore = 0;
            Integer choixIndex = 0;
            for (int j = 0; j < 2; j++) {
                if (repenseList.get(j).getChoix().equals(choixStudent)) {
                    StudentScore = repenseList.get(j).getScore();
                    choixIndex = j;

                }
            }
            if (style.equals(AUDITIF)) auditifScore = auditifScore + StudentScore;
            else if (style.equals(VISUEL)) visuelScore = visuelScore + StudentScore;
            else if (style.equals(KINESTHESIQUE)) kinesthesiqueScore = kinesthesiqueScore + StudentScore;
            else {
                if (choixIndex == 0) auditifScore = auditifScore + StudentScore;
                else if (choixIndex == 1) visuelScore = visuelScore + StudentScore;
                else kinesthesiqueScore = kinesthesiqueScore + StudentScore;
            }


        }


        if (Collections.max(List.of(auditifScore, visuelScore, kinesthesiqueScore)) == auditifScore) {
            Group_auditif.add(new StudentScor(mail,auditifScore));
        } else if (Collections.max(List.of(auditifScore, visuelScore, kinesthesiqueScore)) == visuelScore) {
            Group_visuel.add(new StudentScor(mail,visuelScore));
        } else Group_kinesthesique.add(new StudentScor(mail,kinesthesiqueScore));


    }


    private static void readExelData() {
        String excelFilePath = "C:\\Users\\iheb\\reponse.xlsx";
        respenseAndPeoples = new ArrayList<>();
        try (
                FileInputStream fis = new FileInputStream(new File(excelFilePath));
                Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming you want to read from the first sheet

            for (Row row : sheet) {
                List<String> listtmp = new ArrayList<>();
                for (Cell cell : row) {
                    // Depending on the cell type, do different things
                    switch (cell.getCellType()) {
                        case STRING:
                            listtmp.add(cell.getStringCellValue());
                            //    System.out.println(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            break;
                        case BLANK:
                            break;
                        default:

                            //  System.out.println("[UNKNOWN]\t");
                    }
                }
                respenseAndPeoples.add(listtmp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
