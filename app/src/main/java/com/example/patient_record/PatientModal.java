package com.example.patient_record;

public class PatientModal {

    //variables for patient data
    private String firstName;
    private String lastName;
    private String age;
    private String p_visit;
    private int id;


    //getters and setters
    public String getfName() {
        return firstName;
    }

    public void setfName(String fName) {
        this.firstName = fName;
    }

    public String getlName() {
        return lastName;
    }

    public void setlName(String lName) {
        this.lastName = lName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getP_visit() {
        return p_visit;
    }

    public void setP_visit(String p_visit) {
        this.p_visit = p_visit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PatientModal(String firstName, String lastName, String age, String p_visit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.p_visit = p_visit;
    }


}
