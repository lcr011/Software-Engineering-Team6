
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;

public class splashScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { SplashScreen window = new
	 * SplashScreen(); window.frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the application.
	 */
	public splashScreen(JFrame frm) {
		this.frame = frm;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// frame = new JFrame();

		// set bounds in middle of screen, take up roughly 50% of screen, minus a bit
		// frame.setBounds((int)(.25f *
		// Toolkit.getDefaultToolkit().getScreenSize().width), (int)(.25f *
		// Toolkit.getDefaultToolkit().getScreenSize().height), (int)(.5f *
		// Toolkit.getDefaultToolkit().getScreenSize().width), (int)(.5f *
		// Toolkit.getDefaultToolkit().getScreenSize().height));
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// initialize label for logo display
		JLabel lblNewLabel = new JLabel("");

		// create and resize the image to fit the frame
		Image img = (new ImageIcon(splashScreen.class.getResource("/resources/logo.jpg"))).getImage();
		img = img.getScaledInstance(frame.getSize().width, frame.getSize().height, Image.SCALE_SMOOTH);

		// set the resized image for display
		lblNewLabel.setIcon((new ImageIcon(img)));

		frame.getContentPane().add(lblNewLabel, BorderLayout.CENTER);
	}

}