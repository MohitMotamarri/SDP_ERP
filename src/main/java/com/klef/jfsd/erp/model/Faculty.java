package com.klef.jfsd.erp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "faculty_table")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private Long id;

    @Column(name = "faculty_name")
    private String facultyName; // Combined name field for first, middle, and last names

    @Column(name = "faculty_gender")
    private String gender;

    @Column(name = "faculty_date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "faculty_personal_email")
    private String personalEmail;

    @Column(name = "faculty_identification")
    private String identification;

    @Column(name = "faculty_department")
    private String department;

    @Column(name = "faculty_position")
    private String position;

    @Column(name = "faculty_address_contact")
    private String addressContact;

    @Column(name = "faculty_photo", columnDefinition = "LONGBLOB")
    private byte[] facultyPhoto; // Field to store faculty photo as a LONGBLOB

    @Column(name = "faculty_password")
    private String password; // New field for faculty password

    // Many-to-Many relationship with Course
    @ManyToMany
    @JoinTable(
        name = "faculty_courses",
        joinColumns = @JoinColumn(name = "faculty_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses; // Faculty can teach multiple courses

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddressContact() {
        return addressContact;
    }

    public void setAddressContact(String addressContact) {
        this.addressContact = addressContact;
    }

    public byte[] getFacultyPhoto() {
        return facultyPhoto;
    }

    public void setFacultyPhoto(byte[] facultyPhoto) {
        this.facultyPhoto = facultyPhoto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    // Optional: Override toString method for easy debugging or logging
    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", facultyName='" + facultyName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", personalEmail='" + personalEmail + '\'' +
                ", identification='" + identification + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", addressContact='" + addressContact + '\'' +
                '}';
    }
}
