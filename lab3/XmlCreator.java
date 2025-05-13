package com.lab3;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;

public class XmlCreator {
    public static void main(String[] args) throws IOException {
        Element dealership = new Element("dealership");

        // Toyota brand
        Element toyota = new Element("brand");
        toyota.setAttribute("brandName", "Toyota");
        toyota.setAttribute("brandId", "1");

        Element camry = new Element("car");
        camry.setAttribute("carId", "001");
        camry.addContent(new Element("model").setText("Camry"));
        camry.addContent(new Element("year").setText("2020"));
        camry.addContent(new Element("price").setText("25000"));
        Element features1 = new Element("features");
        features1.addContent(new Element("feature").setText("Hybrid"));
        features1.addContent(new Element("feature").setText("Navigation"));
        camry.addContent(features1);
        camry.addContent(new Element("dealerId").setText("0"));
        toyota.addContent(camry);

        Element corolla = new Element("car");
        corolla.setAttribute("carId", "002");
        corolla.addContent(new Element("model").setText("Corolla"));
        corolla.addContent(new Element("year").setText("2021"));
        corolla.addContent(new Element("price").setText("20000"));
        Element features2 = new Element("features");
        features2.addContent(new Element("feature").setText("Compact"));
        features2.addContent(new Element("feature").setText("Bluetooth"));
        corolla.addContent(features2);
        corolla.addContent(new Element("dealerId").setText("001"));
        toyota.addContent(corolla);

        // Honda brand
        Element honda = new Element("brand");
        honda.setAttribute("brandName", "Honda");
        honda.setAttribute("brandId", "2");

        Element civic = new Element("car");
        civic.setAttribute("carId", "003");
        civic.addContent(new Element("model").setText("Civic"));
        civic.addContent(new Element("year").setText("2019"));
        civic.addContent(new Element("price").setText("18000"));
        Element features3 = new Element("features");
        features3.addContent(new Element("feature").setText("Turbo"));
        features3.addContent(new Element("feature").setText("Sunroof"));
        civic.addContent(features3);
        civic.addContent(new Element("dealerId").setText("0"));
        honda.addContent(civic);

        Element accord = new Element("car");
        accord.setAttribute("carId", "004");
        accord.addContent(new Element("model").setText("Accord"));
        accord.addContent(new Element("year").setText("2022"));
        accord.addContent(new Element("price").setText("28000"));
        Element features4 = new Element("features");
        features4.addContent(new Element("feature").setText("Leather"));
        features4.addContent(new Element("feature").setText("Backup Camera"));
        accord.addContent(features4);
        accord.addContent(new Element("dealerId").setText("003"));
        honda.addContent(accord);

        dealership.addContent(toyota);
        dealership.addContent(honda);

        Document doc = new Document(dealership);
        XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
        xmlOutput.output(doc, new FileWriter("cars.xml"));
    }
}
