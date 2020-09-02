package com.suraj.utils;

import com.suraj.dto.EmployeeDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileUtils {
    private static final String COMMA = ",";
    private final Function<String, EmployeeDTO> mapToItem = (line) -> {
        String[] row = line.split(COMMA);// a CSV has comma separated lines
        EmployeeDTO dto = new EmployeeDTO();
        int pointer = 0;
        dto.extractEmployeeId(row[pointer]); //<-- this is the first column in the csv file
        dto.extractEmployeeName(row[++pointer]);
        dto.extractTitle(row[++pointer]);
        dto.extractBusinessUnit(row[++pointer]);
        dto.extractPlace(row[++pointer]);
        dto.extractSupervisorID(row[++pointer]);
        dto.extractCompetencies(row[++pointer]);
        dto.extractSalary(row[++pointer]);
        return dto;
    };

    public List<EmployeeDTO> processInputFile(File inputFile) throws IOException {
        List<EmployeeDTO> inputList = new ArrayList<>();
        BufferedReader br = null;
        try {
            if (null == inputFile && !inputFile.exists()) return inputList;
            InputStream inputFS = new FileInputStream(inputFile);
            br = new BufferedReader(new InputStreamReader(inputFS));
            inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());

        } finally {
            if (null != br)
                br.close();
        }
        return inputList;
    }
}
