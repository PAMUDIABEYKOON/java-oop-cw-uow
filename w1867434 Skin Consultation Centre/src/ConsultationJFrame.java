//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: w1867434
//Date: 09/01/2023

import org.jasypt.util.text.StrongTextEncryptor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ConsultationJFrame extends JFrame {

    DefaultTableModel model2;
    JTable patientTable;
    JScrollPane scrollPane2;
    JPanel panel4;
    JPanel panel5;
    public ConsultationJFrame(ArrayList<Patient> patients, ArrayList<Consultation> consultations){

        this.setTitle("Skin Consultation Centre - Consultations");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(1000, 500);
        this.setVisible(true);
        this.setResizable(true);

        panel4 = new JPanel();
        panel4.setLayout(null);
        panel4.setBackground(new Color(201, 187, 170));
        panel4.setBounds(0, 0, 1000, 50);
        this.add(panel4);


        panel5 = new JPanel();
        panel5.setLayout(null);
        panel5.setBackground(Color.white);
        panel5.setBounds(0, 50, 1000, 450);
        this.add(panel5);

        JLabel patientLabel = new JLabel("ALL CONSULTATIONS - PATIENTS INFORMATION");
        patientLabel.setBounds(350, 20, 300, 25);
        patientLabel.setForeground(Color.WHITE);
        panel4.add(patientLabel);

        //        >>>> PATIENT TABLE <<<<
        model2 = new DefaultTableModel();
        patientTable = new JTable(model2);

        model2.addColumn("Unique ID");
        model2.addColumn("Name");
        model2.addColumn("Surname");
        model2.addColumn("Mobile No");
        model2.addColumn("Date of Birth");
        model2.addColumn("Doctor License No");
        model2.addColumn("Consultation Cost");
        model2.addColumn("Notes");

        // Decryption
        String decryptedNotes = "";
        String key = "Bar123Bar123";

        StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
        textEncryptor.setPassword(key);

        for (int i = 0; i < patients.size(); i++) {
            // decrypt
            decryptedNotes = textEncryptor.decrypt(consultations.get(i).getNotes());

            model2.addRow(new Object[]{
                    patients.get(i).getUniqueId(),
                    patients.get(i).getName(),
                    patients.get(i).getSurname(),
                    patients.get(i).getMobNo(),
                    patients.get(i).getDob(),
                    patients.get(i).getDocMedLicenseNo(),
                    consultations.get(i).getCost(),
                    decryptedNotes,
            });
        }

        scrollPane2 = new JScrollPane(patientTable);
        scrollPane2.setSize(960, 400);
        scrollPane2.setBounds(20, 60, 950, 400);
        scrollPane2.setBackground(Color.white);
        patientTable.setFillsViewportHeight(true);
        panel5.add(scrollPane2);
    }
}