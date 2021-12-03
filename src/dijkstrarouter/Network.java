package dijkstrarouter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Network {
	private final List<Node> nodeList;

	public Network() {
		nodeList = new ArrayList<>();
	}

	public void add(Node node) {
		nodeList.add(node);
	}

	public int size() {
		return nodeList.size();
	}

	public String getNodeReportOf(String nodeName) {
		Node currentNode = nodeList.stream().filter(node -> node.getNodeName().equals(nodeName)).findFirst().orElse(null);
		if (currentNode == null) {
			return "No node found";
		} else {
			StringJoiner result = new StringJoiner(" ");
			nodeList.stream().filter(node -> !node.getNodeName().equals(nodeName))
				.forEach(neighbor -> {
						int costToCurrentNeighbor = currentNode.getCostTo(neighbor.getNodeName());

						result.add((costToCurrentNeighbor == 9999 ? "inf" : costToCurrentNeighbor) + "," + neighbor);
					}
				);
			return result.toString();
		}
	}
}
