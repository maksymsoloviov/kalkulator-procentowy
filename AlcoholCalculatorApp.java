import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
class AlcoholCalculatorModel {
   private double vesselVolume;
   private double alcoholPercentage;
   private int vesselCount;
   public AlcoholCalculatorModel(double vesselVolume, double alcoholPercentage, int vesselCount) {
       if (vesselVolume < 0 || alcoholPercentage < 0 || vesselCount < 0) {
           throw new IllegalArgumentException("Wartości nie mogą być ujemne.");
       }
       this.vesselVolume = vesselVolume;
       this.alcoholPercentage = alcoholPercentage;
       this.vesselCount = vesselCount;
   }
   public double getTotalDrinkVolume() {
       return vesselVolume * vesselCount;
   }
   public double getPureAlcoholVolume() {
       return getTotalDrinkVolume() * (alcoholPercentage / 100.0);
   }
}
class AlcoholCalculatorFrame extends JFrame {
   private final String[] vessels = {
           "Własna wartość", "Kieliszek (50 ml)", "Szklanka (250 ml)", "Pół litra (500 ml)", "Butelka (750 ml)"
   };
   private final Double[] vesselVolumes = {0.0, 50.0, 250.0, 500.0, 750.0};
   private final String[] drinks = {
           "Własny napój", "Piwo (5%)", "Wino (12%)", "Wódka (40%)", "Spirytus (95%)"
   };
   private final Double[] drinkPercentages = {0.0, 5.0, 12.0, 40.0, 95.0};
   private JComboBox<String> vesselComboBox;
   private JTextField volumeField;
   private JComboBox<String> drinkComboBox;
   private JTextField percentageField;
   private JTextField countField;
   private JLabel resultTotalLabel;
   private JLabel resultPureLabel;
   public AlcoholCalculatorFrame() {
       setTitle("Kalkulator procentowy (Lab 02)");
       setSize(420, 380);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
       setLayout(new BorderLayout(10, 10));
       JPanel formPanel = new JPanel(new GridLayout(6, 2, 8, 8));
       formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
       formPanel.add(new JLabel("Wielkość naczynia:"));
       vesselComboBox = new JComboBox<>(vessels);
       formPanel.add(vesselComboBox);
       formPanel.add(new JLabel("Pojemność (ml) / Własna:"));
       volumeField = new JTextField();
       formPanel.add(volumeField);
       formPanel.add(new JLabel("Rodzaj napoju:"));
       drinkComboBox = new JComboBox<>(drinks);
       formPanel.add(drinkComboBox);
       formPanel.add(new JLabel("Zawartość % / Własna:"));
       percentageField = new JTextField();
       formPanel.add(percentageField);
       formPanel.add(new JLabel("Ilość sztuk naczyń:"));
       countField = new JTextField("1");
       formPanel.add(countField);
       JButton calculateButton = new JButton("Oblicz");
       formPanel.add(calculateButton);
       add(formPanel, BorderLayout.CENTER);
       JPanel resultPanel = new JPanel(new GridLayout(2, 1, 5, 5));
       resultPanel.setBorder(BorderFactory.createTitledBorder("Wyniki"));
       resultTotalLabel = new JLabel("Objętość napoju: 0.00 ml");
       resultPureLabel = new JLabel("Objętość czystego spirytusu: 0.00 ml");
       resultPanel.add(resultTotalLabel);
       resultPanel.add(resultPureLabel);
       add(resultPanel, BorderLayout.SOUTH);
       vesselComboBox.addActionListener(e -> {
           int index = vesselComboBox.getSelectedIndex();
           if (index > 0) {
               volumeField.setText(String.valueOf(vesselVolumes[index]));
               volumeField.setEnabled(false);
           } else {
               volumeField.setEnabled(true);
               volumeField.setText("");
           }
       });
       drinkComboBox.addActionListener(e -> {
           int index = drinkComboBox.getSelectedIndex();
           if (index > 0) {
               percentageField.setText(String.valueOf(drinkPercentages[index]));
               percentageField.setEnabled(false);
           } else {
               percentageField.setEnabled(true);
               percentageField.setText("");
           }
       });
       calculateButton.addActionListener((ActionEvent e) -> {
           try {
               double vol = Double.parseDouble(volumeField.getText().replace(',', '.'));
               double perc = Double.parseDouble(percentageField.getText().replace(',', '.'));
               int cnt = Integer.parseInt(countField.getText().trim());
               AlcoholCalculatorModel model = new AlcoholCalculatorModel(vol, perc, cnt);
               resultTotalLabel.setText(String.format("Objętość napoju: %.2f ml", model.getTotalDrinkVolume()));
               resultPureLabel.setText(String.format("Objętość czystego spirytusu: %.2f ml", model.getPureAlcoholVolume()));
           } catch (NumberFormatException ex) {
               JOptionPane.showMessageDialog(this, "Wprowadź poprawne dane liczbowe!", "Błąd", JOptionPane.ERROR_MESSAGE);
           } catch (IllegalArgumentException ex) {
               JOptionPane.showMessageDialog(this, ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
           }
       });
   }
}
public class AlcoholCalculatorApp {
   public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {
           new AlcoholCalculatorFrame().setVisible(true);
       });
   }
}