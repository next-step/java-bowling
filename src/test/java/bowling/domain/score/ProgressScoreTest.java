package bowling.domain.score;

import bowling.exception.RangeArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.pin.Pins.MAX_COUNT_HIT_PINS;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ProgressScoreTest {

    @DisplayName("ProgressScore는 score와 count로 value로 생성한다")
    @Test
    void should_make_score() {
        //arrange, act, assert
        assertAll(
                () -> assertThat(ProgressScore.of(0, 1)).isEqualTo(ProgressScore.of(0, 1)),
                () -> assertThat(ProgressScore.of(1, 1)).isEqualTo(ProgressScore.of(1, 1)),
                () -> assertThat(ProgressScore.of(30, 0)).isEqualTo(ProgressScore.of(30, 0))
        );
    }

    @DisplayName("Progress value는 3을 넘기면 RangeArgumentException이 발생한다")
    @Test
    void should_throw_exception_left_count_over_2() {
        //arrange, act, assert
        assertThatThrownBy(() -> ProgressScore.of(30, 3)).isInstanceOf(RangeArgumentException.class);
    }

    @DisplayName("strike ProgressScore를 생성할수 있다")
    @Test
    void should_make_strike_score() {
        //arrange, act, assert
        assertThat(ProgressScore.ofStrike().getValue()).isEqualTo(MAX_COUNT_HIT_PINS);
    }

    @DisplayName("spare ProgressScore를 생성할수 있다")
    @Test
    void should_make_spare_score() {
        //arrange, act, assert
        assertThat(ProgressScore.ofSpare().getValue()).isEqualTo(MAX_COUNT_HIT_PINS);
    }

    @DisplayName("ProgressScore는 계산가능하지 않다")
    @Test
    void should_return_false_score_is_compute() {
        //arrange, act, assert
        assertFalse(ProgressScore.of(1, 1).isCompute());
    }

}