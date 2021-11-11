import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class playerEntry extends JPanel {

	// Declare variables
	JTable redTeamTable;
	JTable greenTeamTable;
	private JPanel redTeamPanel;
	private JPanel greenTeamPanel;
	private JScrollPane redTeamScrollPane;
	private JScrollPane greenTeamScrollPane;
	private JButton btnStartButton;
	private JButton btnAddPlayer;
	private JFrame frame;
	private JPanel panel;
	private String[][] greenTeamData;
	private String[][] redTeamData;
	private String[] columns;
	private static final long serialVersionUID = 1L;
	


	public playerEntry(JFrame frm) {
		// Create arrays to be inserted into tables
		columns = new String[] { "#", "ID", "Codename" };
		redTeamData = new String[20][20];
		greenTeamData = new String[20][20];

		for (int i = 1; i <= 20; i++) {
			redTeamData[i - 1][0] = String.valueOf(i);
			greenTeamData[i - 1][0] = String.valueOf(i);
		}

		// Begin creating GUI elements
		this.frame = frm;
		this.panel = new JPanel();

		initialize();
	}

	private void initialize() {

		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		add(panel);

		// Red team border elements
		redTeamPanel = new JPanel();
		redTeamPanel.setBackground(new Color(204, 0, 51));
		redTeamPanel.setBorder(new TitledBorder(null, "Red Team", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(redTeamPanel);

		redTeamScrollPane = new JScrollPane();
		redTeamPanel.add(redTeamScrollPane);

		// Create table for red team along with a listener to detect changes to table
		redTeamTable = new JTable(redTeamData, columns);
		redTeamTable.getModel().addTableModelListener(new TableModelListener() {

			public void tableChanged(TableModelEvent e) {
				// Save position of the last cell to be edited
				int i = e.getLastRow();
				int j = e.getColumn();
				System.out.println(i);
				System.out.println(j);
				int counter = 0;

				// First check if it's the ID column
				if (j == 1) {
					// Then save the value of the new ID to be checked
					String checkID = (String) redTeamTable.getModel().getValueAt(i, j);
					 try (Connection conn = photonMain.connect();
				        		PreparedStatement statement = conn.prepareStatement("SELECT * FROM player WHERE id = "+checkID);
				                ResultSet rs = statement.executeQuery()) {
				        	while (rs.next() & counter <= 20) {	
				        		counter++;
				                String codename = rs.getString("codename");
				                redTeamData [i][2] = codename;

				            }
				        		
				        } catch (SQLException ex) {
				            System.out.println(ex.getMessage());
				        }
					
				}
				if (j == 2) {
					// Then save the value of the new ID to be checked
					
					String checkcodename = (String) redTeamTable.getModel().getValueAt(i, j);
					 try (Connection conn = photonMain.connect();
				        		PreparedStatement statement = conn.prepareStatement("SELECT * FROM player WHERE codename = "+"'"+checkcodename+"'");
				                ResultSet rs = statement.executeQuery()) {
				        	while (rs.next() & counter <= 20) {	
				        		counter++;
				                String id = rs.getString("id");
				                redTeamData [i][1] = id;

				            }
				        } catch (SQLException ex) {
				            System.out.println(ex.getMessage());
				        }
					
				}
				
				

				// Search database for ID value HERE by using String checkID
				
			}
		});
		

		redTeamTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		redTeamScrollPane.setViewportView(redTeamTable);
		redTeamTable.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		redTeamTable.setFillsViewportHeight(true);

		// Start game button
		btnStartButton = new JButton("Start Game");
		panel.add(btnStartButton);
		btnStartButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						System.out.println("Action reached");
						photonMain.signalReceiver(0);
					}
				});
		
		//Add Player
		btnAddPlayer = new JButton("Add player");
		panel.add(btnAddPlayer);
		btnAddPlayer.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent d)
		  {
			
		    for(int i = 0; i < redTeamTable.getColumnCount();i++) {
		    	if (i == 1) {
		    		for (int k = 0; k < redTeamTable.getRowCount();k++) {
		    			if ( redTeamTable.getModel().getValueAt(k, i) != null) {
		    				String checkID = (String) redTeamTable.getModel().getValueAt(k, 1);
		    				try (Connection conn = photonMain.connect();
					        		PreparedStatement statement = conn.prepareStatement("SELECT * FROM player WHERE id = "+"'"+checkID+"'");
					                ResultSet rs = statement.executeQuery()) {
		    					{
		    						
						        	if (!rs.next()) {	
						        		String id = (String) redTeamTable.getModel().getValueAt(k, 1);
						        		String codename = (String) redTeamTable.getModel().getValueAt(k, 2);
						        		if (id != null && codename != null)
						        		{
						        			PreparedStatement statement2 = conn.prepareStatement("Insert into player(id,codename) Values ( "+"'"+ id +"',"+"'"+ codename +"'"+")");
						        			ResultSet rs2 = statement2.executeQuery();
						

						            }
						        }
			                	
			                }
		    				}
		    				 catch (SQLException ex) {
						            System.out.println(ex.getMessage());
						        }
		    		          
		    		           
		    		        }
		    			
		    		}
		    	}
		    }
		    for(int i = 0; i < greenTeamTable.getColumnCount();i++) {
		    	if (i == 1) {
		    		for (int k = 0; k < greenTeamTable.getRowCount();k++) {
		    			if ( greenTeamTable.getModel().getValueAt(k, i) != null) {
		    				String checkID = (String) greenTeamTable.getModel().getValueAt(k, 1);
		    				try (Connection conn = photonMain.connect();
					        		PreparedStatement statement = conn.prepareStatement("SELECT * FROM player WHERE id = "+"'"+checkID+"'");
					                ResultSet rs = statement.executeQuery()) {
		    					{
		    						
						        	if (!rs.next()) {	
						        		String id = (String) greenTeamTable.getModel().getValueAt(k, 1);
						        		String codename = (String) greenTeamTable.getModel().getValueAt(k, 2);
						        	PreparedStatement statement2 = conn.prepareStatement("Insert into player(id,codename) Values ( "+"'"+ id +"',"+"'"+ codename +"'"+")");
						            ResultSet rs2 = statement2.executeQuery();
						

						            }
						        }
			                	
			                }
		    				 catch (SQLException ex) {
						            System.out.println(ex.getMessage());
						        }
		    		          
		    		           
		    		        }
		    			
		    		}
		    	}
		    }
		    
		  }
		});
		// Green team border elements
		greenTeamPanel = new JPanel();
		greenTeamPanel.setBackground(new Color(0, 204, 102));
		greenTeamPanel
				.setBorder(new TitledBorder(null, "Green Team", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(greenTeamPanel);

		greenTeamScrollPane = new JScrollPane();
		greenTeamPanel.add(greenTeamScrollPane);

		// Create table for green team along with a listener to detect changes to table
		greenTeamTable = new JTable(greenTeamData, columns);
		greenTeamTable.getModel().addTableModelListener(new TableModelListener() {

			public void tableChanged(TableModelEvent e) {
				// Save position of the last cell to be edited
				int i = e.getLastRow();
				int j = e.getColumn();
				System.out.println(i);
				System.out.println(j);
				int counter = 0;
				// First check if it's the ID column
				if (j == 1) {
					// Then save the value of the new ID to be checked
					String checkID = (String) greenTeamTable.getModel().getValueAt(i, j);
					try (Connection conn = photonMain.connect();
			        		PreparedStatement statement = conn.prepareStatement("SELECT * FROM player WHERE id = "+checkID);
			                ResultSet rs = statement.executeQuery()) {
			        	while (rs.next() & counter <= 20) {	
			        		counter++;
			                String codename = rs.getString("codename");
			                greenTeamData [i][2] = codename;

			            }
			        } catch (SQLException ex) {
			            System.out.println(ex.getMessage());
			        }
					System.out.println(checkID);
				}
				if (j == 2) {
					// Then save the value of the new ID to be checked
					String checkcodename = (String) greenTeamTable.getModel().getValueAt(i, j);
					 try (Connection conn = photonMain.connect();
				        		PreparedStatement statement = conn.prepareStatement("SELECT * FROM player WHERE codename = "+"'"+checkcodename+"'");
				                ResultSet rs = statement.executeQuery()) {
				        	while (rs.next() & counter <= 20) {	
				        		counter++;
				                String id = rs.getString("id");
				                greenTeamData [i][1] = id;

				            }
				        } catch (SQLException ex) {
				            System.out.println(ex.getMessage());
				        }
					System.out.println(checkcodename);
				}

				// Search database for ID value HERE by using String checkID
				
			}
		});

		greenTeamScrollPane.setViewportView(greenTeamTable);
		greenTeamTable.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		greenTeamTable.setFillsViewportHeight(true);

		frame.getContentPane().add(panel);

	}
	// public static void main(String[] args) {
	// JFrame frame = new JFrame();
	// JPanel panel = new JPanel();
	// playerEntry pEntry = new playerEntry();

	// frame.setTitle("Player Entry");
	// frame.setSize(1000, 1000);
	// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// frame.getContentPane().add(pEntry);
	// panel.setLayout(null);

	// frame.setVisible(true);
	// }
}
