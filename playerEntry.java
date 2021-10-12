import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;

public class playerEntry extends JPanel {

	JTable redTeamTable;
	JTable greenTeamTable;
	private JPanel redTeamPanel;
	private JPanel greenTeamPanel;
	private JScrollPane redTeamScrollPane;
	private JScrollPane greenTeamScrollPane;
	
	public playerEntry() {
	  String[] teams = {"ID", "Codename"};
	  
	  String[][] redTeamData = {{"100", "Player1"}, {"26", "Player2"}};
	  String[][] greenTeamData = {{"7045", "Player3"}, {"325", "Player4"}};
	  
	  JPanel panel = new JPanel();
	  FlowLayout flowLayout = (FlowLayout) panel.getLayout();
	  add(panel);
	  
	  redTeamPanel = new JPanel();
	  redTeamPanel.setBorder(new TitledBorder(null, "Red Team", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  panel.add(redTeamPanel);
	  
	  greenTeamPanel = new JPanel();
	  greenTeamPanel.setBorder(new TitledBorder(null, "Green Team", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  panel.add(greenTeamPanel);
	  
	  redTeamScrollPane = new JScrollPane();
	  redTeamPanel.add(redTeamScrollPane);
	  
	  redTeamTable = new JTable(redTeamData, teams);
	  redTeamScrollPane.setViewportView(redTeamTable);
	  redTeamTable.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	  redTeamTable.setFillsViewportHeight(true);
	 
	  greenTeamScrollPane = new JScrollPane();
	  greenTeamPanel.add(greenTeamScrollPane);
	  
	  greenTeamTable = new JTable(greenTeamData, teams);
	  greenTeamScrollPane.setViewportView(greenTeamTable);
	  greenTeamTable.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	  greenTeamTable.setFillsViewportHeight(true);
	  
	  JLabel redTeamLabel = new JLabel("Red Team");
	  redTeamLabel.setVerticalAlignment(SwingConstants.TOP);

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
      
      JLabel redTeamLabel = new JLabel("Red Team");
      redTeamLabel.setBounds(10, 20, 80, 25);
      panel.add(redTeamLabel);
      
      frame.setVisible(true);
	}

}
