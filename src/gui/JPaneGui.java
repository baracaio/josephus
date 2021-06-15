package gui;

import ds.NodeInterface;
import josephus.Game;
import josephus.GameInterface;
import ds.LinkedListInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPaneGui extends JFrame implements GuiInterface {

    private static int POINT_SIZE = 8;

    private int width;

    private int height;

    private boolean renderAnimation;

    private int interval;

    private int total;


    private JPanel menu;

    private LinkedListInterface list;

    private GameInterface game;

    public JPaneGui(int width, int height, GameInterface game) {
        this.width = width;
        this.height = height;
        this.game = game;
        this.list = game.getList();
        renderAnimation = true;
        interval = 2;
        total = 50;

        this.setSize(width, height);
        this.setLayout(new GridLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMenu();

        this.setVisible(true);
    }

    @Override public void paint(Graphics g) {
        super.paint(g);
        drawCircle(g, 300, 300, 150, list.getSize());
    }

    public void drawCircle(
            Graphics g,
            int px,
            int py,
            int r,
            int total
    ) {
        int i = 1;
        double distance = 2 * Math.PI / total;
        NodeInterface node = list.getFirst();

        do {
            double cos = Math.cos(i * distance);
            double sin = Math.sin(i * distance);

            int x = (int) ( cos * r + px );
            int y = (int) ( sin * r + py );

            if (node.getValue()) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.RED);
            }

            g.fillRect(x, y, POINT_SIZE, POINT_SIZE);

            node = node.getNext();
            i++;
        } while (node != list.getFirst());
    }

    @Override
    public void begin(GameInterface newGame) {

    }

    @Override
    public int getPace() {
        return interval;
    }

    @Override
    public int getTotalPlayers() {
        return total;
    }

    private void setMenu() {
        menu = new JPanel();
        menu.setBounds(0, 0, width, 100);

        JButton btnTotalPlayers = new JButton("Definir total de elementos");
        btnTotalPlayers.setBounds(10, 10, 60, 40);
        btnTotalPlayers.addActionListener(this.setTotal());

        JButton btnPace = new JButton("Definir intervalo");
        btnPace.setBounds(10, 60, 60, 40);
        btnPace.addActionListener(setPace());

        JButton start = new JButton("Iniciar simulação");
        start.setBounds(10, height - 60, 60, 40);
        start.addActionListener(render());

        JButton stop = new JButton("Encerrar");
        stop.setBounds(60, height - 60, 60, 40);
        stop.addActionListener(stopRendering());

        menu.add(btnPace);
        menu.add(btnTotalPlayers);
        menu.add(start);
        menu.add(stop);

        this.add(menu);
    }

    private void recreate() {
        if (list == null || game == null) {
            game = new Game(50, 2);
            list = game.getList();

            return;
        }

        game = new Game(getTotalPlayers(), getPace());
        list = game.getList();
    }

    private void wipeList() {
        while (list.getSize() > 0) {
            list.removeBeginning();
        }
    }

    private ActionListener setPace() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int pace = Integer.parseInt(JOptionPane.showInputDialog("Informe um intervalo"));

                    if (pace < 0 || pace > total) {
                        throw new IllegalArgumentException("O valor informado não é válido.");
                    }

                    interval = pace;

                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "O valor informado precisa ser um inteiro positivo.");
                } catch (IllegalArgumentException exception) {
                    String message = "O valor informado precisa ser maior do que zero e menor do que o total de elementos. Atualmente, o total de elementos é igual a " + total;
                    JOptionPane.showMessageDialog(null, message);
                }
            }
        };
    }

    private ActionListener setTotal() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer totalElementos = Integer.parseInt(JOptionPane.showInputDialog("Informe o total de elementos"));

                    if (totalElementos < 1 || totalElementos > 50) {
                        throw new IllegalArgumentException("O valor informado não é válido. Informe um número entre 1 e 50.");
                    }

                    total = totalElementos;
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "O valor informado precisa ser um inteiro positivo.");
                } catch (IllegalArgumentException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        };
    }

    private ActionListener render() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    recreate();
                    Simulation simulation = new Simulation();
                    renderAnimation = true;
                    simulation.start();
                } catch (IllegalThreadStateException exception) {
                    JOptionPane.showMessageDialog(null,"Houve um erro inesperado");
                    System.exit(1);
                }
            }
        };
    }

    private ActionListener stopRendering() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderAnimation = false;
                game = null;
                wipeList();
                list = null;
                JOptionPane.showMessageDialog(null, "Jogo encerrado, jogue novamente ou feche o programa.");
            }
        };
    }

    private class Simulation extends Thread {
        public void run() {
            try {
                while (game.playTurn() && renderAnimation) {
                    repaint();
                    sleep(300);
                }
                getWinner();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Houve um erro inesperado.");
                System.exit(2);
            }
        }
    }

    private void getWinner() {
        NodeInterface node = list.getFirst();
        int i = 1;
        while (!node.getValue()) {
            node = node.getNext();
            i++;
        }
        JOptionPane.showMessageDialog(null, "Winner: " + i);
    }

}
