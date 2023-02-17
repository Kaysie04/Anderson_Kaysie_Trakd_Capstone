package com.anderson.trakd.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "nhcompany")
public class NHCompanyCredentials implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name= "db_id")
    private Long dbId;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "company_email")
    private String companyEmail;

    @Column(name = "password")
    private String password;

    public NHCompanyCredentials(){};

    public NHCompanyCredentials(Long dbId, String employeeId, String companyEmail, String password) {
        this.dbId = dbId;
        this.employeeId = employeeId;
        this.companyEmail = companyEmail;
        this.password = password;
    }

    public Long getDbId() {
        return dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NHCompanyCredentials that = (NHCompanyCredentials) o;
        return Objects.equals(dbId, that.dbId) && Objects.equals(employeeId, that.employeeId) && Objects.equals(companyEmail, that.companyEmail) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dbId, employeeId, companyEmail, password);
    }

    @Override
    public String toString() {
        return "NHCompanyCredentials{" +
                "dbId=" + dbId +
                ", employeeId='" + employeeId + '\'' +
                ", companyEmail='" + companyEmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
