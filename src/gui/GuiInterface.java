package gui;

import josephus.GameInterface;

public interface GuiInterface {
    public void begin(GameInterface game);

    public int getPace();

    public int getTotalPlayers();
}
