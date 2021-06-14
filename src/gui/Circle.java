package gui;

import ds.LinkedListInterface;
import ds.NodeInterface;

import javax.swing.*;
import java.awt.*;

public class Circle extends JPanel {
    private static int POINT_SIZE = 8;

    private int posX;
    private int posY;
    private int total;
    private int rad;
    private NodeInterface node;

    public Circle(int x, int y, int max, int radian) {
        posX = x;
        posY = y;
        rad = radian;
        total = max;

        this.setBounds(0, 100, 400, 400);
    }

    public void setNode(NodeInterface node) {
        this.node = node;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        draw(g, posX, posY, rad, total);
    }

    private void draw(Graphics graph, int posX, int posY, int rad, int total) {
        int decayList = total;

        double playersSpacement = 2 * Math.PI / total;

        while(decayList != 0) {

            double cos = Math.cos(decayList * playersSpacement);
            double sin = Math.sin(decayList * playersSpacement);

            int coordX = (int) (cos * rad + posX);
            int coordY = (int) (sin * rad + posY);

            if(node.getValue()) {
                graph.setColor(Color.GREEN);
            } else {
                graph.setColor(Color.RED);
            }

            graph.fillRect(coordX, coordY, POINT_SIZE, POINT_SIZE);
            decayList--;
        }
    }
}
