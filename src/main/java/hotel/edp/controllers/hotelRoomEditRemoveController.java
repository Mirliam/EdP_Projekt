package hotel.edp.controllers;

import com.google.common.eventbus.EventBus;
import hotel.edp.data.mySQLConnection;
import hotel.edp.events.hotelAddedEvent;
import hotel.edp.events.roomAddedEvent;
import hotel.edp.events.roomEditedEvent;
import hotel.edp.views.hotelRoomEditRemoveView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class hotelRoomEditRemoveController {
    public static Logger log = LogManager.getLogger(hotelRoomSearchController.class.getName());

    private hotelRoomSearchController roomSearchController;
    private EventBus eventBus;
    public hotelRoomEditRemoveView roomRemoveView;
    private mySQLConnection sqlConnection;
    public hotelRoomEditRemoveController(hotelRoomSearchController searchController, EventBus bus){
        this.roomSearchController = searchController;
        this.eventBus = bus;
        this.roomRemoveView = new hotelRoomEditRemoveView(this);
        updateHotelList();
        updateRoomList();
    }

    public void backButtonAction(ActionEvent event) {
        new Thread(() -> {
            try {
                roomRemoveView.setVisibility(false);
                roomSearchController.roomSearchView.setVisibility(true);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }

    public void updateHotelList(){
        new Thread(() -> {
            this.sqlConnection = mySQLConnection.getInstance();
            List<String> hotelList = this.sqlConnection.getAllHotels();
            this.roomRemoveView.clearHotelList();
            for (String hotel:hotelList) {
                this.roomRemoveView.setHotelList(hotel);
            }
        }).start();
    }

    public void removeHotelButtonAction(ActionEvent event) {
        new Thread(() -> {
            try {
                HashMap<String,String> roomData = roomRemoveView.getAllHotelData();
                this.sqlConnection.removeRoom(roomData);
                this.roomRemoveView.clearRoomList();
                updateRoomList();
                Thread.sleep(4000);
            } catch (InterruptedException | SQLException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void editRoomButtonAction(ActionEvent event) {

        new Thread(() -> {
            try {
                HashMap<String,String> roomData = roomRemoveView.getAllHotelData();
                Thread.sleep(4000);
                this.sqlConnection.updateRoomData(roomData);
                eventBus.post(new roomEditedEvent());
            } catch (InterruptedException  e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void updateRoomList() {
        new Thread(() -> {
            this.sqlConnection = mySQLConnection.getInstance();
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String hotel = this.roomRemoveView.getHotelName();
            List<String> roomList = this.sqlConnection.getHotelRooms(hotel);
            this.roomRemoveView.clearRoomList();
            for (String room:roomList) {
                this.roomRemoveView.setRoomList(room);
            }
        }).start();
    }

    public void roomComboBoxAction(ActionEvent event) {
        new Thread(() -> {
            this.sqlConnection = mySQLConnection.getInstance();
            try{
                Thread.sleep(1500);
                String hotel = this.roomRemoveView.getHotelName();
                String room = this.roomRemoveView.getRoomName();
                HashMap<String,String> roomDetails = this.sqlConnection.getRoomData(hotel,room);
                log.info(roomDetails);
                this.roomRemoveView.showRoomData(roomDetails);
            } catch (InterruptedException | NullPointerException e) {
                //log.error(e);
            }

        }).start();
    }

    public void hotelComboBoxAction(ActionEvent event){
        updateRoomList();
    }
}

