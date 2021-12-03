package dijkstrarouter.UI;

import javax.swing.*;

public class Driver extends JFrame {
	Driver() {
		setContentPane(new AppPanel());
		setSize(1000, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Driver();
	}
}
