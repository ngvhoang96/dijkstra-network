package dijkstrarouter.UI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NetworkPanel extends JPanel {
	NetworkPanel() {
		setLayout(null);
		setBounds(0, 0, 600, 600);

		add(new Router("A"));
		add(new Router("B"));

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				System.out.println(e.getComponent());
			}
		});
//		Router routerA = new Router("A");
//		add(routerA);
	}
}
