package main.asw.user;

import main.asw.encryption.EncryptionUtils;

import java.util.Date;

/**
 * Created by nicolas on 3/02/17.
 *
 * @author nicolas
 * @author MIGUEL
 */
public class User {

    private String firstName, lastName, email, address, nationality, nif;
    private Date dateOfBirth;
    private String password;
    private String unencryptedPass;   //For (TODO) letter generation. Not stored on DB.


    public User(String firstName, String lastName, String email, Date dateOfBirth, String address, String nationality, String nif) {
        this.firstName = firstName;
        this.lastName = lastName;

        this.setEmail(email);
        this.setDateOfBirth(dateOfBirth);
        this.setNif(nif);

        this.address = address;
        this.nationality = nationality;

        this.unencryptedPass = EncryptionUtils.getInstance().generatePassword();
        this.password = EncryptionUtils.getInstance().encryptPassword(unencryptedPass);
    }

    private boolean validateDate(Date date) {
        return !date.after(new Date());
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", nationality='" + nationality + '\'' +
                ", nif='" + nif + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    public String getUnencryptedPass() {
        return unencryptedPass;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getNationality() {
        return nationality;
    }

    public String getNif() {
        return nif;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        if (validateDate(dateOfBirth))
            this.dateOfBirth = dateOfBirth;
        else
            throw new IllegalArgumentException("The date is after today");
    }

    public void setEmail(String email) {
        if (validateEmail(email))
            this.email = email;
        else
            throw new IllegalArgumentException("The email is not well formed");
    }

    private boolean validateEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public void setNif(String nif) {
        if (validateNif(nif))
            this.nif = nif;
        else
            throw new IllegalArgumentException("The nif is not well formed");
    }

    private boolean validateNif(String nif) {
        Boolean res = true;
        if (nif.length() == 9) {
            int dni = Integer.parseInt(nif.substring(0, 8));
            String juegoCaracteres = "TRWAGMYFPDXBNJZSQVHLCKE";
            int modulo = dni % 23;
            char letra = juegoCaracteres.charAt(modulo);
            if (nif.charAt(8) != letra)
                res = false;
        } else {
            res = false;
        }
        return res;
    }
}
