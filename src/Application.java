import josephus.Game;
import josephus.GameInterface;
import gui.GuiInterface;
import gui.JPaneGui;

public class Application {
    public static void main(String[] args) {
        GuiInterface gui = new JPaneGui(600, 600);
        GameInterface game = new Game(gui.getPace(), gui.getTotalPlayers());

        gui.begin(game);
    }
}