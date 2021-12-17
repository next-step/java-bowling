package qna.utils;

import java.util.function.Consumer;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionWrapper {

    public static <T, R, E extends Exception> Function<T, R> wrapper(
        FunctionWithException<T, R, E> fe) {
        return arg -> {
            try {
                return fe.apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T, E extends Exception> Consumer<T> wrapperAccept(
        ConsumerWithException<T, E> fe) {
        return arg -> {
            try {
                fe.accept(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
