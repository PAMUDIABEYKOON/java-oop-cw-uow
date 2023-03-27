//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: w1867434
//Date: 09/01/2023

import java.time.LocalDate;

public class Patient extends Person {

    private int uniqueId;
    private int docMedLicenseNo;

    Patient(String name, String surname, LocalDate dob, int mobNo, int uniqueId, int docMedLicenseNo) {
        this.setName(name);
        this.setSurname(surname);
        this.setDob(dob);
        this.setMobNo(mobNo);
        this.uniqueId = uniqueId;
        this.docMedLicenseNo = docMedLicenseNo;
    }

    public int getUniqueId() {return uniqueId;}

    public void setUniqueId(int uniqueId) {this.uniqueId = uniqueId;}

    public int getDocMedLicenseNo() {return docMedLicenseNo;}

    public void setDocMedLicenseNo(int docMedLicenseNo) {this.docMedLicenseNo = docMedLicenseNo;}
}