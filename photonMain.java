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
					splashScreen splsh = new splashScreen(frame);
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
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
	public static int getPlayersCount() {
        String SQL = "SELECT count(*) FROM players";
        int count = 0;

        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }
	public static void getPlayers() {

        String SQL = "SELECT first_name, last_name, codename FROM players";

        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL)) {
            // display actor information
            displayPlayers(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
	private void displayPlayers(ResultSet rs) throws SQLException {

		int count = getPlayersCount();
        for (int n=0; n<count; n++)  {
            String tempcolumn = (rs.getString("first_name") + ","+ rs.getString("last_name") + ","+ rs.getString("codename"));
				rows[n] = tempcolumn;
				System.out.println(rows[n]);
			  }
        }
}
