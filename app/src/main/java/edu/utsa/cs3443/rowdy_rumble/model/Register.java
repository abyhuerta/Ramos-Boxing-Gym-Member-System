package edu.utsa.cs3443.rowdy_rumble.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Otto Zoesch-Weigel
 * Represents a registration object extending the User class.
 */
public class Register extends User {
    private String name;
    private String userName;
    private String passWord;
    private int dateCreated;

    /**
     * Constructor for Register class.
     *
     * @param name        The name of the user.
     * @param userName    The username of the user.
     * @param password    The password of the user.
     * @param dateCreated The date when the user was created.
     */
    public Register(String name, String userName, String password, int dateCreated) {
        super(name, userName, password);
        this.dateCreated = dateCreated;
    }

    /**
     * Calculates the age based on the date of birth.
     *
     * @param DOB The date of birth in the format "MM/DD/YYYY".
     * @return The age of the user.
     */
    public static int calculateAge(String DOB) {
        String[] splits = DOB.split("/");
        int year = Integer.parseInt(splits[2]);
        int month = Integer.parseInt(splits[0]);
        int day = Integer.parseInt(splits[1]);
        LocalDate birthDate = LocalDate.of(year, month, day);  // Example date of birth
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();
        return age;
    }

    /**
     * Validates the date format.
     *
     * @param str The date string to validate.
     * @return True if the date format is invalid, otherwise false.
     */
    public static boolean validateDate(String str) {
        Pattern pattern = Pattern.compile("[0-1][0-9]/[0-3][0-9]/[1-2][0-9][0-9][0-9]");
        Matcher matcher = pattern.matcher(str);
        return !matcher.find();
    }

    /**
     * Validates the phone number format.
     *
     * @param str The phone number string to validate.
     * @return True if the phone number format is invalid, otherwise false.
     */
    public static boolean validatePhoneNumber(String str) {
        Pattern pattern = Pattern.compile("\\([0-9]{3}\\)[0-9]{3}-[0-9]{3}");
        Matcher matcher = pattern.matcher(str);
        return !matcher.find();
    }

    /**
     * Validates if a string represents a number.
     *
     * @param str The string to validate.
     * @return True if the string is not a number, otherwise false.
     */
    public static boolean validateNumber(String str) {
        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    /**
     * Validates if a string represents a float number.
     *
     * @param str The string to validate.
     * @return True if the string is not a float number, otherwise false.
     */
    public static boolean validateFloat(String str) {
        try {
            Float.parseFloat(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    /**
     * Getter for the user's name.
     *
     * @return The name of the user.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Setter for the user's name.
     *
     * @param name The name of the user.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the user's username.
     *
     * @return The username of the user.
     */
    @Override
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for the user's username.
     *
     * @param userName The username of the user.
     */
    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for the user's password.
     *
     * @return The password of the user.
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * Setter for the user's password.
     *
     * @param passWord The password of the user.
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * Getter for the date when the user was created.
     *
     * @return The date when the user was created.
     */
    public int getDateCreated() {
        return dateCreated;
    }

    /**
     * Setter for the date when the user was created.
     *
     * @param dateCreated The date when the user was created.
     */
    public void setDateCreated(int dateCreated) {
        this.dateCreated = dateCreated;
    }
}
