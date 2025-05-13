package com.lab3;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;

public class XPathParser {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("src/main/java/com/lab3/CreatedFiles/cars.xml");

        XPath xpath = XPathFactory.newInstance().newXPath();

        String tag = "model";
        String expression = "//" + tag;
        NodeList nodes = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
        System.out.println("Tag '" + tag + "' exists: " + (nodes.getLength() > 0));

        expression = "//features/*";
        nodes = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
        System.out.println("Tag 'features' has children: " + (nodes.getLength() > 0));

        expression = "//model";
        nodes = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
        System.out.println("Models:");
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getTextContent());
        }
    }
}
