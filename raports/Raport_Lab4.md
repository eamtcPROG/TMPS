# Topic: Behavioral Design Patterns

## Author: Corețchi Mihai FAF-211

## Objectives:
1. Study and understand the Behavioral Design Patterns.
2. As a continuation of the previous laboratory work, think about the functionalities that your system will need to provide to the user.
3. Implement some additional functionalities, or create a new project using structural design patterns.

## Theoretical background:
Behavioral design patterns are a category of design patterns that focus on the interaction and communication between objects and classes. They provide a way to organize the behavior of objects in a way that is both flexible and reusable, while separating the responsibilities of objects from the specific implementation details. Behavioral design patterns address common problems encountered in object behavior, such as how to define interactions between objects, how to control the flow of messages between objects, or how to define algorithms and policies that can be reused across different objects and classes.

### Some examples from this category of design patterns are:
- Chain of Responsibility
- Command
- Interpreter
- Iterator
- Mediator
- Observer
- Strategy

---

## Implemented Structural Design Patterns

### 1. Mediator Pattern

The Mediator pattern reduces the complexity of communication between multiple objects or classes. It centralizes complex communications and control logic between objects in the system.

#### Code Snippet
```java
public class UserManagementMediator implements Mediator {
    private IFacade userManagementFacade;

    public UserManagementMediator(IFacade userManagementFacade) {
        this.userManagementFacade = userManagementFacade;
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
        // Implementation of data source switching logic
    }
}

```

### 2. Command Pattern

The Command pattern turns a request into a stand-alone object that contains all information about the request. This transformation allows you to parameterize methods with different requests, delay or queue a request's execution, and support undoable operations.

#### Code Snippet
```java
public interface Command {
    void execute();
}

public class CreateUserCommand implements Command {
    private IUserService userService;

    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() {
        userService.createUser();
    }
}

```

### 3. Observer Pattern

The Observer pattern provides a subscription mechanism to allow multiple objects to listen and react to events or changes happening in another object.

#### Code Snippet
```java
public interface Observer {
    void update();
}

public class UserNotificationObserver implements Observer {
    @Override
    public void update() {
        System.out.println("User data has changed. Notifying relevant parties...");
    }
}

public class User {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

```


---

## Conclusion

Behavioral design patterns like Mediator, Command, and Observer have been implemented to enhance system interaction and maintainability. The Mediator pattern simplifies communication by centralizing control, the Command pattern encapsulates actions as objects for flexibility, and the Observer pattern allows for dynamic event handling. These patterns collectively contribute to a system that is more organized and easier to manage.
