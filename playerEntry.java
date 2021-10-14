import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class playerEntry extends JPanel {

	//Declare variables
	JTable redTeamTable;
	JTable greenTeamTable;
	private JPanel redTeamPanel;
	private JPanel greenTeamPanel;
	private JScrollPane redTeamScrollPane;
	private JScrollPane greenTeamScrollPane;
	private JButton btnNewButton;
	
	public playerEntry() {
	//Create arrays to be inserted into tables
	  String[] columns = {"#", "ID", "Codename"};
	  String[][] redTeamData = new String[20][20];
	  String[][] greenTeamData = new String[20][20];
	  
	  for(int i = 1; i <= 20; i++) {
		  redTeamData[i-1][0] = String.valueOf(i);
		  greenTeamData[i-1][0] = String.valueOf(i);
	  }
	
	//Begin creating GUI elements
	  JPanel panel = new JPanel();
	  FlowLayout flowLayout = (FlowLayout) panel.getLayout();
	  add(panel);
	  
	//Red team border elements
	  redTeamPanel = new JPanel();
	  redTeamPanel.setBackground(new Color(204, 0, 51));
	  redTeamPanel.setBorder(new TitledBorder(null, "Red Team", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  panel.add(redTeamPanel);
	  
	  redTeamScrollPane = new JScrollPane();
	  redTeamPanel.add(redTeamScrollPane);
	  
	//Create table for red team along with a listener to detect changes to table
	  redTeamTable = new JTable(redTeamData, columns);
	  redTeamTable.getModel().addTableModelListener(new TableModelListener() {

	      public void tableChanged(TableModelEvent e) {
	         System.out.println(e);
	      }
	    });
	  
	  redTeamTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
	  redTeamScrollPane.setViewportView(redTeamTable);
	  redTeamTable.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	  redTeamTable.setFillsViewportHeight(true);
	  
	//Start game button
	  btnNewButton = new JButton("Start Game");
	  panel.add(btnNewButton);
	 
	//Green team border elements
	  greenTeamPanel = new JPanel();
	  greenTeamPanel.setBackground(new Color(0, 204, 102));
	  greenTeamPanel.setBorder(new TitledBorder(null, "Green Team", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  panel.add(greenTeamPanel);
	 
	  greenTeamScrollPane = new JScrollPane();
	  greenTeamPanel.add(greenTeamScrollPane);
	  
	//Create table for green team along with a listener to detect changes to table
	  greenTeamTable = new JTable(greenTeamData, columns);
	  greenTeamTable.getModel().addTableModelListener(new TableModelListener() {

	      public void tableChanged(TableModelEvent e) {
	         System.out.println(e);
	      }
	    });
	  
	  greenTeamScrollPane.setViewportView(greenTeamTable);
	  greenTeamTable.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	  greenTeamTable.setFillsViewportHeight(true);
	  
	}
	
	public static void main(String[] args) {
      JFrame frame = new JFrame();
      JPanel panel = new JPanel();
      playerEntry pEntry = new playerEntry();
      
      frame.setTitle("Player Entry");
      frame.setSize(1000, 1000);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(pEntry);
      panel.setLayout(null);
      
      frame.setVisible(true);
	}
}
