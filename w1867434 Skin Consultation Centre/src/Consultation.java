//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: w1867434
//Date: 09/01/2023

import java.time.LocalDate;
import java.time.LocalTime;

public class Consultation extends Person {

    private LocalDate date;
    private LocalTime time;
    private String notes;
    private int cost;

    Consultation(int cost, String notes) {
        this.cost = cost;
        this.notes = notes;
    }

    Consultation(LocalDate date, LocalTime time, String notes, int cost) {
        this.date = date;
        this.time = time;
        this.notes = notes;
        this.cost = cost;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time =time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes() {
        this.notes = notes;
    }

    public int getCost() {
        return cost;
    }

    public void setCost() {
        this.cost = cost;
    }
}