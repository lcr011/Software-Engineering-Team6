import java.awt.EventQueue;
import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class photonMain {
	String[] rows;
	private static final String url = "jdbc:postgresql://ec2-44-199-26-122.compute-1.amazonaws.com:5432/dfoas6b885fbrt?sslmode=require";
    private static final String user = "xlsnfmxwycfzie";
    private static final String password = "5dc1c02da3a4cef71d3cce3edad7a304de4871f278873cfb1ef6b499fb234327";

	public static void main(String args[]) {
		//initialize frame
		JFrame frame = new JFrame();
		frame.setSize(1250, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//connect to server 
		connect();
		//run splash screen on event dispatcher
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setTitle("Splash Screen");
					SplashScreen splsh = new SplashScreen(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		//pause on splash screen for 3 seconds
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//run player entry screen on event dispatcher
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.getContentPane().removeAll();
					playerEntry plyrEntry = new playerEntry(frame);
					frame.setTitle("Player Entry");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}
	public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
	
}
