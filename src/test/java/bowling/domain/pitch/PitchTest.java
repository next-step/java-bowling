package bowling.domain.pitch;

import bowling.domain.exception.OutOfRangeArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PitchTest {
    public static final Pitch PIN_COUNT_0 = Pitch.valueOf(0);
    public static final Pitch PIN_COUNT_8 = Pitch.valueOf(8);
    public static final Pitch PIN_COUNT_10 = Pitch.valueOf(10);

    @DisplayName("점수를 저장할 수 있다.")
    @Test
    void init() {
        assertThat(PIN_COUNT_10).isEqualTo(Pitch.valueOf(10));
    }

    @DisplayName("범위 밖의 점수를 입력하면 에러")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void error(int score) {
        assertThatExceptionOfType(OutOfRangeArgumentException.class)
                .isThrownBy(() -> Pitch.valueOf(score));
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
        Pitch pitch = PIN_COUNT_8.next(2);
        assertThat(pitch.isSpare()).isTrue();
    }

    @DisplayName("거터 여부를 알 수 있다.")
    @Test
    void isGutter() {
        assertThat(PIN_COUNT_0.isGutter()).isTrue();
    }
}
