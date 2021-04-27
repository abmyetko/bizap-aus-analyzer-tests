package com.capsilon.automation.aus.utils;

import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CSVUtils {

    public static List<CSVRecord> getRecords(String fileName, String[] headers) throws IOException {
        return getRecords(fileName, headers, ',');
    }

    public static List<CSVRecord> getRecords(String fileName, String[] headers, Character delimiter) throws IOException {
        Reader in = new FileReader(ClassLoader.getSystemResource(fileName).getFile());

        return CSVFormat.DEFAULT
            .withHeader(headers)
            .withFirstRecordAsHeader()
            .withDelimiter(delimiter)
            .parse(in)
            .getRecords();
    }

    @SneakyThrows
    public static List<CSVRecord> getDuMappingRecords(){
        String[] headers = {
                "Message Status for DU10.3", "DU/DU identifier", "MsgID", "Name", "Severity", "Category", "Category Name",
                "Type", "Lender Text", "Boiler Plate", "Doc Association", "Documents to Associate"
        };
        return getRecords("du-mapping.csv", headers, ';');
    }
}
