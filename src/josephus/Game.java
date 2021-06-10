package josephus;
import ds.*;

public class Game {

    private int numberOfPlayers;
    private int pace;

    public Game(int numberOfPlayers, int pace) {
        this.numberOfPlayers = numberOfPlayers;
        this.pace = pace;
    }

    public int getPace() {
        return pace;
    }

    public void setPace(int pace) {
        this.pace = pace;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     *
     * @param numberOfPlayers
     * @param list
     * @return
     */
    /*public void startGame() {
        LinkedList list = new LinkedList();
        if (generatePlayers(this.numberOfPlayers, list)) {

            int survivors = list.getSize();
            int counterToKill = 1;
            NodeInterface current = list.getFirst();

            do {
                for (int i = 0; i < pace; i++) {
                    current = current.getNext();
                    if (current.getValue()) {
                        counterToKill++;
                    }
                }
                if (counterToKill == pace && current.getValue()) {
                    current.setValue(false);
                    survivors--;
                }

            } while (survivors != 1);
        }
    }*/

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
