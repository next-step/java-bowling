package bowling.helper;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ValidInputHelper {
    private ValidInputHelper() {
    }

    public static <T> T get(Supplier<T> supplier, Consumer<RuntimeException> exceptionPrinter) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            exceptionPrinter.accept(e);
            return get(supplier, exceptionPrinter);
        }
    }
}
