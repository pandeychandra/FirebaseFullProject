package com.example.moonlight.firebasefullproject;

/**
 * Created by MoonLight on 3/3/2018.
 */

public class StudentDetails {
    private String name;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private  String uid;

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    private String Location;
    private  String phoneNumber;

        public StudentDetails() {
            // This is default constructor.
        }

        public String getStudentName() {

            return name;
        }

        public void setStudentName(String name) {

            this.name = name;
        }

        public String getStudentPhoneNumber() {

            return phoneNumber;
        }

        public void setStudentPhoneNumber(String phonenumber) {

            this.phoneNumber = phonenumber;
        }

}