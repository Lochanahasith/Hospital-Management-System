/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Frame;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Lochana Hasith Mash
 */
public class Ward {

    // constructor of the Ward class
    public Ward(int wardNo, String wardName, int numberOfPatients, String nameOfHead, int numberOfBeds) {

        this.wardNo = wardNo;
        this.wardName = wardName;
        this.numberOfPatients = numberOfPatients;
        this.nameOfHead = nameOfHead;
        this.numberOfBeds = numberOfBeds;
    }

    /**
     * @return the wardNo
     */
    public int getWardNo() {
        return wardNo;
    }

    /**
     * @param wardNo the wardNo to set
     */
    public void setWardNo(int wardNo) {
        this.wardNo = wardNo;
    }

    /**
     * @return the wardName
     */
    public String getWardName() {
        return wardName;
    }

    /**
     * @param wardName the wardName to set
     */
    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    /**
     * @return the numberOfPatients
     */
    public int getNumberOfPatients() {
        return numberOfPatients;
    }

    /**
     * @param numberOfPatients the numberOfPatients to set
     */
    public void setNumberOfPatients(int numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    /**
     * @return the nameOfHead
     */
    public String getNameOfHead() {
        return nameOfHead;
    }

    /**
     * @param nameOfHead the nameOfHead to set
     */
    public void setNameOfHead(String nameOfHead) {
        this.nameOfHead = nameOfHead;
    }

    /**
     * @return the numberOfBeds
     */
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    /**
     * @param numberOfBeds the numberOfBeds to set
     */
    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }
    private int wardNo;
    private String wardName;
    private int numberOfPatients;
    private String nameOfHead;
    private int numberOfBeds;

    public void addToDb() throws Exception {

        Statement stat = HospitalSystem.getStatement();
        stat.executeUpdate("INSERT into Ward(Ward_NO,Ward_Name,No_of_patient,Name_of_head,No_of_beds) VALUES('" + wardNo + "','" + wardName + "','" + numberOfPatients + "','" + nameOfHead + "','" + numberOfBeds + "')");

    }

    public static Ward searchFromDb(int wardNo) throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM Ward WHERE  Ward_NO='" + wardNo + "'");

        if (result.next()) {

            Ward ward1 = new Ward(wardNo, result.getString(2), result.getInt(3), result.getString(4), result.getInt(5));

            return ward1;
        }

        return null;
    }

    public static int deleteFromDb(int wardNo) throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM Ward WHERE  Ward_NO= '" + wardNo + "'");

        if (result.next()) {
            stat.executeUpdate("DELETE FROM Ward WHERE  Ward_NO='" + wardNo + "'");
        } else {
            return 1;
        }
        return 0;
    }

    public void UpdateDb() throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM Ward WHERE  Ward_NO= '" + getWardNo() + "'");

        if (result.next()) {
            stat.executeUpdate("UPDATE Ward set Ward_Name='" + wardName + "',No_of_patient='" + numberOfPatients + "',Name_of_head='" + nameOfHead + "',No_of_beds='" + numberOfBeds + "' WHERE Ward_NO='" + getWardNo() + "'");
            JOptionPane.showMessageDialog(null, "Succesfully updated");
        }

    }

}
