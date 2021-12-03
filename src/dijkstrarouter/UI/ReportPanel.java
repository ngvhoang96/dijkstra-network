package dijkstrarouter.UI;

import javax.swing.*;
import java.awt.*;

public class ReportPanel extends JPanel {
	ReportPanel(AppActionListener appActionListener) {
		setBackground(Color.lightGray);
		setLayout(null);
		setBounds(600, 0, 400, 600);

		JLabel testLabel = new JLabel("report");
		testLabel.setBounds(0, 0, 200, 100);
		add(testLabel);
	}
}
