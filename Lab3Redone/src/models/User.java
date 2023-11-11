package models;

public class User {
    private String name;
    private String surname;
    private String email;

    private String phone;


    public User(String name, String surname, String email,String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {return phone;}

}
