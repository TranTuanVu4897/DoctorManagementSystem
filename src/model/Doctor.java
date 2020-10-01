/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author TranVu
 */
public class Doctor {
    int id;
    String name;
    String dateOfBirth;
    String specialization;
    int availability;
    String email;
    String mobile;

    public Doctor() {
    }

    public Doctor(int id, String name, String dateOfBirth, String specialization, int availability, String email, String mobile) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.specialization = specialization;
        this.availability = availability;
        this.email = email;
        this.mobile = mobile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Doctor information: \n"
                + "Name: " + this.name + "\n"
                + "Date of Birth: " + this.dateOfBirth + "\n"
                + "Specialization: " + this.specialization + "\n"
                + "Availability: " + this.availability + "\n"
                + "Email: " + this.email + "\n"
                + "Mobile: " + this.mobile + "\n"; //To change body of generated methods, choose Tools | Templates.
    }
}
