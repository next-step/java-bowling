package qna.exception;

import java.util.function.Consumer;

public class ConsumerExceptionWrapper {
    @FunctionalInterface
    public interface ConsumerWithException<T, E extends Exception> {
        void accept(T t) throws E;
    }

    public static <T, E extends Exception> Consumer<T> wrapper(ConsumerWithException<T, E> consumer) {
        return arg -> {
            try {
                consumer.accept(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
