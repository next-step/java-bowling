package bowling.util;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ValidationUtils {
    private ValidationUtils() {
        //no instance
    }

    public static<T> T throwIfNull(T obj) {
        if (Objects.isNull(obj)) {
            throw new IllegalArgumentException("주어진 객체가 null 입니다.");
        }
        return obj;
    }

    public static<T> T tryUntilSuccess(Supplier<T> supplier, Consumer<Throwable> onError) {
        try {
            return supplier.get();
        } catch (Exception e) {
            onError.accept(e);
            return tryUntilSuccess(supplier, onError);
        }
    }
}
