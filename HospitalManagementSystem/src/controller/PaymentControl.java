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
public class PaymentControl {

    public PaymentControl(int patientId, int admissionId, String date, String time, float hospitalCharge, float totalcharge) {

        this.patientId = patientId;
        this.admissionId = admissionId;
        this.date = date;
        this.time = time;
        this.hospitalCharge = hospitalCharge;
        this.totalCharge = totalcharge;

    }

    public int addToDb() throws Exception {

        Statement stat = HospitalSystem.getStatement();
        stat.executeUpdate("INSERT into Payment(Admission_Patient_Patient_id,Admission_Admission_id,Date,Time,Hospital_charge,Total_Payment) VALUES('" + getPatientId() + "','" + getAdmissionId() + "','" + getDate() + "','" + getTime() + "','" + getHospitalCharge() + "','" + getTotalCharge() + "')");

        ResultSet result = stat.executeQuery("SELECT LAST_INSERT_ID() FROM Service");

        result.next();
        int id = result.getInt(1);
        return id;

    }

    public static PaymentControl searchFromDb(int pt_id) throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM Payment WHERE  Payment_id ='" + pt_id + "'");

        if (result.next()) {

            PaymentControl payment = new PaymentControl(result.getInt(2), result.getInt(3), result.getString(4), result.getString(5), result.getFloat(6), result.getFloat(7));

            return payment;
        }
        return null;
    }

    public static int deleteFromDb(int pay_id) throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM Payment WHERE  Payment_id = '" + pay_id + "'");

        if (result.next()) {
            stat.executeUpdate("DELETE FROM Payment WHERE  Payment_id ='" + pay_id + "'");
        } else {
            return 1;
        }
        return 0;
    }

    public void UpdateDb(int p_id) throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * From Payment WHERE  Payment_id = '" + p_id + "'");

        if (result.next()) {
            stat.executeUpdate("UPDATE Payment set Admission_Patient_Patient_id='" + patientId + "',Admission_Admission_id='" + admissionId + "',Date='" + date + "' ,Time='" + time + "',Hospital_charge='" + hospitalCharge + "',Total_Payment='" + totalCharge + "' WHERE  Payment_id = '" + p_id + "' ");
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
     * @return the hospitalCharge
     */
    public float getHospitalCharge() {
        return hospitalCharge;
    }

    /**
     * @param hospitalCharge the hospitalCharge to set
     */
    public void setHospitalCharge(float hospitalCharge) {
        this.hospitalCharge = hospitalCharge;
    }

    /**
     * @return the totalCharge
     */
    public float getTotalCharge() {
        return totalCharge;
    }

    /**
     * @param Totalcharge the totalCharge to set
     */
    public void setTotalCharge(float Totalcharge) {
        this.totalCharge = Totalcharge;
    }

    private int patientId;
    private int admissionId;
    private String date;
    private String time;
    private float hospitalCharge;
    private float totalCharge;

}
