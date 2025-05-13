package com.lab3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;

public class JsonCreator {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        ArrayNode dealership = mapper.createArrayNode();

        ObjectNode toyota = mapper.createObjectNode();
        toyota.put("brandName", "Toyota");
        toyota.put("brandId", "1");
        ArrayNode toyotaCars = mapper.createArrayNode();
        ObjectNode camry = mapper.createObjectNode();
        camry.put("carId", "001");
        camry.put("model", "Camry");
        camry.put("year", 2020);
        camry.put("price", 25000);
        ArrayNode features1 = mapper.createArrayNode();
        features1.add("Hybrid");
        features1.add("Navigation");
        camry.set("features", features1);
        camry.put("dealerId", "0");
        toyotaCars.add(camry);
        toyota.set("cars", toyotaCars);

        dealership.add(toyota);
        root.set("dealership", dealership);

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("cars.json"), root);
    }
}
