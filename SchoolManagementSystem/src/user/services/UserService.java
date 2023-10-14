package user.services;

import general.interfaces.IRepository;
import user.interfaces.IUserRepository;
import user.models.User;
import user.repositories.UserRepository;
import user.interfaces.IUserService;

import java.util.List;

public class UserService implements IUserService {

    private final IRepository<User> userRepository;

    private static IUserService instance;
//    private final IUserRepository userRepository;

    public static IUserService getInstance(IRepository<User> userRepository) {
        if (instance == null) {
            instance = new UserService(userRepository);
        }
        return instance;
    }
    public UserService(IRepository<User> userRepository) {

        this.userRepository = userRepository;
    }



    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User saveOrUpdate(User user) {
        User existingUser = userRepository.findById(user.getId());
        if (existingUser == null) {

            User newUser = user.clone();
            userRepository.save(newUser);
        } else {

            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setDateOfBirth(user.getDateOfBirth());
            userRepository.update(existingUser);
        }
        return user;
    }


    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

}
