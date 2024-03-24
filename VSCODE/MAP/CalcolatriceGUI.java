package MAP;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcolatriceGUI extends JFrame {
    private JTextField display; // Campo di testo per visualizzare gli input e i risultati
    private double num1, num2; // Variabili per memorizzare i numeri inseriti dall'utente
    private char operatore; // Variabile per memorizzare l'operatore selezionato dall'utente

    public CalcolatriceGUI() {
        setTitle("Calcolatrice"); // Imposta il titolo della finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Chiudi l'applicazione quando la finestra viene chiusa
        setLayout(new BorderLayout()); // Imposta il layout della finestra

        display = new JTextField(); // Crea un nuovo campo di testo
        display.setEditable(false); // Imposta il campo di testo come non modificabile
        display.setHorizontalAlignment(JTextField.RIGHT); // Allinea il testo a destra nel campo di testo
        add(display, BorderLayout.NORTH); // Aggiunge il campo di testo nella parte superiore della finestra

        JPanel panel = new JPanel(); // Crea un nuovo pannello per i pulsanti
        panel.setLayout(new GridLayout(4, 4)); // Imposta il layout del pannello come una griglia 4x4

        String[] buttonLabels = { // Array di stringhe contenente le etichette dei pulsanti
                "7", "8", "9", "/", // Prima riga di pulsanti
                "4", "5", "6", "*", // Seconda riga di pulsanti
                "1", "2", "3", "-", // Terza riga di pulsanti
                "0", ".", "=", "+"  // Quarta riga di pulsanti
        };

        for (String label : buttonLabels) { // Itera attraverso l'array di etichette dei pulsanti
            JButton button = new JButton(label); // Crea un nuovo pulsante con l'etichetta corrente
            button.addActionListener(new ButtonClickListener()); // Aggiunge un ascoltatore di azioni al pulsante
            panel.add(button); // Aggiunge il pulsante al pannello
        }

        add(panel, BorderLayout.CENTER); // Aggiunge il pannello dei pulsanti nella parte centrale della finestra

        pack(); // Adatta le dimensioni della finestra ai componenti al suo interno
        setLocationRelativeTo(null); // Imposta la finestra al centro dello schermo
    }

    // Classe interna che gestisce l'azione di clic sui pulsanti
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand(); // Ottiene il comando associato all'azione dell'evento

            switch (command) {
                case "+":
                case "-":
                case "*":
                case "/":
                    num1 = Double.parseDouble(display.getText()); // Ottiene il primo numero dalla stringa nel campo di testo
                    operatore = command.charAt(0); // Ottiene l'operatore dall'etichetta del pulsante
                    display.setText(""); // Cancella il campo di testo per l'inserimento del secondo numero
                    break;
                case "=":
                    num2 = Double.parseDouble(display.getText()); // Ottiene il secondo numero dalla stringa nel campo di testo
                    double risultato = calcolaRisultato(num1, num2, operatore); // Calcola il risultato dell'operazione
                    display.setText(String.valueOf(risultato)); // Visualizza il risultato nel campo di testo
                    break;
                default:
                    display.setText(display.getText() + command); // Aggiunge il testo del pulsante al campo di testo
            }
        }
    }

    // Metodo per calcolare il risultato dell'operazione
    private double calcolaRisultato(double num1, double num2, char operatore) {
        double risultato = 0;

        // Switch per determinare l'operazione da eseguire in base all'operatore
        switch (operatore) {
            case '+':
                risultato = num1 + num2;
                break;
            case '-':
                risultato = num1 - num2;
                break;
            case '*':
                risultato = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    risultato = num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(null, "Errore: Divisione per zero!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }

        return risultato; // Restituisce il risultato dell'operazione
    }

    // Metodo principale per l'avvio dell'applicazione
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalcolatriceGUI().setVisible(true)); // Crea e visualizza la finestra della calcolatrice
    }
}
