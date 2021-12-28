package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import qna.domain.Score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PinsTest {
    public static final Pins FIVE = Pins.create(5);
    public static final Pins FOUR = Pins.create(4);
    public static final Pins ZERO = Pins.create(0);
    public static final Pins TEN = Pins.create(10);

    @DisplayName("Pin 생성")
    @ParameterizedTest
    @ValueSource(ints = {Pins.MIN_PINS, (Pins.MIN_PINS + Pins.MAX_PINS) / 2, Pins.MAX_PINS})
    void create(int pins) {
        // when & then
        assertThat(Pins.create(pins)).isNotNull();
    }

    @DisplayName("Pins 생성 예외 - 잘못된 생성 결과 전달")
    @ParameterizedTest
    @ValueSource(ints = {Pins.MIN_PINS - 1, Pins.MAX_PINS + 1})
    void createFailed(int invalidPins) {
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Pins.create(invalidPins));
    }

    @DisplayName("Pins 의 값 반환")
    @Test
    void getValue() {
        // given
        Pins pins = Pins.create(Pins.MIN_PINS);
        // when & then
        assertThat(pins.getValue()).isEqualTo(Pins.MIN_PINS);
    }

    @DisplayName("Pins 의 strike 확인")
    @Test
    void isStrike() {
        // given
        Pins pins = Pins.create(Pins.MAX_PINS);
        // when & then
        assertThat(pins.isStrike()).isTrue();
    }

    @DisplayName("Pins 의 spare 확인")
    @Test
    void isSpare() {
        // given
        Pins first = Pins.create(7);
        Pins second = Pins.create(3);
        // when & then
        assertThat(first.isSpare(second)).isTrue();
    }

    @DisplayName("Pins 의 gutter 확인")
    @Test
    void isGutter() {
        // given
        Pins pins = Pins.create(0);
        // when & then
        assertThat(pins.isGutter()).isTrue();
    }

    @DisplayName("Pins 의 Score 반환")
    @Test
    void score() {
        // given
        Pins pins = Pins.create(5);
        // when & then
        assertThat(pins.score()).isEqualTo(Score.withNonRemainingPitches(5));
    }

    @DisplayName("두 Pins 의 Score 합 반환")
    @Test
    void scoreWithOtherPinsScore() {
        // given
        int pinScore = 5;
        int otherPinScore = 5;
        // when
        Score score = Pins.create(pinScore).score(Pins.create(otherPinScore));
        // then
        assertThat(score).isEqualTo(Score.withNonRemainingPitches(pinScore + otherPinScore));
    }
}
