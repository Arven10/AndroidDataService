package ua.kharkiv.dorozhan.androiddataservice.model;

public class User {
    private int id;
    private String firstName;
    private int phone;
    private String email;

    public User() {
    }

    public User(String firstName, int phone, String email) {
        this.firstName = firstName;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
