package bowling.domain;

import bowling.domain.exception.OutOfRangeArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PinCountTest {
    public static final PinCount PIN_COUNT_0 = PinCount.valueOf(0);
    public static final PinCount PIN_COUNT_1 = PinCount.valueOf(1);
    public static final PinCount PIN_COUNT_2 = PinCount.valueOf(2);
    public static final PinCount PIN_COUNT_3 = PinCount.valueOf(3);
    public static final PinCount PIN_COUNT_8 = PinCount.valueOf(8);
    public static final PinCount PIN_COUNT_10 = PinCount.valueOf(10);

    @DisplayName("점수를 저장할 수 있다.")
    @Test
    void init() {
        assertThat(PIN_COUNT_10).isEqualTo(PinCount.valueOf(10));
    }

    @DisplayName("범위 밖의 점수를 입력하면 에러")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void error(int score) {
        assertThatExceptionOfType(OutOfRangeArgumentException.class)
                .isThrownBy(() -> PinCount.valueOf(score));
    }

    @DisplayName("최대 허용 갯수까지 저장되었는지 확인한다.")
    @Test
    void max() {
        assertThat(PIN_COUNT_10.isMax()).isTrue();
    }

    @DisplayName("다른 PinCount 와 더했을때 최대치를 넘지 않아야 한다.")
    @Test
    void isAddable() {
        assertThat(PIN_COUNT_8.isOverMaxAfterAdd(3)).isTrue();
        assertThat(PIN_COUNT_8.isOverMaxAfterAdd(1)).isFalse();
    }

    @DisplayName("스트라이크 여부를 알 수 있다.")
    @Test
    void isStrike() {
        assertThat(PIN_COUNT_10.isStrike()).isTrue();
    }

    @DisplayName("스페어 처리 여부를 알 수 있다.")
    @Test
    void isSpare() {
        PinCount pinCount = PIN_COUNT_8.next(2);
        assertThat(pinCount.isSpare()).isTrue();
    }

    @DisplayName("거터 여부를 알 수 있다.")
    @Test
    void isGutter() {
        assertThat(PIN_COUNT_0.isGutter()).isTrue();
    }
}
