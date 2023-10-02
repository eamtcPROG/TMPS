package user.dto;

import java.time.LocalDate;

public class UserBuilder {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;

    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public UserDTO build() {
        return new UserDTO(id, firstName, lastName, email, dateOfBirth);
    }
}
