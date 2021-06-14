import ds.LinkedList;
import ds.LinkedListInterface;
import ds.NodeInterface;
import ds.Player;

public class Application {
    public static void main(String[] args) {
        LinkedListInterface circular = new LinkedList();
        NodeInterface node;
        for (int i = 0; i < 10; i++) {
            circular.insertBeginning(new Player());
        }

        node = circular.getFirst();

        for (int i = 0; i < 10; i++) {
            if (node.getValue()) {
                System.out.println(i + " - vivo");
            } else {
                System.out.println(i + " - morto");
            }
            node = node.getNext();
        }

        node = new Player();
        node.setValue(false);

        circular.insert(node, 4);

        node = circular.getFirst();

        for (int i = 0; i < 10; i++) {
            if (node.getValue()) {
                System.out.println(i + " - vivo");
            } else {
                System.out.println(i + " - morto");
            }

            node = node.getNext();
        }
    }
}