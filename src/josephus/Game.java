package josephus;
import ds.*;

public class Game implements GameInterface {

    private int numberOfPlayers;
    private int pace;

    public Game(int numberOfPlayers, int pace) {
        this.numberOfPlayers = numberOfPlayers;
        this.pace = pace;
        LinkedListInterface list = new LinkedList();
    }

    public int getPace() {
        return pace;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     *
     * @param numberOfPlayers
     * @param list
     * @return
     */
    public boolean generatePlayers(int numberOfPlayers, LinkedList list) {
        if (isDataValid(numberOfPlayers, pace)) {
            for (int i = 0; i < numberOfPlayers; i++) {
                NodeInterface player = new Player();
                list.insertEnd(player);
            }
            return true;
        }
        return false;
    }

    /**
     *
     * @param numberOfPlayers
     * @param pace
     * @return
     */
    public boolean isDataValid(int numberOfPlayers, int pace) {
        return (numberOfPlayers > 1 && numberOfPlayers <= 50) && (pace > 1 && pace <= numberOfPlayers);
    }


}
