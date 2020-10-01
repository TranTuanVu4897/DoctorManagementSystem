/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Doctor;

/**
 *
 * @author TranVu
 */
public class Control {

    List<Doctor> docs;
    Scanner scan;

    public Control(List<Doctor> docs) {
        this.docs = docs;
        scan = new Scanner(System.in);
    }

    public Control() {
        scan = new Scanner(System.in);
    }

    public List<Doctor> getDocs() {
        return docs;
    }

    /**
     * Create new doctor
     */
    public void CreateDoctor() {
        Doctor doc = new Doctor();
        //set id
        int id = docs.size() > 0 ? docs.get(docs.size() - 1).getId() + 1 : 0;
        doc.setId(id);
        doc.setName(checkDoctorsName(scan));
        doc.setDateOfBirth(checkDateBirth(scan));
        doc.setSpecialization(checkSpecialization(scan));
        doc.setAvailability(checkAvailability(scan));
        doc.setEmail(checkEmail(scan));
        doc.setMobile(checkMobile(scan));

    }

    public void EditDoctor() {

        Doctor doc = findDoctorById(scan);
        if (doc != null) {
            doc.setName(checkDoctorsName(scan));
            doc.setDateOfBirth(checkDateBirth(scan));
            doc.setSpecialization(checkSpecialization(scan));
            doc.setAvailability(checkAvailability(scan));
            doc.setEmail(checkEmail(scan));
            doc.setMobile(checkMobile(scan));
        }
    }

    public void DeleteDoctor(Scanner in) {
        Doctor doc = findDoctorById(in);
        if (doc != null) {
            docs.remove(doc);
        }
    }

    public void SearchDoctor(Scanner in) {
        boolean loop = true;
        while (loop) {
            System.out.println("Please choose the way search:\n"
                    + "1. By Id\n"
                    + "2. By Name\n"
                    + "3. Back\n"
                    + "Your choose: ");
            switch (in.nextLine().trim()) {
                case "1":
                    Doctor doc = findDoctorById(scan);
                    if (doc != null) {
                        System.out.println(doc.toString());
                    }
                    loop = false;
                    break;
                case "2":
                    List<Doctor> listDocs = findDoctorByName(scan);
                    if (!listDocs.isEmpty()) {
                        listDocs.forEach((d) -> {
                            System.out.println(d.toString());
                        });
                    }
                    loop = false;
                    break;
                case "3":
                    loop = false;
                    break;
                default:
                    System.out.println("Please only input 1 - 3.");
                    break;
            }
        }
    }

