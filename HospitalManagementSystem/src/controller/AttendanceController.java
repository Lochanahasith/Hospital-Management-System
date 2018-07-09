/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Lochana Hasith Mash
 */
public class AttendanceController {

    public AttendanceController(String empId, int wardNo, String date, String month) {

        this.empId = empId;
        this.wardNo = wardNo;
        this.date = date;
        this.month = month;

    }

    public void addToDb() throws Exception {

        Statement stat = HospitalSystem.getStatement();
        stat.executeUpdate("INSERT into Attendance(Employee_NIC_no,Employee_Ward_Ward_NO,date,Month) VALUES('" + getEmpId() + "','" + getWardNo() + "','" + getDate() + "','" + getMonth() + "')");
    }

    public static int Count(String empId, String month) throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM Attendance WHERE Employee_NIC_no= '" + empId + "'");

        if (result.next()) {
            Statement stat1 = HospitalSystem.getStatement();
            ResultSet rs = stat1.executeQuery("SELECT COUNT(*) FROM Attendance WHERE Employee_NIC_no= '" + empId + "' AND Month= '" + month + "' ");
            rs.next();
            int count = rs.getInt(1);
            return count;

        }

        return 0;
    }

    /**
     * @return the empId
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(String empId) {
        this.empId = empId;
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
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    private String empId;
    private int wardNo;
    private String date;
    private String month;

}
