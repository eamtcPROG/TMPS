package user.mediator;

import general.interfaces.Mediator;
import general.interfaces.IFacade;
import general.interfaces.IRepository;
import general.interfaces.DataSource;
import user.controllers.UserController;
import user.facade.UserManagementFacade;
import user.interfaces.IUserService;
import user.repositories.UserRepository;
import user.services.UserService;
import general.datasources.json.JsonDataSource;
import general.datasources.xml.XMLDataSourceAdapter;
import general.datasources.xml.XMLFileHandler;
import general.interfaces.XMLHandler;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import user.models.User;

public class UserManagementMediator implements Mediator {
    private IFacade userManagementFacade;
    private Type listType;
    private DataSource<User> jsonDataSource;
    private DataSource<User> xmlDataSource;
    private String currentDataSource;

    public UserManagementMediator(IFacade userManagementFacade) {
        this.userManagementFacade = userManagementFacade;
        this.listType = new TypeToken<List<User>>() {}.getType();
        this.jsonDataSource = new JsonDataSource<>(listType);
        this.xmlDataSource = new XMLDataSourceAdapter<>(new XMLFileHandler(), listType);
        this.currentDataSource = "JSON";
    }

    @Override
    public void createUser() {
        userManagementFacade.create();
    }

    @Override
    public void displayUsers() {
        userManagementFacade.display();
    }

    @Override
    public void switchDataSource() {
        IRepository<User> userRepository;
        IUserService userService;
        UserController userController;

        if ("JSON".equals(currentDataSource)) {
            userRepository = UserRepository.getInstance(xmlDataSource, "../SchoolManagementSystem/resources/user/user.xml");
            userService = UserService.getInstance(userRepository);
            userController = new UserController(userService);
            userManagementFacade = new UserManagementFacade(userController);
            currentDataSource = "XML";
            System.out.println("Switched to XML data source.");
        } else {
            userRepository = UserRepository.getInstance(jsonDataSource, "../SchoolManagementSystem/resources/user/user.json");
            userService = UserService.getInstance(userRepository);
            userController = new UserController(userService);
            userManagementFacade = new UserManagementFacade(userController);
            currentDataSource = "JSON";
            System.out.println("Switched to JSON data source.");
        }
    }
}
