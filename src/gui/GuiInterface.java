package gui;

import josephus.GameInterface;

public interface GuiInterface {
    public void begin(GameInterface newGame);

    public int getPace();

    public int getTotalPlayers();
}
