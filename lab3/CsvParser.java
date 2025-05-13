package com.lab3;

import com.opencsv.CSVReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.Scanner;

public class CsvParser {
    public static void main(String[] args) throws IOException {
        // Метод: Split
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/lab3/CreatedFiles/students.csv"))) {
            String line;
            System.out.println("Split: ");
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                System.out.println(String.join(" | ", data));
            }
        }

        // Метод: OpenCSV
        try (CSVReader reader = new CSVReader(new FileReader("src/main/java/com/lab3/CreatedFiles/students.csv"))) {
            System.out.println("OpenCSV: ");
            for (String[] row : reader) {
                System.out.println(String.join(" | ", row));
            }
        }

        // Метод: Scanner
        try (Scanner scanner = new Scanner(new File("src/main/java/com/lab3/CreatedFiles/students.csv"))) {
            System.out.println("Scanner: ");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        }

        // Метод: Apache Commons CSV
        try (CSVParser parser = new CSVParser(new FileReader("src/main/java/com/lab3/CreatedFiles/students.csv"), CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            System.out.println("Commons CSV: ");
            for (CSVRecord record : parser) {
                System.out.println(record.get("id") + " | " + record.get("name") + " | " + record.get("age") + " | " + record.get("grade"));
            }
        }
    }
}