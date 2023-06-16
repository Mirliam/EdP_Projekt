package hotel.edp.views;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.List;

public class searchResultsView{
    private JFrame resultsFrame;
    private JTable dataTable;
    private JScrollPane scrollP;
    private final static int COLUMN_COUNT = 10;
    public static Logger log = LogManager.getLogger(searchResultsView.class.getName());

    public searchResultsView(List<HashMap<String, String>> dataSet){
        this.resultsFrame = new JFrame("Room Search Result");
        this.resultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.resultsFrame.setBounds(550, 150, 800, 600);
        log.info("Drawing results");
        String[][] data = new String[dataSet.size()][COLUMN_COUNT];
        String[] column = {"HotelName","RoomId","RoomStandard","Price","MaxPeople","Iron","Breakfast","Tv","Bar","Jacuzzi"};
        int i = 0;
        for(HashMap<String, String> room:dataSet){
            data[i][0] = room.get("HotelName");
            data[i][1] = room.get("RoomId");
            data[i][2] = room.get("RoomStandard");
            data[i][3] = room.get("Price");
            data[i][4] = room.get("MaxPeople");
            if(Integer.parseInt(room.get("Iron")) == 1)
                data[i][5] = "+";
            else
                data[i][5] = "-";

            if(Integer.parseInt(room.get("Breakfast")) == 1)
                data[i][6] = "+";
            else
                data[i][6] = "-";

            if(Integer.parseInt(room.get("Tv")) == 1)
                data[i][7] = "+";
            else
                data[i][7] = "-";

            if(Integer.parseInt(room.get("Bar")) == 1)
                data[i][8] = "+";
            else
                data[i][8] = "-";

            if(Integer.parseInt(room.get("Jacuzzi")) == 1)
                data[i][9] = "+";
            else
                data[i][9] = "-";

            i++;
        }

        this.dataTable = new JTable(data,column);
        this.scrollP = new JScrollPane(dataTable);
        this.resultsFrame.add(scrollP);
        this.resultsFrame.setSize(800,600);
        setVisibility(true);
    }

    public void showData(){
        this.dataTable.removeAll();
        DefaultTableModel model = (DefaultTableModel) this.dataTable.getModel();
        model.addRow(new Object[]{"Column 1", "Column 2", "Column 3"});
    }
    public void setVisibility(boolean visible){
        this.resultsFrame.setVisible(visible);
    }
}
