package gui;

import ds.NodeInterface;
import josephus.GameInterface;
import ds.LinkedListInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPaneGui extends Thread implements GuiInterface {

    private int width;

    private int height;

    private boolean renderAnimation;

    private int interval;

    private int total;

    private JFrame frame;

    private JPanel menu;

    private JPanel bottom;

    private Circle circle;

    private LinkedListInterface list;

    private GameInterface game;

    public JPaneGui(int width, int height) {
        frame = new JFrame("Algorigmo de Josephus");
        frame.setSize(width, height);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMenu();
        setBottom();

        this.width = width;
        this.height = height;
        interval = 2;
        total = 50;

        renderAnimation = true;
    }

    @Override
    public void begin(GameInterface game) {
        this.game = game;
        list = game.getList();
        circle = new Circle(200, 200, list.getSize(), 150);
        circle.setNode(game.getList().getFirst());
        frame.add(circle);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        try {
            NodeInterface node;
            while (game.playTurn() && renderAnimation) {
                node = game.getList().getFirst();

                do {
                    circle.setNode(node);

                    node = node.getNext();
                    sleep(1000);
                } while (node != game.getList().getFirst());
            }
        } catch (InterruptedException exception) {
            JOptionPane.showMessageDialog(frame, "Deu ruim, mano");
        }
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


        menu.add("North", btnPace);
        menu.add("North", btnTotalPlayers);

        frame.add(menu);
    }

    private void setBottom() {
        bottom = new JPanel();
        bottom.setBounds(0, height - 100, width, 100);
        JButton start = new JButton("Iniciar simulação");
        start.setBounds(10, height - 60, 60, 40);
        start.addActionListener(render());

        JButton stop = new JButton("Encerrar");
        stop.setBounds(60, height - 60, 60, 40);
        stop.addActionListener(stopRendering());

        bottom.add("South", start);
        bottom.add("South", stop);

        frame.add("South", bottom);
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
                    JOptionPane.showMessageDialog(frame, "O valor informado precisa ser um inteiro positivo.");
                } catch (IllegalArgumentException exception) {
                    String message = "O valor informado precisa ser maior do que zero e menor do que o total de elementos. Atualmente, o total de elementos é igual a " + total;
                    JOptionPane.showMessageDialog(frame, message);
                }
            }
        };
    }

    private ActionListener setTotal() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int totalElementos = Integer.parseInt(JOptionPane.showInputDialog("Informe o total de elementos"));

                    if (totalElementos < 1 || totalElementos > 50) {
                        throw new IllegalArgumentException("O valor informado não é válido. Informe um número entre 1 e 50.");
                    }

                    total = totalElementos;
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(frame, "O valor informado precisa ser um inteiro positivo.");
                } catch (IllegalArgumentException exception) {
                    JOptionPane.showMessageDialog(frame, exception.getMessage());
                }
            }
        };
    }

    private ActionListener render() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        };
    }

    private ActionListener stopRendering() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderAnimation = false;
            }
        };
    }
}
