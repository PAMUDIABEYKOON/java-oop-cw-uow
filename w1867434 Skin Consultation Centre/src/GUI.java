//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: w1867434
//Date: 09/01/2023

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//
public class GUI extends JFrame implements ActionListener {

    JFrame frame;
    JPanel panel1;
    JPanel panel2;
    JLabel label;
    JButton sortBtn;
    JButton consultBtn;
    DefaultTableModel model1;
    JTable docTable;
    JScrollPane scrollPane1;

    public GUI() {

        frame = new JFrame("Skin Consultation Centre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);

        panel1 = new JPanel();
        panel1.setBackground(new Color(201, 187, 170));
        panel1.setBounds(0, 0, 800, 50);
        frame.add(panel1);

        panel2 = new JPanel();
        panel2.setBackground(Color.white);
        panel2.setBounds(0, 50, 800, 550);
        frame.add(panel2);


        //        >>>> TITLE OF THE TABLE <<<<

        label = new JLabel("SKIN CONSULTATION CENTER - DOCTORS");
        label.setBounds(280, 25, 300, 20);
        label.setForeground(Color.white);
        panel1.add(label);


        //        >>>> PANEL 2 - BUTTONS & DOCTOR TABLE <<<<

        sortBtn = new JButton("Sort Doctors A-Z");
        sortBtn.setBounds(100, 30, 150, 20);
        sortBtn.setBackground(Color.white);
        sortBtn.addActionListener(this);
        panel2.add(sortBtn);

        consultBtn = new JButton("Consultation");
        consultBtn.setBounds(530, 30, 150, 20);
        consultBtn.setBackground(Color.white);
        consultBtn.addActionListener(this);
        panel2.add(consultBtn);

        model1 = new DefaultTableModel();
        docTable = new JTable(model1);

        model1.addColumn("Licence No");     //      Column Names
        model1.addColumn("Name");
        model1.addColumn("Surname");
        model1.addColumn("Mobile No");
        model1.addColumn("Specialisation");

        for (int i = 0; i < WestminsterSkinConsultationManager.doctors.size(); i++) {   //      Row Data
            model1.addRow(new Object[]{
                    WestminsterSkinConsultationManager.doctors.get(i).getMedLicenceNo(),
                    WestminsterSkinConsultationManager.doctors.get(i).getName(),
                    WestminsterSkinConsultationManager.doctors.get(i).getSurname(),
                    WestminsterSkinConsultationManager.doctors.get(i).getMobNo(),
                    WestminsterSkinConsultationManager.doctors.get(i).getSpecialisation(),
            });
        }

        scrollPane1 = new JScrollPane(docTable);
        scrollPane1.setSize(700, 400);
        scrollPane1.setBounds(50, 80, 700, 400);
        scrollPane1.setBackground(Color.white);
        docTable.setFillsViewportHeight(true);
        panel2.add(scrollPane1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sortBtn) {
            for (int i = model1.getRowCount() - 1; i >= 0; i--) {
                model1.removeRow(i);
            }
            sortTable();

        } else if (e.getSource() == consultBtn) {

            int selectedRow = docTable.getSelectedRow();

            if (selectedRow != -1) {
                // get the model of the table
                DefaultTableModel model1 = (DefaultTableModel) docTable.getModel();

                // get the value at the selected row and column
                String value = model1.getValueAt(selectedRow, 0).toString();

                FormJFrame formJframe = new FormJFrame(sortBtn, consultBtn, model1, value);
            }
        }
    }


    public static void main() {

        GUI gui = new GUI();
    }


    public void sortTable() {

        ArrayList<String> lastnames = new ArrayList<>();

        // sorting surnames in lastname array
        for (int i = 0; i < WestminsterSkinConsultationManager.doctors.size(); i++) {
            lastnames.add(WestminsterSkinConsultationManager.doctors.get(i).getSurname());
        }

        // setting surnames in alphabetical order
        for (int i = 0; i < lastnames.size(); i++) {
            for (int j = i + 1; j < lastnames.size(); j++) {
                String temp;
                if (lastnames.get(i).compareTo(lastnames.get(j)) > 0) {
                    temp = lastnames.get(i);
                    lastnames.set(i, lastnames.get(j));
                    lastnames.set(j, temp);
                }
            }
        }

        // printing the doctor list with relevant information in alphabetical order
        for (int i = 0; i < lastnames.size(); i++) {
            for (int j = 0; j < WestminsterSkinConsultationManager.doctors.size(); j++) {
                if (lastnames.get(i) == WestminsterSkinConsultationManager.doctors.get(j).getSurname()) {
                    model1.addRow(new Object[]{
                            WestminsterSkinConsultationManager.doctors.get(j).getMedLicenceNo(),
                            WestminsterSkinConsultationManager.doctors.get(j).getName(),
                            WestminsterSkinConsultationManager.doctors.get(j).getSurname(),
                            WestminsterSkinConsultationManager.doctors.get(j).getMobNo(),
                            WestminsterSkinConsultationManager.doctors.get(j).getSpecialisation(),
                    });
                }
            }
        }
    }
}