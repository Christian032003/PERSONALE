package MAP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class IndovinaNumeroGUI extends JFrame {
    private int numeroCasuale;
    private int tentativi;
    private JTextField inputField;
    private JLabel feedbackLabel;

    public IndovinaNumeroGUI() {
        setTitle("Indovina il Numero!");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Genera un numero casuale tra 1 e 100
        Random random = new Random();
        numeroCasuale = random.nextInt(100) + 1;
        tentativi = 0;

        JLabel istruzioniLabel = new JLabel("Indovina il numero tra 1 e 100:");
        inputField = new JTextField(10);
        JButton inviaButton = new JButton("Invia");
        feedbackLabel = new JLabel();

        // Listener per il bottone di invio
        inviaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int guess = Integer.parseInt(inputField.getText());
                    tentativi++;

                    if (guess < numeroCasuale) {
                        feedbackLabel.setText("Il numero da indovinare è maggiore.");
                    } else if (guess > numeroCasuale) {
                        feedbackLabel.setText("Il numero da indovinare è minore.");
                    } else {
                        feedbackLabel.setText("Complimenti! Hai indovinato il numero in " + tentativi + " tentativi.");
                        inviaButton.setEnabled(false); // Disabilita il bottone dopo che l'utente ha indovinato
                    }
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Inserisci un numero valido.");
                }
                inputField.setText(""); // Resetta il campo di input dopo ogni tentativo
            }
        });

        // Pannello per posizionare gli elementi
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(istruzioniLabel);
        panel.add(inputField);
        panel.add(inviaButton);

        add(panel, BorderLayout.CENTER);
        add(feedbackLabel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                IndovinaNumeroGUI game = new IndovinaNumeroGUI();
                game.setVisible(true);
            }
        });
    }
}
