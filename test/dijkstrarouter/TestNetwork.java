package dijkstrarouter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNetwork {
	Network network;
	Node nodeA;
	Node nodeB;
	Node nodeC;

	@BeforeEach
	void setup() {
		nodeA = new Node("A");
		nodeB = new Node("B");
		nodeC = new Node("C");

		network = new Network();
		network.add(nodeA);
		network.add(nodeB);
		network.add(nodeC);
	}

	@Test
	void aNetworkHasManyNodes() {
		assertEquals(3, network.size());
	}

	@Test
	void eachNodeShowsZeroCostToOtherNodeOnInit() {
		assertEquals("inf,B inf,C", network.getNodeReportOf("A"));
	}

	@Test
	void eachNodeShowsCostsToOtherNodes() {
		nodeA.connectWith(nodeB, 1);
		nodeA.connectWith(nodeC, 3);
		nodeB.connectWith(nodeC, 2);

		assertEquals("1,B 3,C", network.getNodeReportOf("A"));
	}
}
