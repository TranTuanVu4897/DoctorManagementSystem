/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Doctor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author TranVu
 */
public class ControlTest {

    static Control control;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    public ControlTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        List<Doctor> docs = new ArrayList<>();
        docs.add(new Doctor(1, "Nguyen Van A", "10/01/1989", "Alotoftext", 1, "tranvannhat@gmail.com", "(009)-000-0000"));
        control = new Control(docs);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void nameLongerThan50Char() {
        try {
            String expResult = "Name is not longer than 50 characters";
            control.checkDoctorsName(new Scanner("ban nao cung hoc rat cham chi hoc mon swt cua thay pham ngoc ha nhung abcxyz"));
            String result = outContent.toString();
            assertEquals(expResult, result);
        } catch (Exception e) {

        }
    }

    @Test
    public void nameNotLongerThan50Char() {
        try {
            String expResult = "abcxyz";
            String result = control.checkDoctorsName(new Scanner("abcxyz"));
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
    }

    @Test
    public void nameEqual50Char() {
        try {
            String expResult = "abcxyzvbcsjcsjbcjsbcsjcbscbksbksckbckscmmmgdjsgdjs";
            String result = control.checkDoctorsName(new Scanner("abcxyzvbcsjcsjbcjsbcsjcbscbksbksckbckscmmmgdjsgdjs"));
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
    }

    @Test
    public void dateOfBirthNotConform() {
        try {
            String expResult = "Date of birth is not match format. Please input correct date.";
            control.checkDateBirth(new Scanner("This is an incorrect input"));
            String result = outContent.toString();
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
    }

    @Test
    public void dateOfBirthWrongDate() {
        try {
            String expResult = "Date of birth is not match format. Please input correct date.";
            control.checkDateBirth(new Scanner("29/02/2001"));
            String result = outContent.toString();
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
    }

    @Test
    public void dateOfBirthCorrect() {
        try {
            String expResult = "28/2/2000";
            String result = control.checkDateBirth(new Scanner("28/2/2000"));
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
    }

    @Test
    public void specializationLongerThan255Chars() {
        try {
            String expResult = "Specialization is not longer than 225 characters";
            control.checkSpecialization(new Scanner("this is an incorrect inputttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt255"));
            String result = outContent.toString();
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
    }

    @Test
    public void specializationNotLongerThan255Chars() {
        try {
            String expResult = "abcxyz";
            String result = control.checkSpecialization(new Scanner("abcxyz"));
            outContent.toString();
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
    }

    @Test
    public void specializationEquals255Chars() {
        try {
            String expResult = "this is an incorrect inputttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt255";
            String result = control.checkSpecialization(new Scanner("this is an incorrect inputttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt255"));
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
    }

    @Test
    public void invalidEmail() {
        try {
            String expResult = "Email is invalid. Please input again";
            control.checkEmail(new Scanner("abcxyz"));
            String result = outContent.toString();
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
    }

    @Test
    public void validEmail() {
        try {
            String expResult = "correctemail1234@gmail.com.vn";
            String result = control.checkSpecialization(new Scanner("correctemail1234@gmail.com.vn"));
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
    }

    @Test
    public void invalidPhone() {
        try {
            String expResult = "Phone number is invalid.";
            control.checkSpecialization(new Scanner("Incorrect phone number"));
            String result = outContent.toString();
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
    }

    @Test
    public void validPhone() {
        try {
            String expResult = "(092)-000-0000";
            String result = control.checkSpecialization(new Scanner("(092)-000-0000"));
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
    }

    @Test
    public void editInvalidId() {
        try {
            String expResult = "This id is not exist";
            control.findDoctorById(new Scanner("99999999"));
            String result = outContent.toString();
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
    }

    @Test
    public void editValidId() {
        try {
            Doctor expResult = new Doctor(1, "Nguyen Van A", "10/01/1989", "Alotoftext", 1, "tranvannhat@gmail.com", "(009)-000-0000");
            Doctor result = control.findDoctorById(new Scanner("1"));
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
    }

    @Test
    public void deleteInvalidId() {
        try {
            String expResult = "This id is not exist";
            control.findDoctorById(new Scanner("99999999"));
            String result = outContent.toString();
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
    }

    @Test
    public void deleteValidId() {
        try {
            int expResult = control.getDocs().size() - 1;
            control.DeleteDoctor(new Scanner("1"));
            int result = control.getDocs().size();
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
    }

    @Test
    public void searchNotExistName() {
        try {
            String expResult = "There is no doctor have name 'This is not exist name doctor'";
            control.findDoctorByName(new Scanner("This is not exist name doctor"));
            String result = outContent.toString();
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
    }

    @Test
    public void searchExistName() {
        try {
            List< Doctor> expResult = new ArrayList<>();
            expResult.add(new Doctor(1, "Nguyen Van A", "10/01/1989", "Alotoftext", 1, "tranvannhat@gmail.com", "(009)-000-0000"));
            List<Doctor> result = control.findDoctorByName(new Scanner("Nguyen Van A"));
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
    }

}
