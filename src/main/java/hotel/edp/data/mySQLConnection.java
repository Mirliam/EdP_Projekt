package hotel.edp.data;

import hotel.edp.controllers.hotelRoomSearchController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class mySQLConnection {
    private final static String CONNECTION_STRING = "jdbc:mysql://localhost:3306/Hotels";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "default";
    private Connection sqlConnection;
    public static Logger log = LogManager.getLogger(hotelRoomSearchController.class.getName());
    private static volatile mySQLConnection instance;
    private static final Object mutex = new Object();

    public static mySQLConnection getInstance() {
        mySQLConnection result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new mySQLConnection();
            }
        }
        return result;
    }

    private mySQLConnection() {
    }

    public void connectToDatabase() {
        try {
            this.sqlConnection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void closeConnectionToDatabase() {
        try {
            this.sqlConnection.close();
        } catch (Exception e) {
            log.error(e);
        }
    }

    public List<String> getAllHotels() {
        connectToDatabase();
        List<String> hotelList = new ArrayList<>();
        try {
            Statement stmt = this.sqlConnection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from hotel");
            while (rs.next()) {
                hotelList.add(rs.getString(1));
            }
        } catch (Exception e) {
            log.error(e);
        }
        closeConnectionToDatabase();
        return hotelList;
    }

    public synchronized void newHotel(String hotelName) throws SQLException {
        log.info("Adding new hotel");
        connectToDatabase();
        Statement statement = this.sqlConnection.createStatement();
        statement.executeUpdate("INSERT INTO hotel " + "VALUES ('" + hotelName + "')");
        closeConnectionToDatabase();
    }

    public void newRoom(HashMap<String, String> roomDetails) throws SQLException {
        log.info("Adding new room: " + roomDetails);
        connectToDatabase();
        Statement statement = this.sqlConnection.createStatement();
        log.debug("INSERT INTO rooms (HotelName, RoomID, price, MaxPeople,RoomStandard,"
                + " bar,tv,breakfast, iron, jacuzzi) "
                + "VALUES (" +
                "'" + roomDetails.get("HotelName") + "'," +
                "'" + roomDetails.get("RoomId") + "'," +
                roomDetails.get("Price") + "," +
                roomDetails.get("PeopleCount") + "," +
                "'" + roomDetails.get("RoomStandard") + "'," +
                roomDetails.get("Bar") + "," +
                roomDetails.get("Breakfast") + "," +
                roomDetails.get("Tv") + "," +
                roomDetails.get("Iron") + "," +
                roomDetails.get("Jacuzzi") +
                ")");
        statement.executeUpdate("INSERT INTO rooms (HotelName, RoomID, price, MaxPeople,RoomStandard," +
                " bar,tv,breakfast, iron, jacuzzi) "
                + "VALUES (" +
                "'" + roomDetails.get("HotelName") + "'," +
                "'" + roomDetails.get("RoomId") + "'," +
                roomDetails.get("Price") + "," +
                roomDetails.get("PeopleCount") + "," +
                "'" + roomDetails.get("RoomStandard") + "'," +
                roomDetails.get("Bar") + "," +
                roomDetails.get("Tv") + "," +
                roomDetails.get("Breakfast") + "," +
                roomDetails.get("Iron") + "," +
                roomDetails.get("Jacuzzi") +
                ")");
        closeConnectionToDatabase();
    }

    public List<HashMap<String, String>> roomSearch(HashMap<String, String> roomDetails) throws SQLException {
        log.info("Searching for rooms");
        connectToDatabase();
        List<HashMap<String, String>> roomList = new ArrayList<>();
        String query = "SELECT * FROM rooms";
        String where = " WHERE " + "price>" + roomDetails.get("MinPrice")
                + " AND price<" + roomDetails.get("MaxPrice")
                + " AND RoomStandard='" + roomDetails.get("RoomStandard") + "'"
                + " AND MaxPeople>=" + roomDetails.get("PeopleCount");
        if(!"All".equals(roomDetails.get("HotelName")))
            where += " AND HotelName='" + roomDetails.get("HotelName") + "'";

        if(Integer.parseInt(roomDetails.get("Bar"))==1)
            where += " AND bar='" + roomDetails.get("Bar") + "'";

        if(Integer.parseInt(roomDetails.get("Tv"))==1)
            where += " AND tv='" + roomDetails.get("Tv") + "'";

        if(Integer.parseInt(roomDetails.get("Breakfast"))==1)
            where += " AND breakfast='" + roomDetails.get("Breakfast") + "'";

        if(Integer.parseInt(roomDetails.get("Iron"))==1)
            where += " AND iron='" + roomDetails.get("Iron") + "'";

        if(Integer.parseInt(roomDetails.get("Jacuzzi"))==1)
            where += " AND jacuzzi='" + roomDetails.get("Jacuzzi") + "'";

        query += where;
        log.info(query);
        try {
            Statement stmt = this.sqlConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                HashMap<String,String> record = new HashMap<>();
                record.put("HotelName", rs.getString(1));
                record.put("RoomId", rs.getString(2));
                record.put("Price", rs.getString(3));
                record.put("MaxPeople", rs.getString(4));
                record.put("RoomStandard", rs.getString(5));
                record.put("Bar", rs.getString(6));
                record.put("Tv", rs.getString(7));
                record.put("Breakfast", rs.getString(8));
                record.put("Iron", rs.getString(9));
                record.put("Jacuzzi", rs.getString(10));
                roomList.add(record);
            }
        } catch (Exception e) {
            log.error(e);
        }
        closeConnectionToDatabase();
        log.info(roomList);
        return roomList;
    }

    public void removeRoom(HashMap<String, String> roomData) throws SQLException {
        connectToDatabase();
        try {
            Statement stmt = this.sqlConnection.createStatement();
            String query = "DELETE FROM rooms WHERE "
                    + "HotelName='" + roomData.get("HotelName") + "' "
                    + " AND RoomID='" + roomData.get("RoomId") + "'";
            log.info(query);
            stmt.executeUpdate(query);
        } catch (Exception e) {
            log.error(e);
        }
        closeConnectionToDatabase();
    }

    public List<String> getHotelRooms(String hotelName) {
        connectToDatabase();
        List<String> roomList = new ArrayList<>();
        try {
            Statement stmt = this.sqlConnection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM rooms WHERE HotelName='" + hotelName + "'");
            while (rs.next()) {
                roomList.add(rs.getString(2));
            }
        } catch (Exception e) {
            log.error(e);
        }
        closeConnectionToDatabase();
        return roomList;
    }

    public HashMap<String, String> getRoomData(String hotelName, String roomName) {
        connectToDatabase();
        HashMap<String, String> roomData = new HashMap<String, String>();
        try {
            Statement stmt = this.sqlConnection.createStatement();
            String query = "SELECT * FROM rooms WHERE HotelName='" + hotelName + "'"
                    + " AND RoomID='" + roomName + "'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                roomData.put("HotelName", rs.getString(1));
                roomData.put("RoomId", rs.getString(2));
                roomData.put("Price", rs.getString(3));
                roomData.put("MaxPeople", rs.getString(4));
                roomData.put("RoomStandard", rs.getString(5));
                roomData.put("Bar", rs.getString(6));
                roomData.put("Tv", rs.getString(7));
                roomData.put("Breakfast", rs.getString(8));
                roomData.put("Iron", rs.getString(9));
                roomData.put("Jacuzzi", rs.getString(10));
                }
        } catch (Exception e) {
            log.error(e);
        }
        closeConnectionToDatabase();
        return roomData;
    }

    public void updateRoomData(HashMap<String, String> roomData) {
        connectToDatabase();
        try {
            Statement stmt = this.sqlConnection.createStatement();
            String query = "UPDATE rooms SET "
                    + "price='" + roomData.get("Price") + "', "
                    + "MaxPeople='" + roomData.get("PeopleCount") + "', "
                    + "bar='" + roomData.get("Bar") + "', "
                    + "tv='" + roomData.get("Tv") + "', "
                    + "breakfast='" + roomData.get("Breakfast") + "', "
                    + "iron='" + roomData.get("Iron") + "', "
                    + "roomStandard='" + roomData.get("RoomStandard") + "', "
                    + "jacuzzi='" + roomData.get("Jacuzzi") + "' "
                    + " WHERE HotelName='" + roomData.get("HotelName") + "' "
                    + " AND RoomID='" + roomData.get("RoomId") + "'";
            log.info(query);
            stmt.executeUpdate(query);
        } catch (Exception e) {
            log.error(e);
        }
        closeConnectionToDatabase();

    }
}



