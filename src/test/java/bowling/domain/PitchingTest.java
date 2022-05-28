package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.exception.IllegalPitchingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PitchingTest {
    @ParameterizedTest(name = "하나의 투구에 대한 {0}값을 갖는 객체 생성을 확인한다")
    @CsvSource({"0,0", "10,10"})
    void checkedPitchingObjectGenerate(int value, int expected) {
        // when
        Pitching pitching = new Pitching(value);

        // then
        assertThat(pitching.getValue()).isEqualTo(expected);
    }

    @ParameterizedTest(name = "투구에 대한 값이 0 ~ 10 사이의 숫자가 아닌 {0}인 경우, 예외처리를 한다")
    @ValueSource(strings = {"11", "-1"})
    void exceptionPitchingValueNotNumber(int value) {
        // when & then
        assertThatThrownBy(() -> new Pitching(value))
                .isInstanceOf(IllegalPitchingException.class);
    }

    @Test
    @DisplayName("투구 결과가 스트라이크인지 확인한다.")
    void checkedPitchingResultIsStrike() {
        // given
        int value = 10;

        // when
        Pitching pitching = new Pitching(value);

        // then
        assertThat(pitching.isStrike()).isTrue();
    }

    @Test
    @DisplayName("투구 결과가 스페어인지 확인한다.")
    void checkedPitchingResultIsSpare() {
        // given
        int value = 7;
        int otherValue = 3;

        // when
        Pitching pitching = new Pitching(value);
        Pitching otherPitching = new Pitching(otherValue);

        // then
        assertThat(pitching.isSpare(otherPitching)).isTrue();
    }

    @Test
    @DisplayName("투구 결과가 거터인지 확인한다.")
    void checkedPitchingResultIsGutter() {
        // given
        int value = 0;

        // when
        Pitching pitching = new Pitching(value);

        // then
        assertThat(pitching.isGutter()).isTrue();
    }

    @Test
    @DisplayName("투구 결과가 미스인지 확인한다.")
    void checkedPitchingResultIsMiss() {
        // given
        int value = 7;
        int otherValue = 2;

        // when
        Pitching pitching = new Pitching(value);
        Pitching otherPitching = new Pitching(otherValue);

        // then
        assertThat(pitching.isMiss(otherPitching)).isTrue();
    }
}
