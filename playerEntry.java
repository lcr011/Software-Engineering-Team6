import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class playerEntry extends JPanel {

	JTable redTeamTable;
	JTable greenTeamTable;
	private JPanel redTeamPanel;
	private JPanel greenTeamPanel;
	private JScrollPane redTeamScrollPane;
	private JScrollPane greenTeamScrollPane;
	private JButton btnNewButton;
	
	public playerEntry() {
	  String[] columns = {"#", "ID", "Codename"};
	  String[][] redTeamData = new String[20][20];
	  String[][] greenTeamData = new String[20][20];
	  
	  for(int i = 1; i <= 20; i++) {
		  redTeamData[i-1][0] = String.valueOf(i);
		  greenTeamData[i-1][0] = String.valueOf(i);
	  }
	  
	  JPanel panel = new JPanel();
	  FlowLayout flowLayout = (FlowLayout) panel.getLayout();
	  add(panel);
	  
	  redTeamPanel = new JPanel();
	  redTeamPanel.setBackground(new Color(204, 0, 51));
	  redTeamPanel.setBorder(new TitledBorder(null, "Red Team", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  panel.add(redTeamPanel);
	  
	  redTeamScrollPane = new JScrollPane();
	  redTeamPanel.add(redTeamScrollPane);
	  
	  redTeamTable = new JTable(redTeamData, columns);
	  redTeamTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
	  redTeamScrollPane.setViewportView(redTeamTable);
	  redTeamTable.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	  redTeamTable.setFillsViewportHeight(true);
	  
	  btnNewButton = new JButton("Start Game");
	  panel.add(btnNewButton);
	  
	  greenTeamPanel = new JPanel();
	  greenTeamPanel.setBackground(new Color(0, 204, 102));
	  greenTeamPanel.setBorder(new TitledBorder(null, "Green Team", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  panel.add(greenTeamPanel);
	 
	  greenTeamScrollPane = new JScrollPane();
	  greenTeamPanel.add(greenTeamScrollPane);
	  
	  greenTeamTable = new JTable(greenTeamData, columns);
	  greenTeamScrollPane.setViewportView(greenTeamTable);
	  greenTeamTable.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	  greenTeamTable.setFillsViewportHeight(true);
	  
	}
	
	public void redTeamAddRow(int num, int id, String codename){
		DefaultTableModel redTeamModel = (DefaultTableModel) redTeamTable.getModel();
		redTeamModel.addRow(new Object[]{num, id, codename});
	}
	
	public void greenTeamAddRow(int num, int id, String codename){
		DefaultTableModel greenTeamModel = (DefaultTableModel) greenTeamTable.getModel();
		greenTeamModel.addRow(new Object[]{num, id, codename});
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
