package dijkstrarouter.UI;

import javax.swing.*;
import java.awt.*;

public class ReportPanel extends JPanel {
	JTextArea textArea;

	ReportPanel(AppActionListener appActionListener) {
		setBackground(Color.lightGray);
		setLayout(null);
		setBounds(600, 0, 400, 600);

		JLabel reportHeadLabel = new JLabel("Report");
		reportHeadLabel.setFont(new Font("Aria", Font.PLAIN, 25));
		reportHeadLabel.setBounds(100, 0, 300, 100);
		add(reportHeadLabel);

		textArea = new JTextArea();
		textArea.setBounds(50, 120, 350, 500);
		textArea.setFont(new Font("Aria", Font.PLAIN, 15));
		textArea.setBackground(Color.lightGray);
		textArea.setForeground(Color.white);
		add(textArea);

	}

	void ChangeReport(String report) {
		textArea.setText(report);
	}
}
