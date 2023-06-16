package hotel.edp.views;

import com.google.common.eventbus.Subscribe;
import hotel.edp.events.*;

import javax.swing.*;

public class dialogMessagesViews {

    public dialogMessagesViews(){}
    @Subscribe
    public void onHotelAddedEvent(hotelAddedEvent event){
        JOptionPane.showMessageDialog(null, "New Hotel " + event.hotelName + " added!");
    }

    @Subscribe
    public void onHotelRemovedEvent(hotelRemovedEvent event){
        JOptionPane.showMessageDialog(null, "Hotel " + event.hotelName + " removed!");
    }

    @Subscribe
    public void onRoomAddedEvent(roomAddedEvent event){
        JOptionPane.showMessageDialog(null, "Room " + event.roomName +" added to  Hotel " + event.hotelName);
    }

    @Subscribe
    public void onMinPriceHigherThanMaxEvent(minPriceHigherThanMaxEvent event){
        JOptionPane.showMessageDialog(null, "Min price is higher than max!");
    }

    @Subscribe
    public void onSearchDoneEvent(searchDoneEvent event){
        JOptionPane.showMessageDialog(null, "Search finished!");
    }

    @Subscribe
    public void onRoomEditedEvent(roomEditedEvent event){
        JOptionPane.showMessageDialog(null, "Room edited!");
    }

}
