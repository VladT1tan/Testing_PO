package com.lab3;

import com.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;

public class CsvCreator {
    public static void main(String[] args) throws IOException {
        // Метод: OpenCSV
        try (CSVWriter writer = new CSVWriter(new FileWriter("students.csv"))) {
            String[] headers = {"id", "name", "age", "grade"};
            writer.writeNext(headers);
            writer.writeNext(new String[]{"1", "Максим Иванов", "20", "8.5"});
            writer.writeNext(new String[]{"2", "Степан Ефимов", "21", "7.8"});
            writer.writeNext(new String[]{"3", "Анна Сидорова", "19", "9.2"});
        }

        // Метод: Apache Commons CSV (перезапишет файл)
        try (CSVPrinter printer = new CSVPrinter(new FileWriter("students.csv"), CSVFormat.DEFAULT.withHeader("id", "name", "age", "grade"))) {
            printer.printRecord("1", "Максим Иванов", "20", "8.5");
            printer.printRecord("2", "Степан Ефимов", "21", "7.8");
            printer.printRecord("3", "Анна Сидорова", "19", "9.2");
        }
    }
}
