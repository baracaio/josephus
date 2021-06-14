package Josephus;

import ds.LinkedListInterface;

public class Game implements GameInterface {
    private LinkedListInterface list;

    private int count;

    public Game(int pace, int numberOfPlayers) {
        count = 10;
    }

    @Override
    public boolean playTurn() {
        count--;

        return count > 0;
    }

    @Override
    public LinkedListInterface getList() {
        return list;
    }
}
