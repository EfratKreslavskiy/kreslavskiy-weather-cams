package kreslavskiy.weathercams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class LocationFrame extends JFrame
{
    private final JTextField searchbar;
    private final JLabel latitudeLabel;
    private final JLabel longitudeLabel;

    public LocationFrame()
    {
        setSize(600, 800);
        setTitle("Weather App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints;

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        searchbar = new JTextField("Passaic");
        add(searchbar, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 0;
        JButton searchButton =  new JButton("Search");
        add(searchButton, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 1;
        latitudeLabel = new JLabel("Latitude: ");
        add(latitudeLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 1;
        JLabel latitude = new JLabel();
        add(latitude, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 2;
        longitudeLabel = new JLabel("Longitude: ");
        add(longitudeLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 2;
        JLabel longitude = new JLabel();
        add(longitude, constraints);

        OpenweathermapService owmService = new OpenweathermapServiceFactory().create();
        LocationController locationController = new LocationController(owmService, searchbar, latitude, longitude);

        searchButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                locationController.doSearch();
            }
        });
    }

    static void main(String[] args)
    {
        LocationFrame frame = new LocationFrame();
        frame.setVisible(true);
    }
}
