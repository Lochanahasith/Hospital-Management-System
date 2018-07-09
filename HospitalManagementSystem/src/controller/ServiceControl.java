/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Lochana Hasith Mash
 */
public class ServiceControl {

    public ServiceControl(int patientId, int admissionId, String date, String time, String type, String resultOrDes, float price) {

        this.patientId = patientId;
        this.admissionId = admissionId;
        this.date = date;
        this.time = time;
        this.type = type;
        this.resultOrDes = resultOrDes;
        this.price = price;

    }

    public int addToDb() throws Exception {

        Statement stat = HospitalSystem.getStatement();
        stat.executeUpdate("INSERT into Service(Admission_Patient_Patient_id,Admission_Admission_id,Date,Time,Type,Description,Price) VALUES('" + getPatientId() + "','" + getAdmissionId() + "','" + getDate() + "','" + getTime() + "','" + getType() + "','" + getResultOrDes() + "','" + getPrice() + "')");

        ResultSet result = stat.executeQuery("SELECT LAST_INSERT_ID() FROM Service");

        result.next();
        int id = result.getInt(1);
        return id;

    }

    public static ServiceControl searchFromDb(int s_id) throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM Service WHERE  Service_Id ='" + s_id + "'");

        if (result.next()) {

            ServiceControl service = new ServiceControl(result.getInt(2), result.getInt(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7), result.getFloat(8));

            return service;
        }
        return null;
    }

    public static int deleteFromDb(int s_id) throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM Service WHERE  Service_Id= '" + s_id + "'");

        if (result.next()) {
            stat.executeUpdate("DELETE FROM Service WHERE  Service_Id='" + s_id + "'");
        } else {
            return 1;
        }
        return 0;
    }

    public void UpdateDb(int s_id) throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM Service WHERE  Service_Id= '" + s_id + "'");

        if (result.next()) {
            stat.executeUpdate("UPDATE Service set Admission_Patient_Patient_id='" + patientId + "',Admission_Admission_id= '" + admissionId + "',Date= '" + date + "',Time='" + time + "',Type= '" + type + "',Description='" + resultOrDes + "',Price = '" + price + "' WHERE  Service_Id = '" + s_id + "' ");
            JOptionPane.showMessageDialog(null, "Succesfully updated");
        }

    }

    /**
     * @return the patientId
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    /**
     * @return the admissionId
     */
    public int getAdmissionId() {
        return admissionId;
    }

    /**
     * @param admissionId the admissionId to set
     */
    public void setAdmissionId(int admissionId) {
        this.admissionId = admissionId;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the resultOrDes
     */
    public String getResultOrDes() {
        return resultOrDes;
    }

    /**
     * @param resultOrDes the resultOrDes to set
     */
    public void setResultOrDes(String resultOrDes) {
        this.resultOrDes = resultOrDes;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }
    private int patientId;
    private int admissionId;
    private String date;
    private String time;
    private String type;
    private String resultOrDes;
    private float price;

}
