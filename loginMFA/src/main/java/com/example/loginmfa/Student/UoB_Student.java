package com.example.loginmfa.Student;

public class UoB_Student {

    /**
     * Attributes
     */
    private String email;
    private String phoneNumber;
    private String password;

    /**
     *
     * Construct a student object.
     *
     * @param email students email address
     * @param phoneNumber students phone number
     * @param password students password
     */
    public UoB_Student(String email, String phoneNumber, String password) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    /**
     *
     * Getters and setters for student object
     *
     */

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
