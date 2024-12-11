package com.klef.jfsd.erp.model;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.persistence.*;
import java.util.Set;
@Entity
@Table(name = "student_table")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "student_name")
    private String name; // Combined firstName, middleName, and lastName

    @Column(name = "student_gender")
    private String gender;

    @Column(name = "student_father_name")
    private String fatherName;

    @Column(name = "student_mother_name")
    private String motherName;

    @Column(name = "student_father_contact")
    private String fatherContact;

    @Column(name = "student_mother_contact")
    private String motherContact;

    @Lob
    @Column(name = "student_photo", columnDefinition = "LONGBLOB")
    private byte[] photo; // Changed to store image as binary data

    @Column(name = "student_date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "student_personal_email")
    private String personalEmail;

    @Column(name = "student_identification")
    private String identification;

    @Column(name = "student_major_degree")
    private String majorDegree;

    @Column(name = "student_program")
    private String program;

    @Column(name = "student_address_contact")
    private String addressContact;

    @Column(name = "student_password", nullable = false)
    private String password; // New field for storing student password

    // Many-to-many relationship with courses
    @ManyToMany
    @JoinTable(
        name = "student_courses",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherContact() {
        return fatherContact;
    }

    public void setFatherContact(String fatherContact) {
        this.fatherContact = fatherContact;
    }

    public String getMotherContact() {
        return motherContact;
    }

    public void setMotherContact(String motherContact) {
        this.motherContact = motherContact;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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

    public String getMajorDegree() {
        return majorDegree;
    }

    public void setMajorDegree(String majorDegree) {
        this.majorDegree = majorDegree;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getAddressContact() {
        return addressContact;
    }

    public void setAddressContact(String addressContact) {
        this.addressContact = addressContact;
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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", motherName='" + motherName + '\'' +
                ", fatherContact='" + fatherContact + '\'' +
                ", motherContact='" + motherContact + '\'' +
                ", photo=<binary data>" + // Indicate that binary data is present
                ", dateOfBirth=" + dateOfBirth +
                ", personalEmail='" + personalEmail + '\'' +
                ", identification='" + identification + '\'' +
                ", majorDegree='" + majorDegree + '\'' +
                ", program='" + program + '\'' +
                ", addressContact='" + addressContact + '\'' +
                ", password=<hidden>" + // Mask password in toString for security
                ", courses=" + courses +
                '}';
    }
}
