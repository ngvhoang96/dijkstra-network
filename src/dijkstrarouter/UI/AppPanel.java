package dijkstrarouter.UI;

import javax.swing.*;
import java.awt.*;

public class AppPanel extends JPanel {
	private final NetworkPanel networkPanel;

	AppPanel() {
		setLayout(null);
		setBackground(Color.BLACK);

		JButton theButton = new JButton("test");
		add(theButton);

		AppActionListener appActionListener = new AppActionListener(this);
		networkPanel = new NetworkPanel(appActionListener);
		add(networkPanel);
		add(new ReportPanel(appActionListener));
	}

	public NetworkPanel getNetworkPanel() {
		return networkPanel;
	}
}
