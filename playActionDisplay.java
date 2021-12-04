import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JTable;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class playActionDisplay extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	String[] columns = { "#", "ID", "Codename", "Points" };
	String[] actionColumn = { "Player Actions" };
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private int RedMemberPoints;
	private int GreenMemberPoints;
	private int currentHitCount = 0;
	private int minusP = -100;
	private int plusP = 100;
	private int maxPlayerCount = 20;
	String[][] redTeamData = new String[maxPlayerCount][2];
	String[][] greenTeamData = new String[maxPlayerCount][2];
	String[][] playerActions = new String[maxPlayerCount][2];
	//ArrayList<String> playerActions = new ArrayList<String>();
	public String FindPlayerName(String ID) {
		int counter = 0;
		String codename = null;
		 try (Connection conn = photonMain.connect();
	        		PreparedStatement statement = conn.prepareStatement("SELECT * FROM player WHERE id = "+ID);
	                ResultSet rs = statement.executeQuery()) {
	        	while (rs.next() & counter <= maxPlayerCount) {	
	        		counter++;
	                codename = rs.getString("codename");

	            }
	        		
	        } catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }
		 return codename;
	}
	
	public playActionDisplay(playerEntry plyEntry, DatagramSocket rec) {
		
		redTeamData = plyEntry.getRedData();
		greenTeamData = plyEntry.getGreenData();
		
		redTeamData[maxPlayerCount - 1][2] = "RED TOTAL:";
		redTeamData[maxPlayerCount - 1][3] = String.valueOf(RedMemberPoints);
		greenTeamData[maxPlayerCount - 1][2] = "GREEN TOTAL:";
		greenTeamData[maxPlayerCount - 1][3] = String.valueOf(GreenMemberPoints);
		
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
		model.addColumn("Player actions");
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_2.add(scrollPane_2);
		
		scrollPane_2.setViewportView(table_2);
		
		table_3 = new JTable(model);
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
		class DatagramThread extends Thread{
			public void run() {
				
				byte buffer[] = new byte[8];
				DatagramPacket p = new DatagramPacket(buffer, 8);
				try {
					if(rec != null) {
						rec.receive(p);
						currentHitCount++; 
						String packet = new String(p.getData());
						String arr[] = packet.split(":", 2);
						String FirstID = arr[0];
						String SecondID = arr[1];
						char ID2 = SecondID.charAt(0);
						String stringID2 = String.valueOf(ID2);
						//playerActions.add(FindPlayerName(FirstID) + " " + "Hit " + FindPlayerName(stringID2)); 
						if(table_3.getRowCount() >= 20) {
							model.removeRow(0);
							model.addRow(new Object[] {FindPlayerName(FirstID) + " " + "Hit " + FindPlayerName(stringID2)});
							
						}
						else {
							model.addRow(new Object[] {FindPlayerName(FirstID) + " " + "Hit " + FindPlayerName(stringID2)});
						}
						for(int i = 0; i < maxPlayerCount;i++)
						{
							if (table.getModel().getValueAt(i, 1) != null) {
							
								if ((boolean)table.getValueAt(i, 1).equals(FirstID))
								{
									int current = Integer.valueOf(redTeamData[i][3]);
									current += plusP;
									RedMemberPoints+= plusP;
									redTeamData [i][3] = String.valueOf(current);
									redTeamData[maxPlayerCount - 1][3] = String.valueOf(RedMemberPoints);
									table.repaint();
								}
								if ((boolean)table.getValueAt(i, 1).equals(stringID2))
								{
									int current = Integer.valueOf(redTeamData[i][3]);
									current += minusP;
									RedMemberPoints+= minusP;
									redTeamData [i][3] = String.valueOf(current);
									redTeamData[maxPlayerCount - 1][3] = String.valueOf(RedMemberPoints);
									table.repaint();
								}
								if ((boolean)table_1.getValueAt(i, 1).equals(FirstID))
								{
									int current = Integer.valueOf(greenTeamData[i][3]);
									current += plusP;
									GreenMemberPoints+= plusP;
									greenTeamData [i][3] = String.valueOf(current);
									greenTeamData[maxPlayerCount - 1][3] = String.valueOf(GreenMemberPoints);
									table_1.repaint();
								}
								if ((boolean)table_1.getValueAt(i, 1).equals(stringID2))
								{
									int current = Integer.valueOf(greenTeamData[i][3]);
									current += minusP;
									GreenMemberPoints+= minusP;
									greenTeamData [i][3] = String.valueOf(current);
									greenTeamData[maxPlayerCount - 1][3] = String.valueOf(GreenMemberPoints);
									table_1.repaint();
								}
								
							
						}
					}
				}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		}

        timer.scheduleAtFixedRate(new TimerTask() {
            
			int PreGameTime = 10;
			int GameTime = 120;
			boolean started = false;

            public void run() {
            	
				if(PreGameTime > 0) {
					GTimer.setText("Game Time Remaining: " + (PreGameTime/60) + ":" + ((PreGameTime%60)/10 == 0 ? ("0" + PreGameTime%60) : (PreGameTime%60 == 0 ? "00" : (PreGameTime%60))));
					PreGameTime--;
				}
				/*else if(!started) {
					try {
						Process p = Runtime.getRuntime().exec("python /resources/trafficgenerator.py 1 2 3 4 100");
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}*/
				else if(GameTime > 0){
					
					DatagramThread Packets = new DatagramThread();
					Packets.start();
					
					GTimer.setText("Game Time Remaining: " + (GameTime/60) + ":" + ((GameTime%60)/10 == 0 ? ("0" + GameTime%60) : (GameTime%60 == 0 ? "00" : (GameTime%60))));
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
