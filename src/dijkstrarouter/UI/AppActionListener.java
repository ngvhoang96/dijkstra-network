package dijkstrarouter.UI;

import dijkstrarouter.Network;
import dijkstrarouter.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppActionListener implements ActionListener {
	private final AppPanel appPanel;
	private final Network network;

	AppActionListener(AppPanel panel) {
		appPanel = panel;
		network = new Network();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "toggle edit mode" -> appPanel.getNetworkPanel().toggleEditMode();
			case "add a node" -> {
				String nodeName = JOptionPane.showInputDialog("Node name");
				if (nodeName.length() == 1) {
					try {
						Node newNode = new Node(nodeName);
						network.add(newNode);
						appPanel.getNetworkPanel().add(new Router(newNode, appPanel.getNetworkPanel()));
						appPanel.repaint();
						appPanel.getReportPanel().addToReport("Add node " + nodeName);
					} catch (RuntimeException ex) {
						JOptionPane.showMessageDialog(appPanel, ex.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(appPanel, "Please enter 1 character for the name");
				}
			}
			case "run algo" -> {
				if (network.size() > 0) {
					Node startNode = (Node) JOptionPane.showInputDialog(
						appPanel,
						"Please select the starting node",
						"Starting Node",
						JOptionPane.PLAIN_MESSAGE,
						null,
						network.getNodeList().toArray(),
						network.getNodeList().get(0)
					);
					try {
						network.setOrigin(startNode.getNodeName());
						network.runDijkstra();
						appPanel.getReportPanel().changeReport(network.getFullReport());
//						appPanel.getReportPanel().changeReport("Sorry, I have hidden this part to avoid");
//						appPanel.getReportPanel().addToReport("academic dishonesty even though it is my");
//						appPanel.getReportPanel().addToReport("own code LOL. Full version will be open to");
//						appPanel.getReportPanel().addToReport("public after the final is over. :)");
//						appPanel.getReportPanel().addToReport("\nIn the mean time, I hope this app helps");
//						appPanel.getReportPanel().addToReport("you confirming your answer!");

						appPanel.getNetworkPanel().drawSelectedEdges(network.showSelectedEdges());
					} catch (RuntimeException ex) {
						JOptionPane.showMessageDialog(appPanel, ex.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(appPanel, "Please add a node first");
				}
			}
			case "restart" -> {
				Container parent = appPanel.getParent();
				while (!(parent instanceof Driver)) {
					parent = parent.getParent();
				}
				((Driver) parent).restart();
			}
			default -> {
			}
		}
	}

	public void logToReport(String log) {
		appPanel.getReportPanel().addToReport(log);
	}
}
