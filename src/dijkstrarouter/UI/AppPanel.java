package dijkstrarouter.UI;

import javax.swing.*;
import java.awt.*;

public class AppPanel extends JPanel {
	private final NetworkPanel networkPanel;
	private final ReportPanel reportPanel;

	AppPanel() {
		setLayout(null);
		setBackground(Color.BLACK);

		JButton theButton = new JButton("test");
		add(theButton);

		AppActionListener appActionListener = new AppActionListener(this);
		networkPanel = new NetworkPanel(appActionListener);
		reportPanel = new ReportPanel(appActionListener);
		add(networkPanel);
		add(reportPanel);
	}

	public NetworkPanel getNetworkPanel() {
		return networkPanel;
	}

	public ReportPanel getReportPanel() {
		return reportPanel;
	}
}
