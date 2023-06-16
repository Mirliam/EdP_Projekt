package hotel.edp.controllers;

import com.google.common.eventbus.EventBus;
import hotel.edp.data.mySQLConnection;
import hotel.edp.events.hotelAddedEvent;
import hotel.edp.events.roomAddedEvent;
import hotel.edp.views.hotelRoomAddView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class hotelRoomAddController {

    public hotelRoomSearchController roomSearchController;
    public hotelRoomAddView roomAddView;
    private mySQLConnection sqlConnection;
    private final EventBus eventBus;

    public hotelRoomAddController(hotelRoomSearchController searchController, EventBus bus){
        this.roomSearchController = searchController;
        this.roomAddView = new hotelRoomAddView(this);
        eventBus = bus;
    }

    public void backButtonAction(ActionEvent event) {
        new Thread(() -> {
            try {
                roomAddView.setVisibility(false);
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
            this.roomAddView.clearHotelList();
            for (String hotel:hotelList) {
                this.roomAddView.setHotelList(hotel);
            }
        }).start();
    }

    public void addHotelButtonAction(ActionEvent event) {
        new Thread(() -> {
            try {
                String hotel = this.roomAddView.getNewHotelName();
                this.sqlConnection.newHotel(hotel);
                this.roomAddView.clearHotelList();
                updateHotelList();
                Thread.sleep(4000);
                this.roomSearchController.updateHotelList();
                eventBus.post(new hotelAddedEvent(hotel));
            } catch (InterruptedException | SQLException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void addRoomButtonAction(ActionEvent event) {

        new Thread(() -> {
            try {
                HashMap<String,String> roomData = roomAddView.getAllHotelAddData();
                Thread.sleep(4000);
                this.sqlConnection.newRoom(roomData);
                eventBus.post(new roomAddedEvent(roomData.get("RoomId"),roomData.get("HotelName")));
            } catch (InterruptedException | SQLException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
