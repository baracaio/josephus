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
     * @return returns if the game has to continue or not
     */
    public boolean playTurn() {
        int survivors = list.getSize();
        int counterToKill = 1;

        boolean killedSomeone = false;
        boolean gameHasToContinue = false;

        NodeInterface current = list.getFirst();

        while (!killedSomeone) {
            for (int i = 0; i < this.pace; i++) {
                current = current.getNext();
                if (current.getValue()) {
                    counterToKill++;
                }
            }
            if (counterToKill == pace && current.getValue()) {
                current.setValue(false);
                survivors--;
                killedSomeone = true;
            }
        }

        if (survivors > 1) {
            gameHasToContinue = true;
        }

        return gameHasToContinue;
    }

    /**
     * @param numberOfPlayers number of players to be in the circle
     * @param list            Linked list to be used
     * @return Informs whether it generated the players or not
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
     * @param numberOfPlayers number of players to be in the circle
     * @param pace            key used to count the pace in the circle
     * @return returns if user input is valid
     */
    public boolean isDataValid(int numberOfPlayers, int pace) {
        return (numberOfPlayers > 1 && numberOfPlayers <= 50) && (pace > 1 && pace <= numberOfPlayers);
    }


}
