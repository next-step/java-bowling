package bowling.domain.play;

import bowling.domain.Roll;
import bowling.domain.Rolls;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PlayMediatorTest {
    @ParameterizedTest
    @DisplayName("frameNo 가 10이 넘으면 last 이다.")
    @ValueSource(ints = {0, 1, 2, 9, 10, 11})
    void isLast(int frameNo) {
        assertThat(PlayMediator.isLast(frameNo))
                .isEqualTo(frameNo >= 10);
    }

    @Test
    @DisplayName("strike 인 경우 테스트")
    void strike() {
        Rolls rolls = new Rolls();
        rolls.add(Roll.of(10));
        rolls.add(Roll.of(10));
        assertAll(
                () -> assertThat(PlayMediator.isStrike(rolls))
                        .isTrue(),
                () -> assertThat(PlayMediator.isSpare(rolls))
                        .isFalse()
        );
    }

    @Test
    @DisplayName("spare 인 경우 테스트")
    void spare() {
        Rolls rolls = new Rolls();
        rolls.add(Roll.of(5));
        rolls.add(Roll.of(5));
        assertAll(
                () -> assertThat(PlayMediator.isStrike(rolls))
                        .isFalse(),
                () -> assertThat(PlayMediator.isSpare(rolls))
                        .isTrue()
        );
    }

    @Test
    @DisplayName("miss 인 경우 테스트")
    void miss() {
        Rolls rolls = new Rolls();
        rolls.add(Roll.of(1));
        rolls.add(Roll.of(1));
        assertAll(
                () -> assertThat(PlayMediator.isStrike(rolls))
                        .isFalse(),
                () -> assertThat(PlayMediator.isSpare(rolls))
                        .isFalse()
        );
    }
}
