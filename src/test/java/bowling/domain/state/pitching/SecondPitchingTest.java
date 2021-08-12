package bowling.domain.state.pitching;

import bowling.domain.pin.Pins;
import bowling.domain.score.ComputableScore;
import bowling.domain.score.ProgressScore;
import bowling.domain.score.Score;
import bowling.domain.state.result.Miss;
import bowling.domain.state.result.Spare;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SecondPitchingTest {

    @DisplayName("SecondPitching 생성이 가능하다")
    @Test
    void should_make_second_pitching() {
        //arrange, act, assert
        assertThat(SecondPitching.of(Pins.of(10))).isInstanceOf(SecondPitching.class);
    }

    @DisplayName("pins hit 총합 10개이면 Sparer가 반환된다")
    @Test
    void should_return_spare() {
        //arrange
        SecondPitching secondPitching = SecondPitching.of(Pins.of(9));

        //act, assert
        assertThat(secondPitching.hitPins(Pins.of(1))).isInstanceOf(Spare.class);
    }

    @DisplayName("pins hit 총합 10개이하이면 Miss이 반환된다")
    @Test
    void should_return_second() {
        //arrange
        SecondPitching secondPitching = SecondPitching.of(Pins.of(3));

        //act, assert
        assertThat(secondPitching.hitPins(Pins.of(1))).isInstanceOf(Miss.class);
    }

    @DisplayName("hit Pins는 총 pins수를 반환한다")
    @Test
    void should_return_hit_pins_empty() {
        //arrange
        SecondPitching secondPitching = SecondPitching.of(Pins.of(3));

        //act, assert
        assertThat(secondPitching.getHitPins()).contains(3);
    }

    @DisplayName("secondPitching은 isFinish를 false로 반환해야한다")
    @Test
    void should_return_true_is_finish() {
        //arrange
        SecondPitching secondPitching = SecondPitching.of(Pins.of(10));

        //act, assert
        assertFalse(secondPitching.isFinish());
    }

    @DisplayName("secondPitching은 isAllHit를 false로 반환해야한다")
    @Test
    void should_return_false_is_all_hit() {
        //arrange
        SecondPitching secondPitching = SecondPitching.of(Pins.of(10));

        //act, assert
        assertFalse(secondPitching.isAllHit());
    }

    @DisplayName("secondPitching은 isMiss을 false로 반환해야한다")
    @Test
    void should_return_true_is_miss() {
        //arrange
        SecondPitching secondPitching = SecondPitching.of(Pins.of(10));

        //act, assert
        assertFalse(secondPitching.isMiss());
    }

    @DisplayName("InProgress 상태에 스코어를 더하면 한 번 더한 스코어를 반환한다")
    @MethodSource
    @ParameterizedTest
    void should_add_score(Score score, Score expectedScore) {
        //arrange
        SecondPitching secondPitching = SecondPitching.of(Pins.of(1));

        //act, assert
        assertThat(secondPitching.addScore(score)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> should_add_score() {
        return Stream.of(
                Arguments.of(ProgressScore.ofStrike(), ProgressScore.of(11, 1)),
                Arguments.of(ProgressScore.ofSpare(), ComputableScore.of(11)),
                Arguments.of(ProgressScore.of(1, 1), ComputableScore.of(2)),
                Arguments.of(ProgressScore.of(1, 2), ProgressScore.of(2, 1))
        );
    }

}