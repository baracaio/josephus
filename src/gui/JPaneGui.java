package gui;

import Josephus.GameInterface;
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

    private LinkedListInterface list;

    public JPaneGui(int width, int height) {
        frame = new JFrame("Algorigmo de Josephus");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMenu();
        setBottom();

        this.width = width;
        this.height = height;
        interval = 0;
        total = 0;

        renderAnimation = true;
    }

    @Override
    public void begin() {
        frame.setVisible(true);
    }

    @Override
    public void run() {
        try {
            while (renderAnimation) {
                // draw();
            }
        } catch (Exception exception) {

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
        btnPace.addActionListener(this.setPace());


        menu.add(btnPace);
        menu.add(btnTotalPlayers);

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

        bottom.add(start);
        bottom.add(stop);

        frame.add(bottom);
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
