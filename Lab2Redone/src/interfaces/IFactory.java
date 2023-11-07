
package interfaces;

public interface IFactory<T> {
    T create(String type);
}
