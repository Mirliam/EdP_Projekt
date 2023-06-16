package hotel.edp.views;

import hotel.edp.controllers.hotelRoomEditRemoveController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class hotelRoomEditRemoveView {

    private final JComboBox roomListComboBox;
    private JFrame removeRoomPanel;
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
    private JButton removeRoomButton;
    private JButton backButton;
    private hotelRoomEditRemoveController editRoomController;
    private JTextField hotelNameTextField;
    private JComboBox roomIdComboBox;
    private JButton addRoomButton;
    private JLabel peopleCountLabel;
    private JRadioButton roomStandardRB, roomPremiumRB;
    private ButtonGroup hotelStandardButtonGroup;
    private JLabel roomStandardLabel;
    private JLabel chooseHotelLabel;
    private JComboBox hotelListComboBox;
    private JLabel priceLabel;
    private JTextField priceTextField;
    public hotelRoomEditRemoveView(hotelRoomEditRemoveController editRoomC){
        this.editRoomController = editRoomC;
        this.removeRoomPanel = new JFrame("Remove Room/Hotel");
        this.removeRoomPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.removeRoomPanel.setBounds(550, 150, 800, 600);
        this.removeRoomPanel.setLayout(null);

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

        this.removeRoomButton = new JButton("Remove room");
        this.removeRoomButton.setBounds(600, 100, 150,30);
        this.removeRoomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                editRoomController.removeHotelButtonAction(event);
            }
        });

        this.backButton = new JButton("Back");
        this.backButton.setBounds(600, 500, 100 ,30);
        this.backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                editRoomController.backButtonAction(event);
            }
        });

        this.roomIdComboBox = new JComboBox();
        this.roomIdComboBox.setBounds(50,260,150,30);
        this.roomIdComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                editRoomController.roomComboBoxAction(event);
            }
        });
        this.addHotelLabel = new JLabel("Remove/Edit Hotel/room");
        this.addHotelLabel.setBounds(350,50,150,50);
        this.peopleCountLabel = new JLabel("Max people count in room:");
        this.peopleCountLabel.setBounds(300, 350, 200,30);

        this.priceLabel = new JLabel("Price:");
        this.priceLabel.setBounds(550, 230, 200,30);
        this.priceTextField = new JTextField();
        this.priceTextField.setBounds(550, 260, 100,30);

        this.addRoomButton = new JButton("Edit room");
        this.addRoomButton.setBounds(600, 350, 100, 30);
        this.addRoomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                editRoomController.editRoomButtonAction(event);
            }
        });

        this.chooseHotelLabel = new JLabel("Choose Hotel:");
        this.chooseHotelLabel.setBounds(50,150,200,50);
        this.hotelListComboBox = new JComboBox();
        this.hotelListComboBox.setBounds(50,190, 300, 30);
        this.hotelListComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                editRoomController.hotelComboBoxAction(event);
            }
        });
        this.addRoomLabel = new JLabel("Room id:");
        this.addRoomLabel.setBounds(50, 230, 100, 30);
        this.roomListComboBox = new JComboBox();
        this.roomListComboBox.setBounds(50,270, 300, 20);

        this.removeRoomPanel.add(priceLabel);
        this.removeRoomPanel.add(priceTextField);
        this.removeRoomPanel.add(roomStandardLabel);
        this.removeRoomPanel.add(roomStandardRB);
        this.removeRoomPanel.add(roomPremiumRB);
        this.removeRoomPanel.add(addHotelLabel);
        this.removeRoomPanel.add(addRoomLabel);
        this.removeRoomPanel.add(backButton);
        this.removeRoomPanel.add(removeRoomButton);
        this.removeRoomPanel.add(peopleCountSpinner);
        this.removeRoomPanel.add(amenitiesLabel);
        this.removeRoomPanel.add(barCheckBox);
        this.removeRoomPanel.add(tvCheckBox);
        this.removeRoomPanel.add(ironCheckBox);
        this.removeRoomPanel.add(breakfastCheckBox);
        this.removeRoomPanel.add(jacuzziCheckBox);
        this.removeRoomPanel.add(roomIdComboBox);
        this.removeRoomPanel.add(peopleCountLabel);
        this.removeRoomPanel.add(addRoomButton);
        this.removeRoomPanel.add(chooseHotelLabel);
        this.removeRoomPanel.add(hotelListComboBox);
    }

    public void setVisibility(boolean visibility){
        this.removeRoomPanel.setVisible(visibility);
    }

    public void setHotelList(String hotelName){
        this.hotelListComboBox.addItem(hotelName);
    }

    public void clearHotelList(){
        this.hotelListComboBox.removeAllItems();
    }

    public String getHotelName(){
        return this.hotelListComboBox.getSelectedItem().toString();
    }

    public HashMap<String, String> getAllHotelData(){
        HashMap<String, String> filledData = new HashMap<>();


        filledData.put("Price", this.priceTextField.getText());
        filledData.put("HotelName", this.hotelListComboBox.getSelectedItem().toString());
        if (this.roomStandardRB.isSelected())
            filledData.put("RoomStandard", "Standard");
        else
            filledData.put("RoomStandard", "Premium");

        filledData.put("PeopleCount", this.peopleCountSpinner.getValue().toString());
        filledData.put("RoomId", this.roomIdComboBox.getSelectedItem().toString());
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

    public void clearRoomList() {
        this.roomIdComboBox.removeAllItems();
    }
    public void setRoomList(String roomName){
        this.roomIdComboBox.addItem(roomName);
    }

    public String getRoomName() {
        return this.roomIdComboBox.getSelectedItem().toString();
    }

    public void showRoomData(HashMap<String, String> roomDetails) {
        roomDetails.get("");
        this.priceTextField.setText(roomDetails.get("Price"));

        if (roomDetails.get("RoomStandard").equals("Premium"))
            this.roomPremiumRB.setSelected(true);
        else
            this.roomStandardRB.setSelected(true);

        this.peopleCountSpinner.setValue(Integer.parseInt(roomDetails.get("MaxPeople")));
        if (roomDetails.get("Bar").equals("1"))
            this.barCheckBox.setSelected(true);
        else
            this.barCheckBox.setSelected(false);

        if (roomDetails.get("Tv").equals("1"))
            this.tvCheckBox.setSelected(true);
        else
            this.tvCheckBox.setSelected(false);

        if (roomDetails.get("Breakfast").equals("1"))
            this.breakfastCheckBox.setSelected(true);
        else
            this.breakfastCheckBox.setSelected(false);

        if (roomDetails.get("Iron").equals("1"))
            this.ironCheckBox.setSelected(true);
        else
            this.ironCheckBox.setSelected(false);

        if (roomDetails.get("Jacuzzi").equals("1"))
            this.jacuzziCheckBox.setSelected(true);
        else
            this.jacuzziCheckBox.setSelected(false);
    }
}
