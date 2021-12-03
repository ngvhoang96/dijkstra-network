package dijkstrarouter.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppActionListener implements ActionListener {
	AppPanel appPanel;
	NetworkPanel networkPanel;

	AppActionListener(AppPanel panel) {
		appPanel = panel;
		networkPanel = appPanel.getNetworkPanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		switch (e.getActionCommand()) {
			case "toggle edit mode" -> appPanel.getNetworkPanel().toggleEditMode();
			case "add a node" -> {
				String nodeName = JOptionPane.showInputDialog("Node name");
				appPanel.getNetworkPanel().add(new Router(nodeName, appPanel.getNetworkPanel()));
				appPanel.repaint();
			}
			default -> {
			}
		}
	}
}
