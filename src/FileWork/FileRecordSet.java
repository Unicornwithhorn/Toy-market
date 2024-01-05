package FileWork;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;

public class FileRecordSet {
    String name;
    String surname;
    String patronic;
    String phone;
    String date;
    String gender;

    public void setDate(String date) {
        this.date = date;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronic(String patronic) {
        this.patronic = patronic;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public String getDate() {
        return date;
    }

    public String getPatronic() {
        return patronic;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname + " " + this.patronic + " " + this.date + " " +
                this.phone + " " + this.gender + "\n";
    }

    public void recordSet() {
        try {
            FileWriter fileWriter = new FileWriter(this.surname + ".txt", true);
            fileWriter.write(this.toString());
            fileWriter.flush();
        } catch (IOException f){
            f.printStackTrace();
        }
    }
}
