package hotel.edp.views;

import hotel.edp.controllers.hotelRoomSearchController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class hotelRoomSearchView {

    private JFrame mainPanelHotelSearch;
    private JLabel hotelRoomSearchLabel;
    private JLabel chooseHotelLabel;
    private JComboBox hotelListComboBox;
    private JSpinner peopleCountSpinner;
    private JLabel peopleCountLabel;
    private JButton searchRoomButton;
    private JButton addHotelOrRoomButton;
    private JButton removeHotelOrRoomButton;
    private JLabel minPriceLabel;
    private JTextField minPriceTextBox;
    private JLabel maxPriceLabel;
    private JTextField maxPriceTextBox;
    private JLabel amenitiesLabel;
    private JCheckBox barCheckBox;
    private JCheckBox tvCheckBox;
    private JCheckBox breakfastCheckBox;
    private JCheckBox ironCheckBox;
    private JCheckBox jacuzziCheckBox;
    private hotelRoomSearchController roomSearchController;
    private JRadioButton roomStandardRB, roomPremiumRB;
    private ButtonGroup hotelStandardButtonGroup;
    private JLabel roomStandardLabel;


    public hotelRoomSearchView(hotelRoomSearchController searchController){

        roomSearchController = searchController;

        this.mainPanelHotelSearch = new JFrame("Hotel room search");
        this.mainPanelHotelSearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainPanelHotelSearch.setBounds(550, 150, 800, 600);
        this.mainPanelHotelSearch.setLayout(null);

        this.hotelRoomSearchLabel = new JLabel("Hotel room search");
        this.hotelRoomSearchLabel.setBounds(300,10,200,50);
        this.hotelRoomSearchLabel.setFont(new Font("Verdana", Font.PLAIN, 20));

        this.peopleCountLabel = new JLabel("People count");
        this.peopleCountLabel.setBounds(600,100, 200,50 );

        SpinnerModel model = new SpinnerNumberModel(1, 1, 8, 1);
        this.peopleCountSpinner = new JSpinner(model);
        this.peopleCountSpinner.setBounds(600, 150,50,30);

        this.chooseHotelLabel = new JLabel("Choose hotel:");
        this.chooseHotelLabel.setBounds(50,100,200,50);

        this.hotelListComboBox = new JComboBox();
        this.hotelListComboBox.setBounds(50,150, 300, 20);

        this.searchRoomButton = new JButton("Search rooms");
        this.searchRoomButton.setBounds(275, 450, 200, 30);
        this.searchRoomButton.addActionListener(event -> roomSearchController.searchButtonAction(event));

        this.addHotelOrRoomButton = new JButton("Add Room/Hotel");
        this.addHotelOrRoomButton.setBounds(600,450, 160, 30);
        this.addHotelOrRoomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                roomSearchController.addButtonAction(event);
            }
        });
        this.removeHotelOrRoomButton = new JButton("Remove Room/Hotel");
        this.removeHotelOrRoomButton.setBounds(600, 500, 160, 30);
        this.removeHotelOrRoomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                roomSearchController.removeButtonAction(event);
            }
        });

        this.minPriceLabel = new JLabel("Min price:");
        this.minPriceLabel.setBounds(50, 170, 100, 50);

        this.minPriceTextBox = new JTextField();
        this.minPriceTextBox.setBounds(50,220,200,25);

        this.maxPriceLabel = new JLabel("Max price:");
        this.maxPriceLabel.setBounds(350, 170, 100, 50);

        this.maxPriceTextBox = new JTextField();
        this.maxPriceTextBox.setBounds(350, 220, 200, 25 );

        this.amenitiesLabel = new JLabel("Amenities");
        this.amenitiesLabel.setBounds(50, 250, 100, 25);
        this.barCheckBox = new JCheckBox("Bar", false);
        this.barCheckBox.setBounds(50, 275, 80, 25);
        this.tvCheckBox = new JCheckBox("TV", false);
        this.tvCheckBox.setBounds(50, 300, 80, 25);
        this.breakfastCheckBox = new JCheckBox("Breakfast", false);
        this.breakfastCheckBox.setBounds(50, 325,100,25);
        this.ironCheckBox = new JCheckBox("Iron", false);
        this.ironCheckBox.setBounds(140, 275, 100,25);
        this.jacuzziCheckBox = new JCheckBox("Jacuzzi", false);
        this.jacuzziCheckBox.setBounds(140, 300, 100,25);

        this.roomStandardLabel = new JLabel("Room standard");
        this.roomStandardLabel.setBounds(300, 250, 150, 30);
        this.roomPremiumRB = new JRadioButton("Premium");
        this.roomStandardRB = new JRadioButton("Standard");
        this.roomStandardRB.setBounds(300, 270, 100,30);
        this.roomPremiumRB.setBounds(300, 290,100,30);
        this.roomStandardRB.setSelected(true);
        hotelStandardButtonGroup = new ButtonGroup();
        hotelStandardButtonGroup.add(roomStandardRB);
        hotelStandardButtonGroup.add(roomPremiumRB);

        this.mainPanelHotelSearch.add(roomStandardLabel);
        this.mainPanelHotelSearch.add(roomStandardRB);
        this.mainPanelHotelSearch.add(roomPremiumRB);
        this.mainPanelHotelSearch.add(hotelRoomSearchLabel);
        this.mainPanelHotelSearch.add(chooseHotelLabel);
        this.mainPanelHotelSearch.add(hotelListComboBox);
        this.mainPanelHotelSearch.add(searchRoomButton);
        this.mainPanelHotelSearch.add(addHotelOrRoomButton);
        this.mainPanelHotelSearch.add(removeHotelOrRoomButton);
        this.mainPanelHotelSearch.add(peopleCountSpinner);
        this.mainPanelHotelSearch.add(peopleCountLabel);
        this.mainPanelHotelSearch.add(maxPriceLabel);
        this.mainPanelHotelSearch.add(minPriceLabel);
        this.mainPanelHotelSearch.add(maxPriceTextBox);
        this.mainPanelHotelSearch.add(minPriceTextBox);
        this.mainPanelHotelSearch.add(amenitiesLabel);
        this.mainPanelHotelSearch.add(barCheckBox);
        this.mainPanelHotelSearch.add(tvCheckBox);
        this.mainPanelHotelSearch.add(ironCheckBox);
        this.mainPanelHotelSearch.add(breakfastCheckBox);
        this.mainPanelHotelSearch.add(jacuzziCheckBox);

        setVisibility(true);
        clearHotelList();
        getAllHotelSearchData();
    }

    public void setVisibility(boolean visibility){
        this.mainPanelHotelSearch.setVisible(visibility);
    }

    public void setHotelList(String hotelName){
        this.hotelListComboBox.addItem(hotelName);
    }

    public void clearHotelList(){
        this.hotelListComboBox.removeAllItems();
        this.hotelListComboBox.addItem("All");
    }

    public HashMap<String, String> getAllHotelSearchData(){
        HashMap<String, String> filledData = new HashMap<String, String>();
        filledData.put("HotelName",this.hotelListComboBox.getSelectedItem().toString());
        filledData.put("MinPrice", this.minPriceTextBox.getText());
        filledData.put("MaxPrice", this.maxPriceTextBox.getText());
        filledData.put("PeopleCount", this.peopleCountSpinner.getValue().toString());
        if(this.barCheckBox.isSelected())
            filledData.put("Bar","1");
        else
            filledData.put("Bar","0");

        if(this.tvCheckBox.isSelected())
            filledData.put("Tv","1");
        else
            filledData.put("Tv","0");

        if(this.breakfastCheckBox.isSelected())
            filledData.put("Breakfast","1");
        else
            filledData.put("Breakfast","0");

        if(this.ironCheckBox.isSelected())
            filledData.put("Iron","1");
        else
            filledData.put("Iron","0");

        if(this.jacuzziCheckBox.isSelected())
            filledData.put("Jacuzzi","1");
        else
            filledData.put("Jacuzzi","0");

        if (this.roomStandardRB.isSelected())
            filledData.put("RoomStandard", "Standard");
        else
            filledData.put("RoomStandard", "Premium");

        return filledData;
    }
}
