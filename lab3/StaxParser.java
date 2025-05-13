package com.lab3;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

public class StaxParser {
    public static void main(String[] args) throws Exception {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream("src/main/java/com/lab3/CreatedFiles/cars.xml"));

        while (reader.hasNext()) {
            if (reader.isStartElement() && reader.getLocalName().equals("car")) {
                String carId = reader.getAttributeValue(null, "carId");
                System.out.println("Car ID: " + carId);
                if (carId.equals("002")) {
                    System.out.println("Found car with carId=002");
                }
            }
            reader.next();
        }
        reader.close();
    }
}
