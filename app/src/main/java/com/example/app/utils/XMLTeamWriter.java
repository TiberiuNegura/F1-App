package com.example.app.utils;

import android.content.Context;

import com.example.app.entities.Driver;
import com.example.app.entities.Team;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLTeamWriter {

    public void saveTeam(Context context, Team newTeam, String xmlFilePath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Check if file exists
            File xmlFile = new File(context.getFilesDir(), xmlFilePath);
            Document doc;
            Element rootElement;

            if (xmlFile.exists()) {
                doc = docBuilder.parse(xmlFile);
                rootElement = doc.getDocumentElement();
            } else {
                doc = docBuilder.newDocument();
                rootElement = doc.createElement("teams");
                doc.appendChild(rootElement);
            }

            // Create team element
            Element teamElement = doc.createElement("team");
            rootElement.appendChild(teamElement);

            // Add team elements
            addTextElement(doc, teamElement, "name", newTeam.getName());
            addTextElement(doc, teamElement, "image", newTeam.getImagePath());
            addTextElement(doc, teamElement, "wins", String.valueOf(newTeam.getWins()));
            addTextElement(doc, teamElement, "podiums", String.valueOf(newTeam.getPodiums()));
            addTextElement(doc, teamElement, "championships", String.valueOf(newTeam.getChampionships()));
            addTextElement(doc, teamElement, "founded_year", String.valueOf(newTeam.getFoundedYear()));
            addTextElement(doc, teamElement, "country", newTeam.getCountry());
            addTextElement(doc, teamElement, "team_principal", newTeam.getTeamPrincipal());
            addTextElement(doc, teamElement, "web_url", newTeam.getImagePath());

            // Add drivers element
            Element driversElement = doc.createElement("drivers");
            teamElement.appendChild(driversElement);

            // Add driver 1
            Element driver1Element = createDriverElement(doc, newTeam.getDriver1());
            driversElement.appendChild(driver1Element);

            // Add driver 2
            Element driver2Element = createDriverElement(doc, newTeam.getDriver2());
            driversElement.appendChild(driver2Element);

            // Write to file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);

            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Element createDriverElement(Document doc, Driver driver) {
        Element driverElement = doc.createElement("driver");
        addTextElement(doc, driverElement, "name", driver.getName());
        addTextElement(doc, driverElement, "number", String.valueOf(driver.getNumber()));
        addTextElement(doc, driverElement, "image", driver.getImagePath());
        return driverElement;
    }

    private void addTextElement(Document doc, Element parent, String tagName, String text) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(text));
        parent.appendChild(element);
    }
}
