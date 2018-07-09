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
public class EmployeeController {

    //constructor
    public EmployeeController(String firstName, String lastName, String address, String nic, String dob, String email, String type, int wardNo, String contactNo, String specialization, String grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.nic = nic;
        this.dob = dob;
        this.email = email;
        this.type = type;
        this.wardNo = wardNo;
        this.contactNo = contactNo;
        this.specialization = specialization;
        this.grade = grade;
    }

    public void addToDB() throws Exception {

        Statement stat = HospitalSystem.getStatement();
        stat.executeUpdate("INSERT into Employee(First_name,Last_name,Address,NIC_no,DOB,Email,Type,Ward_Ward_NO) VALUES('" + getFirstName() + "','" + getLastName() + "','" + getAddress() + "','" + getNic() + "','" + getDob() + "','" + getEmail() + "','" + getType() + "','" + getWardNo() + "')");
        stat.executeUpdate("INSERT into Employee_Contact_No(ContactNo,Employee_NIC_no) VALUES ('" + getContactNo() + "','" + getNic() + "')");
        if (getType().equals("Doctor")) {

            stat.executeUpdate("INSERT into Doctor(Specialization,Employee_NIC_no) VALUES('" + getSpecialization() + "','" + getNic() + "')");

        } else if (getType().equals("Nurse")) {

            stat.executeUpdate("INSERT into Nurse(Grade,Employee_NIC_no) VALUES('" + getGrade() + "','" + getNic() + "')");

        }
    }

    public static EmployeeController searchFromDb(String nic) throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM Employee WHERE  NIC_no ='" + nic + "'");

        if (result.next()) {

            EmployeeController employee1 = new EmployeeController(result.getString(1), result.getString(2), result.getString(3), nic, result.getString(5), result.getString(6), result.getString(7), result.getInt(8), "", "", "");

            if ("Doctor".equals(result.getString(7))) {

                ResultSet rs = stat.executeQuery("SELECT * FROM Doctor WHERE  Employee_NIC_no ='" + nic + "'");
                rs.next();
                employee1.setSpecialization(rs.getString(1));
            } else if ("Nurse".equals(result.getString(7))) {

                ResultSet rs = stat.executeQuery("SELECT * FROM Nurse WHERE  Employee_NIC_no ='" + nic + "'");
                rs.next();
                employee1.setGrade(rs.getString(1));
            }

            ResultSet rs1 = stat.executeQuery("SELECT * FROM  Employee_Contact_No WHERE  Employee_NIC_no ='" + nic + "'");
            rs1.next();
            employee1.setContactNo(rs1.getString(1));

            return employee1;
        }

        return null;
    }

    public static int deleteFromDb(String empNo) throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM Employee WHERE NIC_no = '" + empNo + "'");

        if (result.next()) {
            stat.executeUpdate("DELETE FROM Employee WHERE  NIC_no ='" + empNo + "'");
        } else {
            return 1;
        }
        return 0;
    }

    public void UpdateDb(String nicNo) throws Exception {

        Statement stat = HospitalSystem.getStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM Employee WHERE NIC_no= '" + nicNo + "'");

        if (result.next()) {
            stat.executeUpdate("UPDATE Employee set First_name='" + firstName + "',Last_name= '" + lastName + "',Address='" + address + "',DOB='" + dob + "',Email = '" + email + "',Ward_Ward_NO='" + wardNo + "'  WHERE  NIC_no= '" + nicNo + "' ");
            stat.executeUpdate("UPDATE Employee_Contact_No set ContactNo='" + contactNo + "'  WHERE  Employee_NIC_no = '" + nicNo + "' ");
            JOptionPane.showMessageDialog(null, "Succesfully updated");

            if (type == "Doctor") {

                stat.executeUpdate("UPDATE Doctor set Specialization ='" + specialization + "' WHERE Employee_NIC_no= '" + nicNo + "' ");
            } else if (type == "Nurse") {

                stat.executeUpdate("UPDATE Nurse set Grade ='" + grade + "' WHERE Employee_NIC_no= '" + nicNo + "' ");
            }
        }
    }

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
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return the specialization
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * @param specialization the specialization to set
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * @param wardNo the wardNo to set
     */
    public void setWardNo(int wardNo) {
        this.wardNo = wardNo;
    }

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
     * @return the wardNo
     */
    public int getWardNo() {
        return wardNo;
    }

    /**
     * @param wardNo the wardNo to set
     */
    public void setWardNo(String wardNo) {
        this.setWardNo(wardNo);
    }

    private String firstName;
    private String lastName;
    private String address;
    private String nic;
    private String dob;
    private String email;
    private String type;
    private int wardNo;
    private String contactNo;
    private String specialization;
    private String grade;

}
