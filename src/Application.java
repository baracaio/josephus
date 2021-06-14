import ds.LinkedListInterface;
import josephus.Game;
import gui.GuiInterface;
import gui.JPaneGui;
import josephus.GameInterface;

public class Application {
    public static void main(String[] args) {
        GameInterface game = new Game(50, 2);
        GuiInterface gui = new JPaneGui(600, 600, game);
    }
}
