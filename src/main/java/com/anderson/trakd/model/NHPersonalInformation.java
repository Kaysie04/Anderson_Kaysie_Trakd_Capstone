package com.anderson.trakd.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.*;

// POJO for nhpersonal
@Entity
@Table(name = "nhpersonal")
public class NHPersonalInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "personal_email")
    private String personalEmail;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "salary")
    private String salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_credentials_id")
    private NHCompanyCredentials companyCredentials;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dept_id")
    private Dept dept;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id")
    private Manager manager;



    public NHPersonalInformation(){}

    public NHPersonalInformation(Long id, String firstname, String lastname, String personalEmail, String phoneNumber, String street, String city, String state, String zipcode, NHCompanyCredentials companyCredentials, Dept dept, Manager manager, String jobTitle, String salary) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.personalEmail = personalEmail;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.companyCredentials = companyCredentials;
        this.dept = dept;
        this.manager = manager;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public NHCompanyCredentials getCompanyCredentials() {
        return companyCredentials;
    }

    public void setCompanyCredentials(NHCompanyCredentials companyCredentials) {
        this.companyCredentials = companyCredentials;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
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
        NHPersonalInformation that = (NHPersonalInformation) o;
        return Objects.equals(id, that.id) && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(personalEmail, that.personalEmail) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(zipcode, that.zipcode) && Objects.equals(companyCredentials, that.companyCredentials) && Objects.equals(dept, that.dept) && Objects.equals(manager, that.manager) && Objects.equals(jobTitle, that.jobTitle) && Objects.equals(salary, that.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, personalEmail, phoneNumber, street, city, state, zipcode, companyCredentials, dept, manager, jobTitle, salary);
    }

    @Override
    public String toString() {
        return "NHPersonalInformation{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", personalEmail='" + personalEmail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", companyCredentials=" + companyCredentials +
                ", dept=" + dept +
                ", manager=" + manager +
                ", jobTitle='" + jobTitle + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
