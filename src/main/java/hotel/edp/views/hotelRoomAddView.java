package hotel.edp.views;

import hotel.edp.controllers.hotelRoomAddController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class hotelRoomAddView implements ActionListener {
    private JFrame addRoomPanel;
    private JLabel addHotelLabel;
    private JLabel hotelNameLabel;
    private JLabel addRoomLabel;
    private JSpinner peopleCountSpinner;
    private JLabel amenitiesLabel;
    private JCheckBox barCheckBox;
    private JCheckBox tvCheckBox;
    private JCheckBox breakfastCheckBox;
    private JCheckBox ironCheckBox;
    private JCheckBox jacuzziCheckBox;
    private JButton addHotelButton;
    private JButton backButton;
    private hotelRoomAddController addRoomController;
    private JTextField hotelNameTextField;
    private JTextField roomIdTexField;
    private JButton addRoomButton;
    private JLabel peopleCountLabel;
    private JRadioButton roomStandardRB, roomPremiumRB;
    private ButtonGroup hotelStandardButtonGroup;
    private JLabel roomStandardLabel;
    private JLabel chooseHotelLabel;
    private JComboBox hotelListComboBox;
    private JLabel priceLabel;
    private JTextField priceTextField;

    public hotelRoomAddView(hotelRoomAddController controller){

        addRoomController = controller;
        this.addRoomPanel = new JFrame("Add Room/Hotel");
        this.addRoomPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addRoomPanel.setBounds(550, 150, 800, 600);
        this.addRoomPanel.setLayout(null);

        this.roomStandardLabel = new JLabel("Room standard");
        this.roomStandardLabel.setBounds(300, 230, 150, 30);
        this.roomPremiumRB = new JRadioButton("Premium");
        this.roomStandardRB = new JRadioButton("Standard");
        this.roomStandardRB.setBounds(300, 260, 100,30);
        this.roomPremiumRB.setBounds(300, 280,100,30);
        this.roomStandardRB.setSelected(true);
        hotelStandardButtonGroup = new ButtonGroup();
        hotelStandardButtonGroup.add(roomStandardRB);
        hotelStandardButtonGroup.add(roomPremiumRB);

        this.amenitiesLabel = new JLabel("Amenities");
        this.amenitiesLabel.setBounds(50, 350, 100, 25);
        this.barCheckBox = new JCheckBox("Bar", false);
        this.barCheckBox.setBounds(50, 375, 80, 25);
        this.tvCheckBox = new JCheckBox("TV", false);
        this.tvCheckBox.setBounds(50, 400, 80, 25);
        this.breakfastCheckBox = new JCheckBox("Breakfast", false);
        this.breakfastCheckBox.setBounds(50, 425,100,25);
        this.ironCheckBox = new JCheckBox("Iron", false);
        this.ironCheckBox.setBounds(140, 375, 100,25);
        this.jacuzziCheckBox = new JCheckBox("Jacuzzi", false);
        this.jacuzziCheckBox.setBounds(140, 400, 100,25);

        SpinnerModel model = new SpinnerNumberModel(1, 1, 8, 1);
        this.peopleCountSpinner = new JSpinner(model);
        this.peopleCountSpinner.setBounds(400, 380,50,30);

        this.addHotelButton = new JButton("Add Hotel");
        this.addHotelButton.setBounds(600, 100, 100,30);
        this.addHotelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                addRoomController.addHotelButtonAction(event);
            }
        });

        this.backButton = new JButton("Back");
        this.backButton.setBounds(600, 500, 100 ,30);
        this.backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                addRoomController.backButtonAction(event);
            }
        });

        this.roomIdTexField = new JTextField();
        this.roomIdTexField.setBounds(50,260,150,30);
        this.addHotelLabel = new JLabel("Add Hotel/room");
        this.addHotelLabel.setBounds(350,50,150,50);
        this.hotelNameLabel = new JLabel("Hotel name:");
        this.hotelNameLabel.setBounds(50, 100, 100,30);
        this.hotelNameTextField = new JTextField();
        this.hotelNameTextField.setBounds(50, 130, 150, 30);
        this.addRoomLabel = new JLabel("Room id:");
        this.addRoomLabel.setBounds(50, 230, 100, 30);
        this.peopleCountLabel = new JLabel("Max people count in room:");
        this.peopleCountLabel.setBounds(300, 350, 200,30);

        this.priceLabel = new JLabel("Price:");
        this.priceLabel.setBounds(550, 230, 200,30);
        this.priceTextField = new JTextField();
        this.priceTextField.setBounds(550, 260, 100,30);

        this.addRoomButton = new JButton("Add room");
        this.addRoomButton.setBounds(600, 350, 100, 30);
        this.addRoomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                addRoomController.addRoomButtonAction(event);
            }
        });

        this.chooseHotelLabel = new JLabel("Choose hotel:");
        this.chooseHotelLabel.setBounds(50,450,200,50);
        this.hotelListComboBox = new JComboBox();
        this.hotelListComboBox.setBounds(50,490, 300, 20);
        clearHotelList();

        this.addRoomPanel.add(priceLabel);
        this.addRoomPanel.add(priceTextField);
        this.addRoomPanel.add(roomStandardLabel);
        this.addRoomPanel.add(roomStandardRB);
        this.addRoomPanel.add(roomPremiumRB);
        this.addRoomPanel.add(addHotelLabel);
        this.addRoomPanel.add(hotelNameLabel);
        this.addRoomPanel.add(addRoomLabel);
        this.addRoomPanel.add(backButton);
        this.addRoomPanel.add(addHotelButton);
        this.addRoomPanel.add(peopleCountSpinner);
        this.addRoomPanel.add(amenitiesLabel);
        this.addRoomPanel.add(barCheckBox);
        this.addRoomPanel.add(tvCheckBox);
        this.addRoomPanel.add(ironCheckBox);
        this.addRoomPanel.add(breakfastCheckBox);
        this.addRoomPanel.add(jacuzziCheckBox);
        this.addRoomPanel.add(roomIdTexField);
        this.addRoomPanel.add(hotelNameTextField);
        this.addRoomPanel.add(peopleCountLabel);
        this.addRoomPanel.add(addRoomButton);
        this.addRoomPanel.add(chooseHotelLabel);
        this.addRoomPanel.add(hotelListComboBox);
    }

    public void setHotelList(String hotelName){
        this.hotelListComboBox.addItem(hotelName);
    }

    public void clearHotelList(){
        this.hotelListComboBox.removeAllItems();
    }

    public String getNewHotelName(){
        return this.hotelNameTextField.getText();
    }
    public HashMap<String, String> getAllHotelAddData(){
        HashMap<String, String> filledData = new HashMap<>();

        filledData.put("RoomId", this.roomIdTexField.getText());
        filledData.put("Price", this.priceTextField.getText());
        filledData.put("HotelName",this.hotelListComboBox.getSelectedItem().toString());
        if (this.roomStandardRB.isSelected())
            filledData.put("RoomStandard", "Standard");
        else
            filledData.put("RoomStandard", "Premium");

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

        return filledData;
    }

    public void setVisibility(boolean visibility){
        this.addRoomPanel.setVisible(visibility);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
