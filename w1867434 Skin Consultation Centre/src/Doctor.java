//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: w1867434
//Date: 09/01/2023

import java.time.LocalDate;

public class Doctor extends Person {

    private int medLicenceNo;
    private String specialisation;

    Doctor(String name, String surname, LocalDate dob, int mobNo, int medLicenceNo, String specialisation) {
        this.setName(name);
        this.setSurname(surname);
        this.setDob(dob);
        this.setMobNo(mobNo);
        this.medLicenceNo = medLicenceNo;
        this.specialisation = specialisation;
    }

    public int getMedLicenceNo() {return medLicenceNo;}

    public void setMedLicenceNo(int medLicenseNo) {
        this.medLicenceNo = medLicenseNo;
    }

    public String getSpecialisation() {return specialisation;}

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
}