//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: w1867434
//Date: 09/01/2023

import org.jasypt.util.text.StrongTextEncryptor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.toedter.calendar.JCalendar;

public class FormJFrame extends JFrame implements ActionListener {

    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Consultation> consultations = new ArrayList<>();
    JPanel panel3;
    JPanel panel4;
    JButton addPatientBtn = new JButton("Add Patient");
    JButton viewConsultationsBtn = new JButton("View Consultations");
    JTextField Text1;
    JTextField Text2;
    JCalendar calendar;
    JTextField Text4;
    JTextField Text5;
    JTextField Text6;
    DefaultTableModel model1;
    int medLicenseNo;
    JLabel Label7;
    JLabel Label8;
    int day;
    int month;
    int year;


    public FormJFrame(JButton sortBtn, JButton consultBtn, DefaultTableModel model1, String value) {

        this.medLicenseNo = Integer.parseInt(value);

        this.setTitle("Skin Consultation Centre - Add Patient");
        this.model1 = model1;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 600);
        this.setVisible(true);
        this.setResizable(false);


        //        >>>> TITLE OF THE FORM <<<<
        panel3 = new JPanel();
        panel3.setLayout(null);
        panel3.setBackground(new Color(201, 187, 170));
        panel3.setBounds(0, 0, 450, 50);
        this.add(panel3);

        JLabel fLabel = new JLabel("ADD PATIENT INFORMATION");
        fLabel.setBounds(140, 20, 180, 25);
        fLabel.setForeground(Color.white);
        panel3.add(fLabel);


        //        >>>> FORM <<<<
        panel4 = new JPanel();
        panel4.setLayout(null);
        panel4.setBackground(Color.white);
        panel4.setBounds(0, 50, 450, 500);
        this.add(panel4);


        JLabel Label1 = new JLabel("Name");
        Label1.setBounds(10, 60, 140, 25);
        panel4.add(Label1);

        Text1 = new JTextField(20);
        Text1.setBounds(200, 60, 220, 25);
        panel4.add(Text1);

        JLabel Label2 = new JLabel("Surname");
        Label2.setBounds(10, 90, 140, 25);
        panel4.add(Label2);

        Text2 = new JTextField(20);
        Text2.setBounds(200, 90, 220, 25);
        panel4.add(Text2);

        JLabel Label3 = new JLabel("Date of Birth");
        Label3.setBounds(10, 120, 160, 25);
        panel4.add(Label3);

        calendar = new JCalendar();
        calendar.setBounds(200,120,220,130);
        calendar.setVisible(true);
        panel4.add(calendar);
        calendar.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Date selectedDate = calendar.getDate();
                Calendar c = Calendar.getInstance();
                c.setTime(selectedDate);
                day = c.get(Calendar.DATE);
                month = c.get(Calendar.MONTH) + 1;
                year = c.get(Calendar.YEAR);
            }
        });


        JLabel Label4 = new JLabel("Mobile No");
        Label4.setBounds(10, 280, 140, 25);
        panel4.add(Label4);

        Text4 = new JTextField(10);
        Text4.setBounds(200, 280, 220, 25);
        panel4.add(Text4);

        JLabel Label5 = new JLabel("Unique ID");
        Label5.setBounds(10, 310, 140, 25);
        panel4.add(Label5);

        Text5 = new JTextField(10);
        Text5.setBounds(200, 310, 220, 25);
        panel4.add(Text5);

        JLabel Label6 = new JLabel("Notes");
        Label6.setBounds(10, 340, 140, 25);
        panel4.add(Label6);

        Text6 = new JTextField();;
        Text6.setBounds(200, 340, 220, 25);
        panel4.add(Text6);


        addPatientBtn.setBounds(210, 400, 165, 25);
        addPatientBtn.setBackground(Color.white);
        addPatientBtn.addActionListener((ActionListener) this);
        panel4.add(addPatientBtn);


        //      >>>> PATIENT ADDED LABELS <<<<
        Label7 = new JLabel();
        Label7.setBounds(50, 450, 165, 25);
        Label7.setVisible(false);
        panel4.add(Label7);

        Label8 = new JLabel();
        Label8.setBounds(50, 480, 165, 25);
        Label8.setVisible(false);
        panel4.add(Label8);


        //        >>>> VIEW CONSULTATION BUTTON <<<<
        viewConsultationsBtn.setBounds(210, 510, 165, 25);
        viewConsultationsBtn.setBackground(new Color(201, 187, 170));
        viewConsultationsBtn.setForeground(Color.white);
        viewConsultationsBtn.addActionListener((ActionListener) this);
        panel4.add(viewConsultationsBtn);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPatientBtn) {

            String inputName = Text1.getText();
            inputName = inputName.substring(0, 1).toUpperCase() + inputName.substring(1).toLowerCase();

            String inputSurname = Text2.getText();
            inputSurname = inputSurname.substring(0, 1).toUpperCase() + inputSurname.substring(1).toLowerCase();

            String strDay = String.valueOf(day);
            String strMonth = String.valueOf(month);
            String strYear = String.valueOf(year);

            if (month < 10) {
                strMonth = "0" + strMonth;
            }

            if (day < 10) {
                strDay = "0" + strDay;
            }

            String stringDoB = (strYear+"-"+strMonth+"-"+strDay);
            LocalDate inputDoB = LocalDate.parse(stringDoB);

            int inputMobNo = Integer.parseInt(Text4.getText());
            int inputID = Integer.parseInt(Text5.getText());

            String inputNotes = Text6.getText();

            // Encryption
            String encryptedNotes = "";
            try {
                String key = "Bar123Bar123";

                StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
                textEncryptor.setPassword(key);

                // encrypt
                encryptedNotes = textEncryptor.encrypt(inputNotes);
                System.out.println(encryptedNotes);
            } catch (Exception ea) {
                ea.printStackTrace();
            }

            Patient patient = new Patient(inputName, inputSurname, inputDoB, inputMobNo, inputID, medLicenseNo);
            patients.add(patient);

            int previousConsultationCount = 0;
            for (int i = 0; i < patients.size(); i++) {
                if (inputID == patients.get(i).getUniqueId()) {
                    previousConsultationCount += 1;
                }
            }
            int cost = 15;
            if (previousConsultationCount > 1) {
                cost = 25;
            }

            Consultation consultation = new Consultation(cost, encryptedNotes);
            consultations.add(consultation);

            Label7.setText("Patient " + inputID + " is Added");
            Label7.setVisible(true);

            Label8.setText("Consultation Cost : £" + cost);
            Label8.setVisible(true);

            Text1.setText(null);
            Text2.setText(null);
            Text4.setText(null);
            Text5.setText(null);
            Text6.setText(null);

        } else if (e.getSource() == viewConsultationsBtn) {
            ConsultationJFrame consultationJFrame = new ConsultationJFrame(patients, consultations);
        }
    }
}