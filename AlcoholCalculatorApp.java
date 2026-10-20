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
   private JTextField volumeField;
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
       formPanel.add(new JLabel("Pojemność (ml):"));
       volumeField = new JTextField();
       formPanel.add(volumeField);
       formPanel.add(new JLabel("Zawartość %:"));
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
   }
}
public class AlcoholCalculatorApp {
   public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {
           new AlcoholCalculatorFrame().setVisible(true);
       });
   }
}