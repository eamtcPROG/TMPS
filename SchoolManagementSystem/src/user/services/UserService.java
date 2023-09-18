package user.services;

import user.interfaces.IUserRepository;
import user.models.User;
import user.repositories.UserRepository;
import user.interfaces.IUserService;

import java.util.List;

public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
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
        if(user.getId() == 0) {
            userRepository.save(user);
        } else {
            userRepository.update(user);
        }
        return user;
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

}
