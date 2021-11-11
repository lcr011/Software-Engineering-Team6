import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.awt.*;

public class playActionDisplay extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	String[] columns = { "Codename", "Points" };
	String[] actionColumn = { "Player Actions" };
	String[][] redTeamData = new String[20][2];
	String[][] greenTeamData = new String[20][2];
	String[][] playerActions = new String[20][2];
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	
	public playActionDisplay(playerEntry plyEntry) {
		
		//Placeholder data
		for (int i = 1; i <= 10; i++) {
			//redTeamData[i - 1][0] = "Player" + String.valueOf(i);
			//greenTeamData[i - 1][0] = "Player" + String.valueOf(i);
			playerActions[i - 1][0] = "hit" + String.valueOf(i);
		}
		
		redTeamData = plyEntry.getRedData();
		greenTeamData = plyEntry.getGreenData();
		columns = plyEntry.getColumns();
		
		setBorder(new TitledBorder(null, "Current Scores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane);
		
		JPanel panel_3 = new JPanel();
		splitPane.setLeftComponent(panel_3);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 0, 51));
		panel_3.add(panel);
		panel.setBorder(new TitledBorder(null, "Red Team", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setToolTipText("");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1);
		
		table = new JTable(redTeamData, columns);
		scrollPane_1.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 204, 102));
		panel_3.add(panel_1);
		panel_1.setBorder(new TitledBorder(null, "Green Team", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		table_1 = new JTable(greenTeamData, columns);
		scrollPane.setViewportView(table_1);
		
		JPanel panel_4 = new JPanel();
		splitPane.setRightComponent(panel_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 153, 204));
		panel_4.add(panel_2);
		panel_2.setBorder(new TitledBorder(null, "Current Game Action", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Player actions", playerActions);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_2.add(scrollPane_2);
		
		scrollPane_2.setViewportView(table_2);
		
		table_3 = new JTable(playerActions, actionColumn);
		scrollPane_2.setViewportView(table_3);
		
		//timer attempt
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		panel_4.add(panel_5);
		panel_5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JLabel GTimer = new JLabel();
		GTimer.setFont(new Font("Impact", Font.PLAIN, 35));
		panel_5.add(GTimer);
		
		Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            
			int PreGameTime = 30;
			int GameTime = 360;

            public void run() {
				
				if(PreGameTime > 0) {
					GTimer.setText("Game Starting in: " + PreGameTime);
					PreGameTime--;
				}else if(GameTime > 0){
					GTimer.setText("Game Time Remaining: " + GameTime);
					GameTime--;
				}else{
					timer.cancel();
                    GTimer.setText("Game Over");
                }
            }
        }, 0, 1000);
		
		
		
	}
	/*public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		playActionDisplay pActDisp = new playActionDisplay();

		frame.setTitle("Action Display");
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(pActDisp);
		panel.setLayout(null);

		frame.setVisible(true);
	}*/
}	
