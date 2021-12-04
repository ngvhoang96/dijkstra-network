package dijkstrarouter.UI;

import dijkstrarouter.Network;
import dijkstrarouter.Node;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppActionListener implements ActionListener {
	AppPanel appPanel;
	NetworkPanel networkPanel;
	Network network;

	AppActionListener(AppPanel panel) {
		appPanel = panel;
		networkPanel = appPanel.getNetworkPanel();
		network = new Network();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "toggle edit mode" -> appPanel.getNetworkPanel().toggleEditMode();
			case "add a node" -> {
				String nodeName = JOptionPane.showInputDialog("Node name");
				if (nodeName.length() > 0) {
					Node newNode = new Node(nodeName);
					network.add(newNode);
					appPanel.getNetworkPanel().add(new Router(newNode, appPanel.getNetworkPanel()));
					appPanel.repaint();
					appPanel.getReportPanel().ChangeReport("Add node " + nodeName);
				}
			}
			case "run algo" -> {
				network.runDijkstra();
				appPanel.getReportPanel().ChangeReport(network.getFullReport());
				appPanel.getNetworkPanel().drawSelectedEdges(network.showSelectedEdges());
			}
			default -> {
			}
		}
	}
}
