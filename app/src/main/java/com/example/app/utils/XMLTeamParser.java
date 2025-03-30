package com.example.app.utils;

import android.content.Context;
import android.util.Log;

import com.example.app.R;
import com.example.app.entities.Team;
import com.example.app.entities.Driver;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLTeamParser {
    private ArrayList<Team> teams = new ArrayList<>();

    public XMLTeamParser(Context context) {
        try {
            // Open the XML file from raw resources (make sure it's named 'teams.xml' and placed in res/raw)
            InputStream stream = context.getResources().openRawResource(R.raw.input);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document xmlDoc = builder.parse(stream);

            // Get all the team elements
            NodeList teamNodes = xmlDoc.getElementsByTagName("team");

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
                teams.add(team);
            }
        } catch (Exception e) {
            Log.d("DEBUG++>", "CANNOT PARSE: " + e.getMessage());
        }
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }
}

