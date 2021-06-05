package com.utbm.lo54.bean;


import javax.persistence.*;



@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "lastName")
    private String lastName;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "course_Session_Id1")
    private Long course_Session_Id1;

    @Column(name = "course_Session_Id2")
    private Long course_Session_Id2;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCourse_Session_Id1() {
        return course_Session_Id1;
    }

    public void setCourse_Session_Id1(Long course_Session_Id1) {
        this.course_Session_Id1 = course_Session_Id1;
    }

    public Long getCourse_Session_Id2() {
        return course_Session_Id2;
    }

    public void setCourse_Session_Id2(Long course_Session_Id2) {
        this.course_Session_Id2 = course_Session_Id2;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", course_Session_Id1='" + course_Session_Id1 + '\'' +
                ", course_Session_Id2='" + course_Session_Id2 + '\'' +
                '}';
    }
}