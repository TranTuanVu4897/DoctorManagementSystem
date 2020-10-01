/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctormanagementsystem;

import controller.Control;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Doctor;

/**
 *
 * @author TranVu
 */
public class DoctorManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         // TODO code application logic here
        boolean loop = true;
        Scanner scan = new Scanner(System.in);
        List<Doctor> doctors = new ArrayList();
        Control control = new Control(doctors);
        while (loop) {
            System.out.println("Welcome to Doctor Management System.\n"
                    + "--------------------------------------------\n"
                    + "1. Create a Doctor profile.\n"
                    + "2. Edit a Doctor profile.\n"
                    + "3. Delete a Doctor profile.\n"
                    + "4. Search a Doctor profile.\n"
                    + "5. Sort Doctors by Date of birth.\n"
                    + "6. Exit.\n"
                    + "Please choose your action:");
            String input = scan.nextLine().trim();
            switch (input) {
                case "1":
                    control.CreateDoctor();
                    break;
                case "2":
                    control.EditDoctor();
                    break;
                case "3":
                    control.DeleteDoctor(scan);
                    break;
                case "4":
                    control.SearchDoctor(scan);
                    break;
                case "5":
                    control.Sort();
                    break;
                case "6":
                    loop = false;
                    doctors = control.getDocs();
                    System.out.println("Thank for use ours system. See you later.");
                    break;
                default:
                    System.out.println("Please input number from 1 - 6.");
                    break;
            }

        }
    }
    
}
