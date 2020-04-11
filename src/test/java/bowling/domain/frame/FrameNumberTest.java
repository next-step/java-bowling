package bowling.domain.frame;

import bowling.exception.FrameNumberOutOfRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class FrameNumberTest {

    @DisplayName("프레임 번호를 생성할 수 있다.")
    @Test
    void create() {
        int number = 1;
        FrameNumber expect = new FrameNumber(number);

        FrameNumber actual = new FrameNumber(number);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("프레임 번호가 1 ~ 10 범위 내가 아니라면 커스텀 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 11})
    void validRange(int number) {
        assertThatExceptionOfType(FrameNumberOutOfRangeException.class).isThrownBy(
                () -> new FrameNumber(number)
        );
    }
}