    public void Sort() {
        Collections.sort(docs, (arg0, arg1) -> {
            return arg0.getDateOfBirth().compareToIgnoreCase(arg1.getDateOfBirth()); //To change body of generated lambdas, choose Tools | Templates.
        });
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%10s %30s %50s %20s %30s %20s", "DOCTOR ID", "NAME", "DATE OF BIRTH", "AVAILABILITY", "EMAIL", "MOBILE");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        docs.stream().map((Doctor doc) -> {
            System.out.format("%10s %30s %50s %20s %30s %20s",
                    doc.getId(), doc.getName(), doc.getDateOfBirth(), doc.getAvailability(), doc.getEmail(), doc.getMobile());
            return doc;
        }).forEachOrdered((_item) -> {
            System.out.println();
        });
        System.out.println("-----------------------------------------------------------------------------");

    }

    /**
     * Check doctor's name
     *
     * @param in
     * @return A String that shorter than 51 characters
     */
    public String checkDoctorsName(Scanner in) {
        String name = "";
        while (true) {
            System.out.println("Please input Doctor's name: ");
            name = in.nextLine().trim();
            if (name.length() <= 50) {
                break;
            }
            System.out.println("Name is not longer than 50 characters");
        }
        return name;
    }

    /**
     * Check date of birth input
     *
     * @param in
     * @return a String that in 'dd/MM/yyyy' format
     */
    public String checkDateBirth(Scanner in) {
        String returnValue = "";
        Pattern pattern = Pattern.compile("^(?:(?:31(\\/)(?:0[13578]|1[02]))\\1|(?:(?:29|30)(\\/)(?:0[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)\\d{2})$|^(?:29(\\/)02\\3(?:(?:(?:1[6-9]|[2-9]\\d)(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0[1-9]|1\\d|2[0-8])(\\/)(?:(?:0[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)\\d{2})$");

        while (true) {
            System.out.println("Please input date of birth: ");
            returnValue = in.nextLine().trim();
            Matcher matcher = pattern.matcher(returnValue);
            if (matcher.find()) {
                break;
            }
            System.out.println("Date of birth is not match format. Please input correct date.");
        }
        return returnValue;
    }

    /**
     * check specialization input
     *
     * @param in
     * @return a String that no longer than 255 characters
     */
    public String checkSpecialization(Scanner in) {
        String name = "";
        while (true) {
            System.out.println("Please input specialization: ");
            name = in.nextLine().trim();
            if (name.length() <= 255) {
                break;
            }
            System.out.println("Specialization is not longer than 225 characters");
        }
        return name;
    }

    /**
     * check Availability
     *
     * @param in
     * @return an integer that 0 for in vacation, 1 for available, 2 for busy in
     * emergency case, 3 for in diagnosing case.
     */
    public int checkAvailability(Scanner in) {
        int returnValue = 0;
        while (true) {
            System.out.println("Please input availability \n"
                    + "0 for in vacation\n"
                    + "1 for available\n"
                    + "2 for busy in emergency case\n"
                    + "3 for in diagnosing case\n"
                    + "Availability: ");
            returnValue = checkInputInt(in);
            if (returnValue < 4 && returnValue >= 0) {
                break;
            }
            System.out.println("Availability should around 0 - 4.");

        }
        return returnValue;
    }

    /**
     * check email
     *
     * @param in
     * @return a String in email format
     */
    public String checkEmail(Scanner in) {
        String returnValue = "";
        Pattern pattern = Pattern.compile("^(([^<>()\\[\\]\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");

        while (true) {
            System.out.println("Please input email: ");
            returnValue = in.nextLine().trim();
            Matcher matcher = pattern.matcher(returnValue);
            if (matcher.find()) {
                break;
            }
            System.out.println("Email is invalid. Please input again");
        }
        return returnValue;
    }

    /**
     * check mobile
     *
     * @param in
     * @return a String of number conforming (000)-000-0000 format
     */
    public String checkMobile(Scanner in) {
        String returnValue = "";
        Pattern pattern = Pattern.compile("^\\(\\d{3}\\)\\-\\d{3}\\-\\d{4}$");

        while (true) {
            System.out.println("Please input mobile number: ");
            returnValue = in.nextLine().trim();
            Matcher matcher = pattern.matcher(returnValue);
            if (matcher.find()) {
                break;
            }
            System.out.println("Phone number is invalid.");
        }
        return returnValue;
    }

    /**
     * find a doctor by id
     *
     * @param in
     * @return
     */
    public Doctor findDoctorById(Scanner in) {
        Doctor d = null;
        while (true) {
            System.out.println("Please input Doctor's Id: ");
            int id = checkInputInt(in);

            for (Doctor doc : docs) {
                if (doc.getId() == id) {
                    d = doc;
                }
            }
            if (d != null) {
                break;
            }
            System.out.println("Not exist Doctor's Id. Continue or not? Y/N?");
            if (in.nextLine().trim().toUpperCase().equals("N")) {
                break;
            }
        }
        return d;
    }

    /**
     * check a number input
     *
     * @param in
     * @return a number
     */
    public int checkInputInt(Scanner in) {
        int returnValue = 0;
        while (true) {
            try {
                returnValue = Integer.parseInt(in.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Not a number. Please input again: ");
            }
        }
        return returnValue;
    }

    /**
     * find all doctor have same name
     *
     * @param in
     * @return
     */
    public List<Doctor> findDoctorByName(Scanner in) {
        List<Doctor> listDocs = new ArrayList<>();
        while (true) {
            String id = checkDoctorsName(in);

            docs.stream().filter((doc) -> (doc.getName().equals(id))).forEachOrdered((doc) -> {
                listDocs.add(doc);
            });
            if (!listDocs.isEmpty()) {
                break;
            }
            System.out.println("There is no doctor have name " + id);
            if (in.nextLine().trim().toUpperCase().equals("N")) {
                break;
            }
        }
        return listDocs;
    }

}
