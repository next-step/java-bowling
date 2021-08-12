package bowling.domain.state.result;

import bowling.domain.pin.Pins;
import bowling.domain.score.ComputableScore;
import bowling.domain.score.ProgressScore;
import bowling.domain.score.Score;
import bowling.domain.state.BunchState;
import bowling.domain.state.pitching.LastPitching;
import bowling.domain.state.pitching.SecondPitching;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StrikeTest {

    @DisplayName("Strike 생성이 가능하다")
    @Test
    void should_make_strike() {
        //arrange, act, assert
        assertThat(Strike.of()).isInstanceOf(Strike.class);
    }

    @DisplayName("Strike는 End이므로 hitPins는 IllegalStateException을 반환한다")
    @Test
    void should_throw_exception_when_end_hit_pins() {
        //arrange
        Strike strike = Strike.of();

        //act, assert
        assertThatIllegalStateException().isThrownBy(() -> strike.hitPins(Pins.of(10)));
    }

    @DisplayName("Strike는 isFinish를 true로 반환해야한다")
    @Test
    void should_return_true_is_finish() {
        //arrange
        Strike strike = Strike.of();

        //act, assert
        assertTrue(strike.isFinish());
    }

    @DisplayName("Strike는 isAllHit를 true로 반환해야한다")
    @Test
    void should_return_true_is_all_hit() {
        //arrange
        Strike strike = Strike.of();

        //act, assert
        assertTrue(strike.isAllHit());
    }

    @DisplayName("Strike는 isMiss을 false로 반환해야한다")
    @Test
    void should_return_false_is_miss() {
        //arrange
        Strike strike = Strike.of();

        //act, assert
        assertFalse(strike.isMiss());
    }

    @DisplayName("max hit count 10을 반환한다")
    @Test
    void should_return_max_hit_count() {
        //arrange
        Strike strike = Strike.of();

        //act, assert
        assertThat(findHitCount(strike)).isEqualTo(10);
    }

    private Integer findHitCount(Strike strike) {
        return strike.getHitPins().stream().findFirst().orElse(0);
    }

    @DisplayName("strike의 경우 progress score를 반환한다")
    @Test
    void should_return_progress_score() {
        //arrange
        Strike strike = Strike.of();

        //act, assert
        assertThat(strike.score()).isEqualTo(ProgressScore.ofStrike());
    }

    @DisplayName("strike에 strike를 addScore하면 ProgressScore의 20value를 반환한다")
    @Test
    void should_return_progress_score_with_strike() {
        //arrange
        Strike strike = Strike.of();
        Strike anotherStrike = Strike.of();

        //act, assert
        assertThat(strike.addScore(anotherStrike.score())).isEqualTo(ProgressScore.of(20, 1));
    }

    @DisplayName("strike에 spare를 addScore하면 ComputableScore의 20value를 반환한다")
    @Test
    void should_return_progress_score_with_spare() {
        //arrange
        Strike strike = Strike.of();
        Spare spare = Spare.of(Pins.of(1));

        //act, assert
        assertThat(strike.addScore(spare.score())).isEqualTo(ComputableScore.of(20));
    }

    @DisplayName("strike의 addScore를 하면 기존 값에 10을 더하여 반환한다")
    @MethodSource
    @ParameterizedTest
    void should_return_add_score(Score score, Score expectedScore) {
        //arrange
        Strike strike = Strike.of();

        //act, assert
        assertThat(strike.addScore(score)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> should_return_add_score() {
        return Stream.of(
                Arguments.of(ProgressScore.of(1, 2), ProgressScore.of(11, 1)),
                Arguments.of(ProgressScore.of(1, 1), ComputableScore.of(11))
        );
    }

}