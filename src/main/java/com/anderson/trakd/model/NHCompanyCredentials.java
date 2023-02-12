package com.anderson.trakd.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "nhcompany")
public class NHCompanyCredentials {
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "company_email")
    private String companyEmail;

    @Column(name = "password")
    private String password;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "salary")
    private String salary;


    public NHCompanyCredentials(){};
    public NHCompanyCredentials(String employeeId, String companyEmail, String password, String jobTitle, String salary) {
        this.employeeId = employeeId;
        this.companyEmail = companyEmail;
        this.password = password;
        this.jobTitle = jobTitle;
        this.salary = salary;
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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NHCompanyCredentials that = (NHCompanyCredentials) o;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(companyEmail, that.companyEmail) && Objects.equals(password, that.password) && Objects.equals(jobTitle, that.jobTitle) && Objects.equals(salary, that.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, companyEmail, password, jobTitle, salary);
    }

    @Override
    public String toString() {
        return "NHCompanyCredentials{" +
                "employeeId='" + employeeId + '\'' +
                ", companyEmail='" + companyEmail + '\'' +
                ", password='" + password + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}