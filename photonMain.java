import java.awt.EventQueue;
import javax.swing.JFrame;

public class photonMain {

	public static void main(String args[]) {
		
		//initialize frame
		JFrame frame = new JFrame();
		frame.setSize(1250, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
}
