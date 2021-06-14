package josephus;

import ds.LinkedListInterface;

public interface GameInterface {
    boolean playTurn();
    LinkedListInterface getList();
}
