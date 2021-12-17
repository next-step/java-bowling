package qna.utils;

@FunctionalInterface
public interface ConsumerWithException<T, E extends Exception> {

    void accept(T t) throws E;
}