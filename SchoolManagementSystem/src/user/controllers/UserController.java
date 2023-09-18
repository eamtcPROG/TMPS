package user.controllers;

import user.interfaces.IUserController;
import user.services.UserService;
import user.utils.UIHelper;
import user.utils.UserMapper;
import user.models.User;
import user.dto.UserDTO;

import java.time.LocalDate;

public class UserController implements IUserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void create() {
        String firstName = UIHelper.readInput("Enter first name: ");
        String lastName = UIHelper.readInput("Enter last name: ");
        String email = UIHelper.readInput("Enter email: ");
        String dateOfBirthStr = UIHelper.readInput("Enter date of birth (yyyy-mm-dd): ");
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);

        User newUser = new User(0, firstName, lastName, email, null, dateOfBirth);
        userService.saveOrUpdate(newUser);

        UIHelper.displayMessage("User created successfully!");
    }

    @Override
    public void display() {
        userService.getAll().forEach(user -> {
            UserDTO userDTO = UserMapper.toDTO(user);
            UIHelper.displayMessage(userDTO.toString());
        });
    }
}
