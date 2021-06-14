import ds.NodeInterface;
import josephus.Game;
import josephus.GameInterface;

public class Application {
    public static void main(String[] args) {

        GameInterface game = new Game(12, 3);
        NodeInterface node;

        int i = 1;
        node = game.getList().getFirst();
        int k;

        while (game.playTurn()) {
            k = 1;
            System.out.println("Round " + i);
            do {
                if (node.getValue()) {
                    System.out.println(k + " - Alive");
                } else {
                    System.out.println(k + " - Dead");
                }
                k++;
                node = node.getNext();
            } while (node != game.getList().getFirst());
            i++;
        }
    }
}
