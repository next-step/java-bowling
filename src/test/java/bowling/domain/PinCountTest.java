package bowling.domain;

import bowling.domain.exception.OutOfRangeArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class PinCountTest {
    public static final PinCount PIN_COUNT_1 = new PinCount(1);
    public static final PinCount PIN_COUNT_3 = new PinCount(3);
    public static final PinCount PIN_COUNT_8 = new PinCount(8);
    public static final PinCount PIN_COUNT_10 = new PinCount(10);

    @DisplayName("점수를 저장할 수 있다.")
    @Test
    void init() {
        int score = 10;
        assertThat(new PinCount(score)).isEqualTo(new PinCount(score));
    }

    @DisplayName("범위 밖의 점수를 입력하면 에러")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void error(int score) {
        assertThatExceptionOfType(OutOfRangeArgumentException.class)
                .isThrownBy(() -> new PinCount(score));
    }
}
