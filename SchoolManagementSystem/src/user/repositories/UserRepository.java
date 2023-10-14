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
    private final String dataPath;

    public static IUserRepository getInstance(DataSource<User> dataSource, String dataPath) {
        if (instance == null) {
            instance = new UserRepository(dataSource, dataPath);
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

        user.setId(0);
        user.setFirstName(null);
        user.setLastName(null);
        user.setEmail(null);
        user.setPhoneNumber(null);
        user.setDateOfBirth(null);

  
        users.add(user);
    }

    public UserRepository(DataSource<User> dataSource, String dataPath) {
        this.dataSource = dataSource;
        this.dataPath = dataPath;
        loadUsers();
    }


    private void loadUsers() {
        List<User> loadedUsers = dataSource.readData(dataPath);
        if (loadedUsers != null && !loadedUsers.isEmpty()) {
            users = loadedUsers;
        }
    }

    @Override
    public void save(User user) {
        User pooledUser = acquireUser();

        pooledUser.setId(user.getId());
        pooledUser.setFirstName(user.getFirstName());
        pooledUser.setLastName(user.getLastName());
        pooledUser.setEmail(user.getEmail());
        pooledUser.setDateOfBirth(user.getDateOfBirth());

        users.add(pooledUser);
        writeUsersToFile();

        releaseUser(pooledUser);
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
        User pooledUser = acquireUser();


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
            users.set(index, pooledUser);
            writeUsersToFile();
        }

        releaseUser(pooledUser);
    }


    @Override
    public void delete(int id) {
        users = users.stream()
                .filter(user -> user.getId() != id)
                .collect(Collectors.toList());
        writeUsersToFile();
    }

    private void writeUsersToFile() {
        dataSource.writeData(dataPath, users);
    }
}
