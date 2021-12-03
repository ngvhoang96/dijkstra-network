package dijkstrarouter.UI;

import javax.swing.*;
import java.awt.*;

public class AppPanel extends JPanel {
	AppPanel() {
		setLayout(null);
		setBackground(Color.BLACK);

		JButton theButton = new JButton("test");
		add(theButton);

		add(new ReportPanel());
		add(new NetworkPanel());
	}
}
