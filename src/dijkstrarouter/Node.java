package dijkstrarouter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Node {
	private final String thisNodeName;
	private final List<String> neighbors;
	private final HashMap<String, Integer> edgesList;

	public Node(String nodeName) {
		thisNodeName = nodeName;
		neighbors = new ArrayList<>();
		edgesList = new HashMap<>();
	}

	public void connectWith(Node otherNode, int cost) {
		neighbors.add(otherNode.getNodeName());
		edgesList.put(otherNode.getNodeName(), cost);

		otherNode.getNeighbors().add(thisNodeName);
		otherNode.edgesList.put(thisNodeName, cost);
	}

	public String getNodeName() {
		return thisNodeName;
	}

	public List<String> getNeighbors() {
		return neighbors;
	}

	public int getCostTo(String otherNodeName) {
		return Optional.ofNullable(edgesList.get(otherNodeName)).orElse(-1);
	}
}
