package MAP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovingBallGUI extends JFrame {
    private int ballX = 50;
    private int ballY = 50;
    private static final int BALL_SIZE = 30;
    private static final int MOVE_AMOUNT = 5;
    
    public MovingBallGUI() {
        setTitle("Moving Ball");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBall(g);
            }
        };

        panel.setBackground(Color.WHITE);
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        panel.getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBall(-MOVE_AMOUNT, 0);
                panel.repaint();
            }
        });
        
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        panel.getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBall(MOVE_AMOUNT, 0);
                panel.repaint();
            }
        });

        add(panel);
        setVisible(true);
    }

    private void drawBall(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);
    }

    private void moveBall(int dx, int dy) {
        int newBallX = ballX + dx;
        int newBallY = ballY + dy;
        
        if (newBallX >= 0 && newBallX <= getWidth() - BALL_SIZE) {
            ballX = newBallX;
        }
        
        if (newBallY >= 0 && newBallY <= getHeight() - BALL_SIZE) {
            ballY = newBallY;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MovingBallGUI());
    }
}
