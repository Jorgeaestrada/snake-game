import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

	public Main () {
		add(new Board());
		setResizable(true);
		pack();

		setTitle("Snake V2.0");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame jFrame = new Main();
			jFrame.setVisible(true);
		});
	}
}