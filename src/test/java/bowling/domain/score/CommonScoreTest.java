package bowling.domain.score;

import bowling.exception.RangeArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.pin.Pins.MAX_COUNT_HIT_PINS;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CommonScoreTest {

    @DisplayName("Score는 value로 생성한다")
    @Test
    void should_make_score() {
        //arrange, act, assert
        assertAll(
                () -> assertThat(CommonScore.of(0)).isEqualTo(CommonScore.of(0)),
                () -> assertThat(CommonScore.of(1)).isEqualTo(CommonScore.of(1)),
                () -> assertThat(CommonScore.of(30)).isEqualTo(CommonScore.of(30))
        );
    }

    @DisplayName("Score value는 30을 넘기면 RangeArgumentException이 발생한다")
    @Test
    void should_throw_exception_value_over_30() {
        //arrange, act, assert
        assertThatThrownBy(() -> CommonScore.of(31)).isInstanceOf(RangeArgumentException.class);
    }

    @DisplayName("strike score value는 10이다")
    @Test
    void should_strike_value_is_10() {
        //arrange, act, assert
        assertThat(CommonScore.ofStrike().getValue()).isEqualTo(MAX_COUNT_HIT_PINS);
    }

    @DisplayName("base 기본 score value는 0이다")
    @Test
    void should_base_value_is_0() {
        //arrange, act, assert
        assertThat(CommonScore.ofBase().getValue()).isEqualTo(0);
    }

    @DisplayName("score는 계산가능하지 않다")
    @Test
    void should_return_false_score_is_compute() {
        //arrange, act, assert
        assertFalse(CommonScore.of(1).isCompute());
    }

    @DisplayName("score끼리 add할 수 있다")
    @Test
    void should_add_score() {
        //arrange
        CommonScore score1 = CommonScore.of(1);
        CommonScore score2 = CommonScore.of(2);

        //act
        Score resultScore = score1.add(score2);

        //assert
        assertThat(resultScore).isEqualTo(CommonScore.of(3));
    }

}