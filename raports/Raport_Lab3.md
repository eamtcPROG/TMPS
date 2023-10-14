# Topic: Structural Design Patterns

## Author: Corețchi Mihai FAF-211

## Objectives:
1. Study and understand the Structural Design Patterns.
2. As a continuation of the previous laboratory work, think about the functionalities that your system will need to provide to the user.
3. Implement some additional functionalities, or create a new project using structural design patterns.

## Theoretical background:
Structural design patterns are a category of design patterns that focus on the composition of classes and objects to form larger structures and systems. They provide a way to organize objects and classes in a way that is both flexible and efficient, while allowing for the reuse and modification of existing code. Structural design patterns address common problems encountered in the composition of classes and objects, such as how to create new objects that inherit functionality from existing objects, how to create objects that share functionality without duplicating code, or how to define relationships between objects in a flexible and extensible way.

### Some examples from this category of design patterns are:
- Adapter
- Bridge
- Composite
- Decorator
- Facade
- Flyweight
- Proxy

---

## Implemented Structural Design Patterns

### 1. Adapter Pattern

The Adapter pattern allows two incompatible interfaces to work together. This makes one class look like another class by providing a wrapper around it.

#### Code Snippet
```java
public class XMLDataSourceAdapter<T> implements DataSource<T> {
    private final XMLHandler xmlHandler;
    private final Type type;

    public XMLDataSourceAdapter(XMLHandler xmlHandler, Type type) {
        this.xmlHandler = xmlHandler;
        this.type = type;
    }

    @Override
    public List<T> readData(String path) {
        return xmlHandler.readXMLFile(path, type);
    }

    @Override
    public void writeData(String path, List<T> data) {
        xmlHandler.writeXMLFile(path, data);
    }

}
```

### 2. Bridge Pattern

The Bridge pattern separates an object’s abstraction from its implementation, so you can vary them independently.

#### Code Snippet
```java
public interface DataSource<T> {
    List<T> readData(String path);
    void writeData(String path, List<T> data);
}
```

### 3. Decorator Pattern

The Decorator pattern is used to add new functionalities to an object without altering its structure.

#### Code Snippet
```java
public interface UserDecorator extends Cloneable {
    void display();
    User clone();

}

public class UserWithRole implements UserDecorator {
    private User user;
    private String role;

    public UserWithRole(User user, String role) {
        this.user = user;
        this.role = role;
    }

    @Override
    public void display() {
        user.toString();
        System.out.println("Role: " + role);
    }

    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }
}
```


### 4. Facade Pattern

The Facade pattern provides a unified interface to a set of interfaces in a subsystem.

#### Code Snippet
```java
public interface IFacade {
    void create();
    void display();
}

public class UserManagementFacade implements IFacade {

    private final IController userController;

    public UserManagementFacade(IController userController) {
        this.userController = userController;
    }

    @Override
    public void create() {
        userController.create();
    }

    @Override
    public void display() {
        userController.display();
    }

}
```

---

## Conclusion

Structural design patterns are incredibly useful for creating clean, maintainable, and scalable code. They help in defining clear roles and interfaces, allowing complex systems to be built from simpler components. In this lab, we have successfully implemented four structural design patterns, namely Adapter, Bridge, Decorator, and Facade.
