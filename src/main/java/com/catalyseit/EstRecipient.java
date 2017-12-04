package com.catalyseit;

public class EstRecipient {
    private String email;
    private String firstname;
    private String lastname;
    private Integer position;
    private Integer step;

    public EstRecipient(){}

    public EstRecipient(String email, String firstname, String lastname, int position, int step) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.position = position;
        this.step = step;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }
}
