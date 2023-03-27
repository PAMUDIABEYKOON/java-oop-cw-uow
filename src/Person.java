//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: w1867434
//Date: 09/01/2023

import java.time.LocalDate;

public class Person {

    private String name;
    private String surname;
    private LocalDate dob;
    private int mobNo;

    Person() {
    }

    Person(String name, String surname, LocalDate dob, int mobNo) {
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.mobNo = mobNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getMobNo() {
        return mobNo;
    }

    public void setMobNo(int mobNo) {
        this.mobNo = mobNo;
    }
}