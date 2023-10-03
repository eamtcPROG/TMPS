package user.repositories;

import general.interfaces.DataSource;
import user.interfaces.IUserRepository;
import user.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository implements IUserRepository {

    private static IUserRepository instance;
    private List<User> users = new ArrayList<>();
    private final DataSource<User> dataSource;
    private final String userJsonPath;

    public static IUserRepository getInstance(DataSource<User> dataSource, String userJsonPath) {
        if (instance == null) {
            instance = new UserRepository(dataSource, userJsonPath);
        }
        return instance;
    }
    public User acquireUser() {
        if (users.isEmpty()) {
            return new User();
        }
        return users.remove(0);
    }

    public void releaseUser(User user) {
        // Reset the state of the user object
        user.setId(0);
        user.setFirstName(null);
        user.setLastName(null);
        user.setEmail(null);
        user.setPhoneNumber(null);
        user.setDateOfBirth(null);

        // Add back to the pool
        users.add(user);
    }

    public UserRepository(DataSource<User> dataSource, String userJsonPath) {
        this.dataSource = dataSource;
        this.userJsonPath = userJsonPath;
        loadUsers();
    }


    private void loadUsers() {
        List<User> loadedUsers = dataSource.readData(userJsonPath);
        if (loadedUsers != null && !loadedUsers.isEmpty()) {
            users = loadedUsers;
        }
    }

    @Override
    public void save(User user) {
        User pooledUser = acquireUser();  // Acquire a user from the pool
        // Copy the data from the input user to the pooled user
        pooledUser.setId(user.getId());
        pooledUser.setFirstName(user.getFirstName());
        pooledUser.setLastName(user.getLastName());
        pooledUser.setEmail(user.getEmail());
        pooledUser.setDateOfBirth(user.getDateOfBirth());

        users.add(pooledUser);
        writeUsersToFile();

        releaseUser(pooledUser);  // Release the user back to the pool
    }

    @Override
    public User findById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public void update(User entity) {
        User pooledUser = acquireUser();  // Acquire a user from the pool

        // Copy the data from the input user to the pooled user
        pooledUser.setId(entity.getId());
        pooledUser.setFirstName(entity.getFirstName());
        pooledUser.setLastName(entity.getLastName());
        pooledUser.setEmail(entity.getEmail());
        pooledUser.setDateOfBirth(entity.getDateOfBirth());

        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == entity.getId()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            users.set(index, pooledUser);  // Update the user in the list
            writeUsersToFile();  // Write updated list to file
        }

        releaseUser(pooledUser);  // Release the user back to the pool
    }


    @Override
    public void delete(int id) {
        users = users.stream()
                .filter(user -> user.getId() != id)
                .collect(Collectors.toList());
        writeUsersToFile();
    }

    private void writeUsersToFile() {
        dataSource.writeData(userJsonPath, users);
    }
}
