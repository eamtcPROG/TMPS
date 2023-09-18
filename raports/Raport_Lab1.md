# Topic: SOLID Principles

## Author: Corețchi Mihai FAF-211

## Objectives:

1. Study and understand the SOLID Principles.

2. Choose a domain, define its main classes/models/entities and choose the appropriate instantiation mechanisms.

3. Create a sample project that respects SOLID Principles.

## Theory:

SOLID is a set of five object-oriented design principles intended to make software designs more maintainable, flexible, and easy to understand. The acronym stands for Single Responsibility Principle, Open-Closed Principle, Liskov Substitution Principle, Interface Segregation Principle, and Dependency Inversion Principle. Each principle addresses a specific aspect of software design, such as the organization of responsibilities, the handling of dependencies, and the design of interfaces. By following these principles, software developers can create more modular, testable, and reusable code that is easier to modify and extend over time. These principles are widely accepted and adopted in the software development community and can be applied to any object-oriented programming language.

## Implementation

The project is User Management System, which handles the CRUD operations for the users.

### Single Responsibility Principle

Each class in the system is designed with a single responsibility in mind:

1. JsonFileHandler is responsible for reading and writing JSON files.
2. UIHelper provides user interface functionalities.
3. UserMapper is used to convert between User entities and UserDTO


    public class JsonFileHandler {
    } - Read and write JSON file
    public class UIHelper {
    } - Display menu, read input, display message
    public class UserMapper {
    } - Convert between User and UserDTO

### Open/Closed Principle

DataSource interface makes the system open for extension. If you want to add another data source, like an XML file, you can easily implement the interface without altering existing code.

    public interface DataSource<T> {
        List<T> readData(String path);
        void writeData(String path, List<T> data);
    }

### Liskov Substitution Principle

All the implemented interfaces ensure that derived classes can replace the interface without altering the correctness of the program. For instance, UserService can seamlessly use any repository implementing the IUserRepository interface.

### Interface Segregation Principle

Instead of having one large interface, the functionality is broken down into smaller interfaces which makes it easier to implement and more readable.

    public interface IController {
        void create();
        void display();
    }
    public interface IRepository<T> {
    }
    public interface IService<T> {
    }
    public interface IUserController extends IController {
    }
    public interface IUserRepository extends IRepository<User> {
    }
    public interface IUserService extends IService<User> {
    }

### Dependency Inversion Principle

High-level modules like UserController or UserService are not dependent on low-level modules but depend on abstractions. This is evident with UserService depending on IUserRepository interface rather than the concrete UserRepository.

    public class UserController {
        private final IUserService userService;
        public UserController(IUserService userService) {
            this.userService = userService;
        }
    }
    public class UserService implements IUserService {
        private final IUserRepository userRepository;
        public UserService(IUserRepository userRepository) {
            this.userRepository = userRepository;
        }
    }

## Architecture Layers

The project follows a layered architecture:

1. Data Layer:

    1.1 Classes: JsonFileHandler, JsonDataSource.

    1.2 Purpose: Handling data persistence and retrieval from JSON files.
2. Repository Layer:

    2.1 Classes: UserRepository.

    2.2 Purpose: Provides CRUD operations for users. In this layer, DTOs from the Data Layer might be transformed into Models.
3. Service Layer:

    3.1 Classes: UserService.

    3.2 Purpose: Provides business logic and communicates with the repository layer. he Models might be transformed back to DTOs here for that purpose.
4. Controller Layer:

    4.1 Classes: UserController.

    4.2 Purpose: Handles user interaction and directs operations in the service layer.
5. Presentation/UI Layer:

    5.1 Classes: UIHelper, Main.

    5.2 Purpose: Interacts with the user and provides interface functionalities.

## Conclusion

This laboratory work helped me to understand SOLID principals better.
