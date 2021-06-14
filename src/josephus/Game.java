package josephus;
import ds.*;

public class Game implements GameInterface {

    private final int numberOfPlayers;
    private final int pace;
    private final LinkedListInterface list;
    private int survivors;
    NodeInterface lastDead;

    public Game(int numberOfPlayers, int pace) {
        this.numberOfPlayers = numberOfPlayers;
        this.pace = pace;
        list = new LinkedList();
        generatePlayers(this.numberOfPlayers);
        survivors = numberOfPlayers;
        lastDead = list.getFirst();
    }

    public int getPace() {
        return pace;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * @return Run a round of josephus algorithm and
     * returns if the game has to continue or not
     */
    public boolean playTurn() {
        if (this.survivors == 1) {
            return false;
        }
        NodeInterface node = lastDead;
        int counterToKill = 1;
        boolean killedSomeone = false;

        do {
            if (counterToKill == pace) {
                if (node.getValue()) {
                    node.setValue(false);
                    survivors--;
                    killedSomeone = true;
                    lastDead = node;
                }
            }
            if (node.getValue()) {
                counterToKill++;
            }

            node = node.getNext();
        } while (!killedSomeone);

        return true;
    }

    /**
     * @return return the list
     */
    public LinkedListInterface getList() {
        return this.list;
    }

    /**
     * @param numberOfPlayers number of players to be in the circle
     */
    private void generatePlayers(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            NodeInterface player = new Player();
            list.insertEnd(player);
        }
    }
}
