package dijkstrarouter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNetwork {
	Network network;
	Node nodeA;
	Node nodeB;
	Node nodeC;
	Node nodeD;

	@BeforeEach
	void setup() {
		nodeA = new Node("A");
		nodeB = new Node("B");
		nodeC = new Node("C");
		nodeD = new Node("D");

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
		assertEquals("inf,B inf,C", network.getNodeReport());
	}

	@Test
	void eachNodeShowsCostsToOtherNodes() {
		nodeA.connectWith(nodeB, 1);
		nodeA.connectWith(nodeC, 3);
		nodeB.connectWith(nodeC, 2);

		assertEquals("1,B   3,C  ", network.getNodeReport());
	}

	@Test
	void nodeReportUpdatesOnSettingDistance() {
		nodeA.connectWith(nodeB, 1);
		nodeA.connectWith(nodeC, 3);
		nodeB.connectWith(nodeC, 2);

		assertEquals("1,B   3,C  ", network.getNodeReport());

		nodeC.setDistanceFrom(nodeA, 2);
		assertEquals("1,B   2,C  ", network.getNodeReport());
	}

	@Test
	void eachNodeShowsCostsToSomeOtherNodes() {
		network.add(nodeD);

		nodeA.connectWith(nodeC, 1);
		nodeA.connectWith(nodeD, 3);
		nodeC.connectWith(nodeD, 1);
		nodeC.connectWith(nodeB, 1);
		nodeD.connectWith(nodeB, 2);

		assertEquals("inf,B 1,C   3,D  ", network.getNodeReport());
	}

	@Test
	void selectEdgeBasedOnCalculation() {
		nodeA.connectWith(nodeC, 1);
		nodeA.connectWith(nodeB, 3);
		nodeC.connectWith(nodeB, 4);

		network.runDijkstra();

		assertEquals(Arrays.asList("AC", "AB"), network.showSelectedEdges());
	}

	@Test
	void selectEdgeBasedOnCalculation2() {
		nodeA.connectWith(nodeC, 1);
		nodeA.connectWith(nodeB, 3);
		nodeC.connectWith(nodeB, 1);

		network.runDijkstra();

		assertEquals(Arrays.asList("AC", "CB"), network.showSelectedEdges());
	}

	@Test
	void selectEdgeBasedOnCalculation3() {
		network.add(nodeD);

		nodeA.connectWith(nodeC, 1);
		nodeA.connectWith(nodeD, 3);
		nodeC.connectWith(nodeD, 1);
		nodeC.connectWith(nodeB, 1);
		nodeD.connectWith(nodeB, 2);

		network.runDijkstra();

		assertEquals(Arrays.asList("AC", "CB", "CD"), network.showSelectedEdges());
	}
}
