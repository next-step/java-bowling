package bowling.domain.state;

import bowling.domain.pin.Pins;
import bowling.domain.score.ComputableScore;
import bowling.domain.score.ProgressScore;
import bowling.domain.score.Score;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.*;

class SpareTest {

    @DisplayName("Spare 생성이 가능하다")
    @Test
    void should_make_spare() {
        //arrange, act, assert
        assertThat(Spare.of(Pins.of(1))).isInstanceOf(Spare.class);
    }

    @DisplayName("Spare는 End이므로 hitPins는 IllegalStateException을 반환한다")
    @Test
    void should_throw_exception_when_end_hit_pins() {
        //arrange
        Spare spare = Spare.of(Pins.of(1));

        //act, assert
        assertThatIllegalStateException().isThrownBy(() -> spare.hitPins(Pins.of(9)));
    }

    @DisplayName("Spare의 ProgressState는 FINISH이다")
    @Test
    void should_return_progress_state_is_finish() {
        //arrange
        Spare spare = Spare.of(Pins.of(1));

        //act, assert
        assertThat(spare.getProgressState()).isEqualTo(ProgressState.FINISH);
    }

    @DisplayName("Spare의 ResultState는 ALLHIT이다")
    @Test
    void should_return_result_state_is_all_hit() {
        //arrange
        Spare spare = Spare.of(Pins.of(1));

        //act, assert
        assertThat(spare.getResultState()).isEqualTo(ResultState.ALLHIT);
    }

    @DisplayName("first pitching에서 넘어온 첫번째 hit count를 반환한다")
    @Test
    void should_return_hit_count() {
        //arrange
        Spare spare = Spare.of(Pins.of(1));

        //act, assert
        assertThat(findHitCount(spare)).isEqualTo(1);
    }

    private Integer findHitCount(Spare spare) {
        return spare.getHitPins().stream().findFirst().orElse(0);
    }

    @DisplayName("spare의 경우 progress score를 반환한다")
    @Test
    void should_return_progress_score() {
        //arrange
        Spare spare = Spare.of(Pins.of(1));

        //act, assert
        assertThat(spare.score()).isEqualTo(ProgressScore.ofSpare());
    }

    @DisplayName("spare에 strike를 addScore하면 ComputableScore의 20value를 반환한다")
    @Test
    void should_return_score_with_strike() {
        //arrange
        Spare spare = Spare.of(Pins.of(1));
        Strike strike = Strike.of();

        //act, assert
        assertThat(spare.addScore(strike.score())).isEqualTo(ComputableScore.of(20));
    }

    @DisplayName("spare에 spare를 addScore하면 ComputableScore의 11value를 반환한다")
    @Test
    void should_return_score_with_spare() {
        //arrange
        Spare spare = Spare.of(Pins.of(1));
        Spare anotherSpare = Spare.of(Pins.of(1));

        //act, assert
        assertThat(spare.addScore(anotherSpare.score())).isEqualTo(ComputableScore.of(11));
    }

    @DisplayName("Spare에 socre를 더하는것은 상태에 따라 두 번 더한 스코어를 반환한다")
    @MethodSource
    @ParameterizedTest
    void should_return_add_score(Score score, Score expectedScore) {
        //arrange
        Spare spare = Spare.of(Pins.of(1));

        //act, assert
        assertThat(spare.addScore(score)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> should_return_add_score() {
        return Stream.of(
                Arguments.of(ProgressScore.of(1, 1), ComputableScore.of(2)),
                Arguments.of(ProgressScore.of(1, 2), ComputableScore.of(11))
        );
    }

}