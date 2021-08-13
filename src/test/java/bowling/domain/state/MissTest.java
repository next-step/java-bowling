package bowling.domain.state;

import bowling.domain.pin.Pins;
import bowling.domain.score.ComputableScore;
import bowling.domain.score.ProgressScore;
import bowling.domain.score.Score;
import bowling.domain.state.Miss;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.*;

class MissTest {

    @DisplayName("Miss 생성이 가능하다")
    @Test
    void should_make_miss() {
        //arrange, act, assert
        assertThat(Miss.of(Pins.of(1), Pins.of(9))).isInstanceOf(Miss.class);
    }

    @DisplayName("Miss는 End이므로 hitPins는 IllegalStateException을 반환한다")
    @Test
    void should_throw_exception_when_end_hit_pins() {
        //arrange
        Miss miss = Miss.of(Pins.of(1), Pins.of(9));

        //act, assert
        assertThatIllegalStateException().isThrownBy(() -> miss.hitPins(Pins.of(9)));
    }

    @DisplayName("Miss는는 isFinish를 true로 반환해야한다")
    @Test
    void should_return_true_is_finish() {
        //arrange
        Miss miss = Miss.of(Pins.of(1), Pins.of(9));

        //act, assert
        assertTrue(miss.isFinish());
    }

    @DisplayName("Miss는 isAllHit를 false로 반환해야한다")
    @Test
    void should_return_false_is_all_hit() {
        //arrange
        Miss miss = Miss.of(Pins.of(1), Pins.of(9));

        //act, assert
        assertFalse(miss.isAllHit());
    }

    @DisplayName("Miss는 isMiss을 true로 반환해야한다")
    @Test
    void should_return_true_is_miss() {
        //arrange
        Miss miss = Miss.of(Pins.of(1), Pins.of(9));

        //act, assert
        assertTrue(miss.isMiss());
    }

    @DisplayName("first pitching과 second pitching에서 넘어온 hit count를 반환한다")
    @Test
    void should_return_hit_count() {
        //arrange
        Miss miss = Miss.of(Pins.of(1), Pins.of(9));

        //act, assert
        assertAll(
                () -> assertThat(miss.getHitPins().get(0)).isEqualTo(1),
                () -> assertThat(miss.getHitPins().get(1)).isEqualTo(9)
        );
    }

    @DisplayName("miss는 ComputableScore를 반환한다")
    @Test
    void should_return_computable_score() {
        //arrange
        Miss miss = Miss.of(Pins.of(1), Pins.of(2));

        //act, assert
        assertThat(miss.score()).isEqualTo(ComputableScore.of(3));
    }

    @DisplayName("Miss에서 score를 더하면 상태에 따라 두 번 더한 스코어를 반환한다")
    @MethodSource
    @ParameterizedTest
    void should_return_add_score(Score score, Score expectedScore) {
        //arrange
        Miss miss = Miss.of(Pins.of(1), Pins.of(2));

        //act, assert
        assertThat(miss.addScore(score)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> should_return_add_score() {
        return Stream.of(
                Arguments.of(ProgressScore.ofStrike(), ComputableScore.of(13)),
                Arguments.of(ProgressScore.ofSpare(), ComputableScore.of(11)),
                Arguments.of(ProgressScore.of(1, 1), ComputableScore.of(2)),
                Arguments.of(ProgressScore.of(1, 2), ComputableScore.of(4))
        );
    }

}