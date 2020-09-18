package bowling.util;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ValidationUtilsTest {
    @Test
    public void throwIfNull() {
        assertThatIllegalArgumentException().isThrownBy(() -> ValidationUtils.throwIfNull(null));
    }

    @Test
    public void tryUntilSuccess() {
        Supplier<Integer> supplier = new Supplier<Integer>() {
            int i = 0;
            @Override
            public Integer get() {
                i += 1;
                if (i < 5) {
                    throw new RuntimeException();
                }
                return i;
            }
        };
        int result = ValidationUtils.tryUntilSuccess(supplier, t -> {});
        assertThat(result).isEqualTo(5);
    }
}
