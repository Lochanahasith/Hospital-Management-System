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
public class AdmissionController {

    public AdmissionController(String dateOfAdmission, String admitted_time, String recommendedBy, int patientId) {

        this.dateOfAdmission = dateOfAdmission;
        this.admittedTime = admitted_time;
        this.recommendedBy = recommendedBy;
        this.patientId = patientId;
    }

    public int addToDb() throws Exception {

        Statement stat = HospitalSystem.getStatement();
        stat.executeUpdate("INSERT into Admission(Date_of_admission ,Admitted_time, Recommended_by, Patient_Patient_id ) VALUES('" + dateOfAdmission + "','" + admittedTime + "','" + recommendedBy + "','" + patientId + "')");
        ResultSet result = stat.executeQuery("SELECT LAST_INSERT_ID() FROM Admission");

        result.next();
        int id = result.getInt(1);
        return id;

    }

    public static AdmissionController searchFromDb(int admission_id) throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM Admission WHERE  Admission_id ='" + admission_id + "'");

        if (result.next()) {

            AdmissionController admission = new AdmissionController(result.getString(2), result.getString(3), result.getString(4), result.getInt(5));

            return admission;
        }

        return null;
    }

    public static int deleteFromDb(int admId) throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM Admission WHERE  Admission_id= '" + admId + "'");

        if (result.next()) {
            stat.executeUpdate("DELETE FROM Admission WHERE  Admission_id='" + admId + "'");
        } else {
            return 1;
        }
        return 0;
    }

    public void UpdateDb(int a_id) throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * From Admission WHERE  Admission_id = '" + a_id + "'");

        if (result.next()) {
            stat.executeUpdate("UPDATE Admission set Date_of_admission='" + dateOfAdmission + "',Admitted_time='" + admittedTime + "',Recommended_by='" + recommendedBy + "' ,Patient_Patient_id='" + patientId + "' WHERE  Admission_id = '" + a_id + "' ");
            JOptionPane.showMessageDialog(null, "Succesfully updated");
        }

    }

    /**
     * @return the admissionId
     */
    /**
     * @return the dateOfAdmission
     */
    public String getDateOfAdmission() {
        return dateOfAdmission;
    }

    /**
     * @param dateOfAdmission the dateOfAdmission to set
     */
    public void setDateOfAdmission(String dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    /**
     * @return the admitted_time
     */
    public String getAdmitted_time() {
        return admittedTime;
    }

    /**
     * @param admitted_time the admitted_time to set
     */
    public void setAdmitted_time(String admitted_time) {
        this.admittedTime = admitted_time;
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
     * @return the recommendedBy
     */
    public String getRecommendedBy() {
        return recommendedBy;
    }

    /**
     * @param recommendedBy the recommendedBy to set
     */
    public void setRecommendedBy(String recommendedBy) {
        this.recommendedBy = recommendedBy;
    }
    private int admissionId;
    private String dateOfAdmission;
    private String admittedTime;
    private String recommendedBy;
    private int patientId;
}
