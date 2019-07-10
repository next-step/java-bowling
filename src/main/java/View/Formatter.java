package View;

@FunctionalInterface
public interface Formatter<T> {
    String format(T target);
}