/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.accountError;

/**
 *
 * @author macbook
 */
public class AccountError {
    private String emailError;
    private String nameError;
    private String passwordError;
    private boolean genderError;
    private String campusError;

    public AccountError() {
         this.emailError = "";
        this.nameError = "";
        this.passwordError = "";
        this.genderError = false;
        this.campusError = "HCM";
    }

    public AccountError(String emailError, String nameError, String passwordError, boolean genderError, String campusError) {
        this.emailError = emailError;
        this.nameError = nameError;
        this.passwordError = passwordError;
        this.genderError = genderError;
        this.campusError = campusError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public boolean getGenderError() {
        return genderError;
    }

    public void setGenderError(boolean genderError) {
        this.genderError = genderError;
    }

    public String getCampusError() {
        return campusError;
    }

    public void setCampusError(String campusError) {
        this.campusError = campusError;
    }
    

    
    
}
