package com.lab3;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;

public class DomParser {
    public static void main(String[] args) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(new File("src/main/java/com/lab3/CreatedFiles/cars.xml"));

        // Парсинг
        Element root = doc.getRootElement();
        for (Element brand : root.getChildren("brand")) {
            System.out.println("Brand: " + brand.getAttributeValue("brandName"));
            for (Element car : brand.getChildren("car")) {
                System.out.println("  Car ID: " + car.getAttributeValue("carId") + ", Model: " + car.getChildText("model"));
            }
        }

        // Запрос по carId
        String carId = "002";
        for (Element brand : root.getChildren("brand")) {
            for (Element car : brand.getChildren("car")) {
                if (car.getAttributeValue("carId").equals(carId)) {
                    System.out.println("Found car: " + car.getChildText("model"));
                }
            }
        }
    }
}
