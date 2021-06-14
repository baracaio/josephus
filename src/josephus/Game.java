package josephus;

import ds.LinkedList;
import ds.LinkedListInterface;
import ds.NodeInterface;
import ds.Player;

public class Game implements GameInterface {
    private LinkedListInterface list;

    private int count;

    private int interval;

    private NodeInterface lastPoint;

    public Game(int pace, int numberOfPlayers) {
        count = numberOfPlayers;
        interval = pace;
        generatePlayers(numberOfPlayers);
        lastPoint = list.getFirst();
    }

    @Override
    public boolean playTurn() {
        if (count == 1) {
            return false;
        }

        NodeInterface player = lastPoint;

        return true;
    }

    @Override
    public LinkedListInterface getList() {
        return list;
    }

    private void generatePlayers(int total) {
        list = new LinkedList();
        for (int i = 0; i < total; i++) {
            list.insertEnd(new Player());
        }
    }
}
