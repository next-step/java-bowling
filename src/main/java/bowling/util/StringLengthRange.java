package bowling.util;

import java.util.Objects;
import java.util.function.Supplier;

public class StringLengthRange {
    private final int minLength;
    private final int maxLength;

    public StringLengthRange(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public boolean test(String str) {
        return Objects.nonNull(str)
                && str.length() >= minLength
                && str.length() <= maxLength;
    }

    public void orThrow(String str, Supplier<RuntimeException> e) {
        if (!test(str)) {
            throw e.get();
        }
    }
}
