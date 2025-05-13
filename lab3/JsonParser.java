package com.lab3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JsonParser {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("src/main/java/com/lab3/CreatedFiles/cars.json"));

        JsonNode dealership = root.get("dealership");
        for (JsonNode brand : dealership) {
            System.out.println("Brand: " + brand.get("brandName").asText());
            JsonNode cars = brand.get("cars");
            for (JsonNode car : cars) {
                System.out.println("  Car ID: " + car.get("carId").asText() + ", Model: " + car.get("model").asText());
            }
        }
    }
}
