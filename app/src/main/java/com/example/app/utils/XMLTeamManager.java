package com.example.app.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.app.TeamAdapter;
import com.example.app.entities.Team;
import com.example.app.entities.Driver;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLTeamManager {
    private static ArrayList<Team> teams = new ArrayList<>();

    public XMLTeamManager() {

    }

    public static void readXml(Context context, String file) {
        try {
            File xmlFile = new File(context.getFilesDir(), "extra_teams.xml");

            if(xmlFile.exists()) {
                InputStream extraTeamsStream = new FileInputStream(xmlFile);
                DocumentBuilder builderExtraTeams = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document xmlExtraTeams = builderExtraTeams.parse(extraTeamsStream);
                teams = readTeams(xmlExtraTeams);
            }

            if(!xmlFile.exists() || teams.isEmpty() ) {
                AssetManager assetManager = context.getAssets();
                InputStream stream = assetManager.open(file);
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document xmlDoc = builder.parse(stream);

                teams = readTeams(xmlDoc);

                stream.close();
            }
        } catch (Exception e) {
            Log.d("DEBUG++>", "CANNOT PARSE: " + e.getMessage());
        }
    }

    public static void addTeam(Team newTeam, Context context, String file) {
        try {
            File xmlFile = new File(context.getFilesDir(), "extra_teams.xml");
            Document doc;

            if(!xmlFile.exists()) {
                AssetManager assetManager = context.getAssets();
                InputStream stream = assetManager.open(file);
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                doc = builder.parse(stream);
                stream.close();
            } else {
                InputStream extraTeamsStream = new FileInputStream(xmlFile);
                DocumentBuilder builderExtraTeams = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                doc = builderExtraTeams.parse(extraTeamsStream);

                extraTeamsStream.close();
            }

            Element root = doc.getDocumentElement();

            Element teamElement = doc.createElement("team");

            addChildElement(doc, teamElement, "name", newTeam.getName());
            addChildElement(doc, teamElement, "image", newTeam.getImagePath());
            addChildElement(doc, teamElement, "wins", String.valueOf(newTeam.getWins()));
            addChildElement(doc, teamElement, "podiums", String.valueOf(newTeam.getPodiums()));
            addChildElement(doc, teamElement, "championships", String.valueOf(newTeam.getChampionships()));
            addChildElement(doc, teamElement, "founded_year", String.valueOf(newTeam.getFoundedYear()));
            addChildElement(doc, teamElement, "country", newTeam.getCountry());
            addChildElement(doc, teamElement, "team_principal", newTeam.getTeamPrincipal());
            addChildElement(doc, teamElement, "web_url", newTeam.getUrl());

            ArrayList<Driver> drivers = new ArrayList<Driver>();
            drivers.add(newTeam.getDriver1());
            drivers.add(newTeam.getDriver2());

            Element driversElement = doc.createElement("drivers");
            for (Driver driver : drivers) {
                Element driverElement = doc.createElement("driver");
                addChildElement(doc, driverElement, "name", driver.getName());
                addChildElement(doc, driverElement, "number", String.valueOf(driver.getNumber()));
                addChildElement(doc, driverElement, "image", driver.getImagePath());
                driversElement.appendChild(driverElement);
            }

            teamElement.appendChild(driversElement);

            root.appendChild(teamElement);

            File outputFile = new File(context.getFilesDir(), "extra_teams.xml");
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(
                    new DOMSource(doc),
                    new StreamResult(outputFile)
            );

            teams.add(newTeam);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editTeam(Team currentTeam, Team newTeam, Context context) {
        try {
            for (int i = 0; i < teams.size(); i++) {
                if (teams.get(i).getName().equals(currentTeam.getName())) {
                    teams.remove(i);
                    break;
                }
            }
            addTeam(newTeam, context, "teams.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addChildElement(Document doc, Element parent, String tagName, String value) {
        Element child = doc.createElement(tagName);
        child.appendChild(doc.createTextNode(value));
        parent.appendChild(child);
    }

    private static ArrayList<Team> readTeams(Document doc) {
        // Get all the team elements
        NodeList teamNodes = doc.getElementsByTagName("team");

        ArrayList<Team> newTeams = new ArrayList<Team>();

        // Loop through each team node
        for (int i = 0; i < teamNodes.getLength(); i++) {
            Element teamElement = (Element) teamNodes.item(i);

            // Extract basic team info
            String name = teamElement.getElementsByTagName("name").item(0).getTextContent();
            String imagePath = teamElement.getElementsByTagName("image").item(0).getTextContent();
            int wins = Integer.parseInt(teamElement.getElementsByTagName("wins").item(0).getTextContent());
            int podiums = Integer.parseInt(teamElement.getElementsByTagName("podiums").item(0).getTextContent());
            int championships = Integer.parseInt(teamElement.getElementsByTagName("championships").item(0).getTextContent());
            int foundedYear = Integer.parseInt(teamElement.getElementsByTagName("founded_year").item(0).getTextContent());
            String teamPrincipal = teamElement.getElementsByTagName("team_principal").item(0).getTextContent();
            String url = teamElement.getElementsByTagName("web_url").item(0).getTextContent();
            String country = teamElement.getElementsByTagName("country").item(0).getTextContent();



            // Get driver nodes (assuming there are exactly two drivers per team)
            NodeList driverNodes = teamElement.getElementsByTagName("driver");

            // Parse driver 1
            Element driver1Element = (Element) driverNodes.item(0);
            String driver1Name = driver1Element.getElementsByTagName("name").item(0).getTextContent();
            int driver1Number = Integer.parseInt(driver1Element.getElementsByTagName("number").item(0).getTextContent());
            String driver1Image = driver1Element.getElementsByTagName("image").item(0).getTextContent();
            Driver driver1 = new Driver(driver1Name, driver1Number, driver1Image);

            // Parse driver 2
            Element driver2Element = (Element) driverNodes.item(1);
            String driver2Name = driver2Element.getElementsByTagName("name").item(0).getTextContent();
            int driver2Number = Integer.parseInt(driver2Element.getElementsByTagName("number").item(0).getTextContent());
            String driver2Image = driver2Element.getElementsByTagName("image").item(0).getTextContent();
            Driver driver2 = new Driver(driver2Name, driver2Number, driver2Image);

            // Create the Team object with all parsed values and add it to the list
            Team team = new Team(name, imagePath, wins, podiums, championships, foundedYear, teamPrincipal, driver1, driver2, url, country);
            newTeams.add(team);
        }

        return newTeams;
    }

    public static void saveTeams(List<Team> teamsList, Context context) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("teams");
            doc.appendChild(root);

            for (Team team : teamsList) {
                Element teamElement = doc.createElement("team");

                addChildElement(doc, teamElement, "name", team.getName());
                addChildElement(doc, teamElement, "image", team.getImagePath());
                addChildElement(doc, teamElement, "wins", String.valueOf(team.getWins()));
                addChildElement(doc, teamElement, "podiums", String.valueOf(team.getPodiums()));
                addChildElement(doc, teamElement, "championships", String.valueOf(team.getChampionships()));
                addChildElement(doc, teamElement, "founded_year", String.valueOf(team.getFoundedYear()));
                addChildElement(doc, teamElement, "country", team.getCountry());
                addChildElement(doc, teamElement, "team_principal", team.getTeamPrincipal());
                addChildElement(doc, teamElement, "web_url", team.getUrl());

                Element driversElement = doc.createElement("drivers");
                for (Driver driver : new Driver[]{ team.getDriver1(), team.getDriver2() }) {
                    Element driverElement = doc.createElement("driver");
                    addChildElement(doc, driverElement, "name", driver.getName());
                    addChildElement(doc, driverElement, "number", String.valueOf(driver.getNumber()));
                    addChildElement(doc, driverElement, "image", driver.getImagePath());
                    driversElement.appendChild(driverElement);
                }
                teamElement.appendChild(driversElement);
                root.appendChild(teamElement);
            }

            File outputFile = new File(context.getFilesDir(), "extra_teams.xml");
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(outputFile));

            //teams.clear();
            //teams.addAll(teamsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Team> getTeams() {
        return teams;
    }
}

