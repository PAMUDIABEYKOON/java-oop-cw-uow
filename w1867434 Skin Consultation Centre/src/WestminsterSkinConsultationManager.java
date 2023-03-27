//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: w1867434
//Date: 09/01/2023

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    public static Scanner input = new Scanner(System.in);
    static ArrayList<Doctor> doctors = new ArrayList<>();

    //    MENU CONSOLE
    public static void main(String[] args) throws IOException {

        while (true) {

            System.out.println(">>>>>>> Menu <<<<<<<");
            System.out.println("Enter the option no from below options\n");
            System.out.println("A: Add a new doctor");
            System.out.println("D: Delete a doctor");
            System.out.println("P: Print the list of doctors");
            System.out.println("S: Save in a file");
            System.out.println("L: Load saved Information");
            System.out.println("G: Open the GUI");
            System.out.println("E: Exit thE Programme\n");

            String option = input.next().toUpperCase();

            switch (option) {
                case "A":
                    addDoctor();
                    break;
                case "D":
                    deleteDoctor();
                    break;
                case "P":
                    printDoctorListAlphabeticalOder();
                    break;
                case "S":
                    saveDoctorList();
                    break;
                case "L":
                    loadProgram();
                    break;
                case "G":
                    GUI.main();
                    break;
                case "E":
                    System.out.println("You have successfully exist the programme");
                    System.exit(0);
                default:
                    System.out.println("Please enter a valid input character\n");
                    break;
            }
        }
    }

    public static void addDoctor() {
        try {

//            Getting only 10 inputs
            if (doctors.size() < 11) {

//                Getting doctor information
                System.out.println("Name: ");
                String name = input.next();
                name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
                System.out.println("Surname: ");
                String surname = input.next();
                surname = surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase();

//                Validating the date
                Boolean invalidDate = true;

                LocalDate dob = null;
                while (invalidDate) {
                    try {
                        System.out.println("Date of Birth: (Enter in YYYY-MM-DD format)");
                        String dobString = input.next();
                        dob = LocalDate.parse(dobString);
                        invalidDate = false;
                    } catch (DateTimeParseException e) {
                        System.out.println("Wrong date format");
                    }
                }

//                Validating the mobile number
                Boolean invalidMobile = true;
                int mobNo = 0;

                while (invalidMobile) {
                    System.out.println("Mobile No: ");
                    mobNo = input.nextInt();

                    if (Integer.toString(mobNo).length() != 9) {
                        System.out.println("Invalid Mobile No");
                    } else {
                        invalidMobile = false;
                    }
                }

//                Validating Medical License No
                Boolean invalidMedNo = true;
                int medNo = 0;

                while (invalidMedNo) {
                    System.out.println("Medical License No (4 Digits): ");
                    medNo = input.nextInt();

                    if (Integer.toString(medNo).length() != 4) {
                        System.out.println("Invalid Medical License No");
                    } else {
                        invalidMedNo = false;
                    }
                }

//                Validating the specialisation number
                Boolean invalidSpecNo = true;
                int spec = 0;
                String specName = "";

                while (invalidSpecNo) {
                    System.out.println("Specialisation No: ");
                    System.out.println("    Cosmetic dermatology - 001\n    Medical dermatology - 002\n    Paediatric dermatology - 003");
                    spec = input.nextInt();

                    if (spec == 001) {
                        specName = "Cosmetic Dermatology";
                        invalidSpecNo = false;

                    } else if (spec == 002) {
                        specName = "Medical Dermatology";
                        invalidSpecNo = false;

                    } else if (spec == 003) {
                        specName = "Paediatric Dermatology";
                        invalidSpecNo = false;

                    } else {
                        System.out.println("Invalid Specialization Number");
                    }
                }

//                Creating a doctor object and adding it to the doctor arraylist
                Doctor doctor = new Doctor(name, surname, dob, mobNo, medNo, specName);
                doctors.add(doctor);
                System.out.println("Doctor added successfully!\n");

            } else {
                System.out.println("Centre is full. Cannot allocate a new doctor");
            }

        } catch (InputMismatchException e) {
            System.out.println("Integers Only");
        }
    }


    public static void printDoctorListAlphabeticalOder() {

        ArrayList<String> lastnames = new ArrayList<>();
        System.out.println(">>>> Doctors List <<<<\n");

        // Sorting surnames in lastname array
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            lastnames.add(doctor.getSurname());
        }

        // Setting surnames in alphabetical order
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

        // Printing the doctor list with relevant information in alphabetical order
        for (int i = 0; i < lastnames.size(); i++) {
            for (int j = 0; j < doctors.size(); j++) {
                Doctor doctor = doctors.get(j);
                if (lastnames.get(i) == doctor.getSurname()) {
                    System.out.println("Surname : " + doctor.getSurname());
                    System.out.println("Name : " + doctor.getName());
                    System.out.println("Date of Birth : " + doctor.getDob());
                    System.out.println("Mobile No : " + doctor.getMobNo());
                    System.out.println("Licence No : " + doctor.getMedLicenceNo());
                    System.out.println("Specialisation : " + doctor.getSpecialisation());
                    System.out.println();
                }
            }
        }
    }

    public static void deleteDoctor() {
        try {
            System.out.println("Enter the medical license no to delete the doctor");
            int medNoToRemove = input.nextInt();

//            Looping the arraylist to find the entered medLicense No
            for (int i = 0; i < doctors.size(); i++) {
                Doctor doctor = doctors.get(i);
                if (doctor.getMedLicenceNo() == medNoToRemove) {
                    doctors.remove(doctor);

//                    Displaying deleted information
                    System.out.println("Successfully removed the following doctor");
                    System.out.println("Dr. " + doctor.getName() + " " + doctor.getSurname());
                    System.out.println("Medical Licence No: " + doctor.getMedLicenceNo());
                    System.out.println("Specialisation: " + doctor.getSpecialisation());
                    System.out.println("Mobile No: " + doctor.getMobNo());
                    System.out.println("\nAvailable doctors in the centre:" + doctors.size());
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }

    public static void saveDoctorList() throws IOException {

//        Creating a new file
        try (FileWriter myFile = new FileWriter("doctorsInfo.txt")) {
            for (int i = 0; i < doctors.size(); i++) {
                Doctor doctor = doctors.get(i);
                myFile.write("Dr. " + doctor.getName() +  " " + doctor.getSurname());
                myFile.write("\nMedical Licence No: " + doctor.getMedLicenceNo());
                myFile.write("\nSpecialisation: " + doctor.getSpecialisation());
                myFile.write("\nMobile No: " + doctor.getMobNo());
                myFile.write("\nDate of Birth : " + doctor.getDob());
                myFile.write("\n\n");
            }
            myFile.close();
            System.out.println("Doctors Information saved successfully");
        }
    }

    public static void loadProgram() throws FileNotFoundException {

//        Retrieving the saved information from the file
        try {
            Scanner infoRead = new Scanner(new File("doctorsInfo.txt"));
            while (infoRead.hasNext()) {
                System.out.println(infoRead.nextLine());
            }
            System.out.println();
            System.out.println("Doctors Information loaded successfully");
            infoRead.close();
        } catch (Exception e) {
            System.err.println("No data found in the file");
            input.nextLine();
        }
    }
}