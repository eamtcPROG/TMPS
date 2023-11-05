package user.controllers;

import general.utils.UIHelper;
import user.dto.UserBuilder;
import user.interfaces.IUserController;
import user.interfaces.IUserService;
import user.interfaces.UserDecorator;
import user.models.UserWithRole;
import user.services.UserService;
import user.utils.UserMapper;
import user.models.User;
import user.dto.UserDTO;
import java.util.Random;

import java.time.LocalDate;

public class UserController implements IUserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void create() {
        String firstName = UIHelper.readInput("Enter first name: ");
        String lastName = UIHelper.readInput("Enter last name: ");
        String email = UIHelper.readInput("Enter email: ");
        String dateOfBirthStr = UIHelper.readInput("Enter date of birth (yyyy-mm-dd): ");
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);
        Random rand = new Random();
        UserDTO newUser = new UserBuilder()
                .setId(rand.nextInt(100000))
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setDateOfBirth(dateOfBirth)
                .build();

        User user = UserMapper.toEntity(newUser);

        userService.saveOrUpdate(user.clone());
        UserDecorator decoratedUser = new UserWithRole(user, "Admin");
        decoratedUser.display();
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
