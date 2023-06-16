package hotel.edp.events;

public class roomAddedEvent {
    public String roomName;
    public String hotelName;
    public roomAddedEvent(String room, String hotel){
        this.roomName = room;
        this.hotelName = hotel;
    }
}
