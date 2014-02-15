package platformer;

import platformer.Window.*;

public class Platformer {

	public Platformer() {
		System.out.println("Platformer started");
		newGame();
	}
	
	public static void newGame() {
		new Thread(new Window(640, 480)).start();
	}
	
	public static void main(String[] args) {
		new Platformer();
	}

}
