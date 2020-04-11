package bowling.domain.frame;

import bowling.exception.FrameBowlCountOutOfRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class FrameBowlCountTest {
    @DisplayName("투구수를 생성할 수 있다.")
    @Test
    void create() {
        int count = 0;
        FrameBowlCount expect = new FrameBowlCount(count);

        FrameBowlCount actual = new FrameBowlCount(count);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("투구수가 0 ~ 10 이외의 숫자가 올경우 커스텀 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void validRange(int count) {
        assertThatExceptionOfType(FrameBowlCountOutOfRangeException.class).isThrownBy(
                () -> new FrameBowlCount(count)
        );
    }
}