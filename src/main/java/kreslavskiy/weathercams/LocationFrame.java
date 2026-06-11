package kreslavskiy.weathercams;

import kreslavskiy.weathercams.windy.WindyService;
import kreslavskiy.weathercams.windy.WindyServiceFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class LocationFrame extends JFrame
{
    private final JPanel imagePanel1;
    private final JPanel imagePanel2;
    private final JTextField searchbar;
    private final JLabel latitudeLabel;
    private final JLabel longitudeLabel;
    private final JLabel tempLabel;
    private final JLabel feelsLikeLabel;
    private final JLabel descriptionLabel;

    public LocationFrame()
    {
        setSize(600, 800);
        setTitle("Weather App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GridBagConstraints constraints;

        constraints = new  GridBagConstraints();
        constraints.gridx = 4;
        constraints.gridy = 0;
        constraints.gridheight = 7;
        imagePanel1 = new JPanel();
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.add(imagePanel1, constraints);

        constraints = new  GridBagConstraints();
        constraints.gridx = 4;
        constraints.gridy = 8;
        imagePanel2 = new JPanel();
        rightPanel.add(imagePanel2, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        searchbar = new JTextField("Passaic");

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.add(searchbar, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 0;
        JButton searchButton = new JButton("Search");
        leftPanel.add(searchButton, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 1;
        latitudeLabel = new JLabel("Latitude: ");
        leftPanel.add(latitudeLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 1;
        JLabel latitude = new JLabel();
        leftPanel.add(latitude, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 2;
        longitudeLabel = new JLabel("Longitude: ");
        leftPanel.add(longitudeLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 2;
        JLabel longitude = new JLabel();
        leftPanel.add(longitude, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 3;
        tempLabel = new JLabel("Temperature (F): ");
        leftPanel.add(tempLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 3;
        JLabel temp = new JLabel();
        leftPanel.add(temp, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 4;
        feelsLikeLabel = new JLabel("Feels Like: ");
        leftPanel.add(feelsLikeLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 4;
        JLabel feelsLike = new JLabel();
        leftPanel.add(feelsLike, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 5;
        descriptionLabel = new JLabel("Description: ");
        leftPanel.add(descriptionLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 5;
        JLabel description = new JLabel();
        leftPanel.add(description, constraints);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        OpenweathermapService owmService = new OpenweathermapServiceFactory().create();
        WindyService windyService = new WindyServiceFactory().create();
        LocationController locationController = new LocationController(owmService, windyService, imagePanel1,
                                                                        imagePanel2, searchbar, latitude,
                                                                        longitude, temp, feelsLike, description);

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
