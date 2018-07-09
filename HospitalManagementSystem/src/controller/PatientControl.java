/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Lochana Hasith Mash
 */
public class PatientControl {

    public PatientControl(String firstName, String lastName, String gender, int age, String nic, String address, String contactNo) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
        this.nic = nic;
        this.gender = gender;
        this.contactNo = contactNo;

    }

    public int addToDb() throws Exception {

        Statement stat = HospitalSystem.getStatement();
        stat.executeUpdate("INSERT into Patient(First_name,Last_name,Gender,Age,NIC_NO,Address,contact_No) VALUES('" + getFirstName() + "','" + getLastName() + "','" + getGender() + "','" + getAge() + "','" + getNic() + "','" + getAddress() + "','" + getContactNo() + "')");

        ResultSet result = stat.executeQuery("SELECT LAST_INSERT_ID() FROM Patient");

        result.next();
        int id = result.getInt(1);
        return id;

    }

    public static PatientControl searchFromDb(int p_id) throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM Patient WHERE  Patient_id ='" + p_id + "'");

        if (result.next()) {

            PatientControl patient1 = new PatientControl(result.getString(2), result.getString(3), result.getString(4), result.getInt(5), result.getString(6), result.getString(7), result.getString(8));

            return patient1;
        }
        return null;
    }

    public static int deleteFromDb(int p_id) throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM Patient WHERE Patient_id= '" + p_id + "'");

        if (result.next()) {
            stat.executeUpdate("DELETE FROM Patient WHERE Patient_id ='" + p_id + "'");
        } else {
            return 1;
        }
        return 0;
    }

    public void UpdateDb(int pat_id) throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM Patient WHERE Patient_id= '" + pat_id + "'");

        if (result.next()) {
            stat.executeUpdate("UPDATE Patient set First_name='" + firstName + "',Last_name= '" + lastName + "',Gender = '" + gender + "',Age='" + age + "',NIC_NO='" + nic + "',Address='" + address + "',contact_No='" + contactNo + "' WHERE  Patient_id = '" + pat_id + "' ");
            JOptionPane.showMessageDialog(null, "Succesfully updated");
        }

    }

    /**
     * @return the patientID
     */
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     */
    /**
     * @return the contactNo
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * @param contactNo the contactNo to set
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
     * @return the pId
     */
    private String firstName;
    private String lastName;
    private String address;
    private int age;
    private String nic;
    private String gender;
    private String contactNo;

}
