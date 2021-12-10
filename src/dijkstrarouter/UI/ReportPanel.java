package dijkstrarouter.UI;

import javax.swing.*;
import java.awt.*;

public class ReportPanel extends JPanel {
	JTextArea textArea;
	private boolean showInstruction;

	ReportPanel(AppActionListener appActionListener) {
		setBackground(Color.lightGray);
		setLayout(null);
		setBounds(600, 0, 400, 600);

		JLabel reportHeadLabel = new JLabel("Log", JLabel.CENTER);
		reportHeadLabel.setFont(new Font("Aria", Font.PLAIN, 20));
		reportHeadLabel.setBounds(0, 0, 400, 80);
		add(reportHeadLabel);

		textArea = new JTextArea();
		textArea.setBounds(0, 80, 400, 500);
		textArea.setFont(new Font("Courier new", Font.PLAIN, 13));
		textArea.setMargin(new Insets(30, 30, 30, 30));
		textArea.setBackground(new Color(34, 34, 34));
		textArea.setForeground(Color.white);
		add(textArea);

		showInstruction = true;
		textArea.setText("1. Click Add a node to add a router\n2. Move Node to desired position\n3. Change Edit mode to draw links\n4. Click Run Dijkstra to see the report");
	}

	void changeReport(String report) {
		textArea.setText(report);
	}

	void addToReport(String report) {
		if (showInstruction) {
			textArea.setText("");
			showInstruction = false;
		}
		textArea.append("\n" + report);
	}
}
