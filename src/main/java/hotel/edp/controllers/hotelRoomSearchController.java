package hotel.edp.controllers;

import com.google.common.eventbus.EventBus;
import hotel.edp.data.mySQLConnection;
import hotel.edp.events.minPriceHigherThanMaxEvent;
import hotel.edp.events.searchDoneEvent;
import hotel.edp.views.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class hotelRoomSearchController{
    public hotelRoomSearchView roomSearchView;
    public hotelRoomEditRemoveController removeController;
    public hotelRoomAddController addController;
    public mySQLConnection sqlConnection;
    public EventBus eventBus;
    public dialogMessagesViews dialogBoxesView;
    public searchResultsView searchResult;
    public static Logger log = LogManager.getLogger(hotelRoomSearchController.class.getName());

    public hotelRoomSearchController(){
        eventBus = new EventBus();
        this.roomSearchView = new hotelRoomSearchView(this);
        this.addController = new hotelRoomAddController(this, eventBus);
        this.removeController = new hotelRoomEditRemoveController(this,eventBus );
        this.dialogBoxesView = new dialogMessagesViews();
        updateHotelList();
        eventBus.register(removeController);
        eventBus.register(addController);
        eventBus.register(this);
        eventBus.register(dialogBoxesView);
        log.info("Program Start");
    }

    public void updateHotelList(){
        new Thread(() -> {
            this.sqlConnection = mySQLConnection.getInstance();
            List<String> hotelList = this.sqlConnection.getAllHotels();
            this.roomSearchView.clearHotelList();
            for (String hotel:hotelList) {
                this.roomSearchView.setHotelList(hotel);
            }
            log.info("Hotel list updated");
        }).start();
    }

    public void searchButtonAction(ActionEvent event){
        new Thread(() -> {
            try {
                HashMap<String,String> allData = roomSearchView.getAllHotelSearchData();
                if(Integer.parseInt(allData.get("MinPrice")) > Integer.parseInt(allData.get("MaxPrice"))) {
                    eventBus.post(new minPriceHigherThanMaxEvent());
                    return;
                }
                Thread.sleep(50);
                searchResult = new searchResultsView(sqlConnection.roomSearch(allData));
                eventBus.post(new searchDoneEvent());
            } catch (InterruptedException | SQLException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void addButtonAction(ActionEvent event){
        new Thread(() -> {
                roomSearchView.getAllHotelSearchData();
                addController.updateHotelList();
                addController.roomAddView.setVisibility(true);
                roomSearchView.setVisibility(false);
        }).start();
    }

    public void removeButtonAction(ActionEvent event){
        new Thread(() -> {
            try {
                this.removeController.roomRemoveView.setVisibility(true);
                this.roomSearchView.setVisibility(false);
                this.removeController.updateHotelList();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}

