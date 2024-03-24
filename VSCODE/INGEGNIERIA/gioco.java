package INGEGNIERIA;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class gioco extends JFrame {
    private JButton cartaButton, sassoButton, forbiciButton;
    private JLabel resultLabel;

    public gioco() {
        setTitle("Morra Cinese Game");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        // Pannello per i pulsanti degli omini
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        cartaButton = createButton("carta.png");
        sassoButton = createButton("sasso.png");
        forbiciButton = createButton("forbici.png");

        cartaButton.addActionListener(new MorraListener("Carta"));
        sassoButton.addActionListener(new MorraListener("Sasso"));
        forbiciButton.addActionListener(new MorraListener("Forbici"));

        buttonPanel.add(cartaButton);
        buttonPanel.add(sassoButton);
        buttonPanel.add(forbiciButton);
        add(buttonPanel);

        // Pannello per il risultato
        resultLabel = new JLabel("");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(resultLabel);

        setVisible(true);
    }

    private JButton createButton(String imagePath) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            ImageIcon icon = new ImageIcon(originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            return new JButton(icon);
        } catch (IOException e) {
            e.printStackTrace();
            return new JButton("Error");
        }
    }

    // Classe interna per gestire gli eventi della morra
    private class MorraListener implements ActionListener {
        private String choice;

        public MorraListener(String choice) {
            this.choice = choice;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String computerChoice = generateComputerChoice();
            String result = determineWinner(choice, computerChoice);
            resultLabel.setText(result);
        }

        private String generateComputerChoice() {
            int randomNum = (int) (Math.random() * 3); // 0 - Carta, 1 - Sasso, 2 - Forbici
            switch (randomNum) {
                case 0:
                    return "Carta";
                case 1:
                    return "Sasso";
                default:
                    return "Forbici";
            }
        }

        private String determineWinner(String playerChoice, String computerChoice) {
            if (playerChoice.equals(computerChoice))
                return "Draw!";
            else if ((playerChoice.equals("Carta") && computerChoice.equals("Sasso")) ||
                     (playerChoice.equals("Sasso") && computerChoice.equals("Forbici")) ||
                     (playerChoice.equals("Forbici") && computerChoice.equals("Carta")))
                return "You win!";
            else
                return "Computer wins!";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new gioco();
            }
        });
    }
}
