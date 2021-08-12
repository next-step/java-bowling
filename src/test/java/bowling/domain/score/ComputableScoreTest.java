package bowling.domain.score;

import bowling.exception.RangeArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ComputableScoreTest {

    @DisplayName("ComputableScore는 value로 생성한다")
    @Test
    void should_make_computable_score_with_value() {
        //arrange, act, assert
        assertAll(
                () -> assertThat(ComputableScore.of(0)).isEqualTo(ComputableScore.of(0)),
                () -> assertThat(ComputableScore.of(1)).isEqualTo(ComputableScore.of(1)),
                () -> assertThat(ComputableScore.of(30)).isEqualTo(ComputableScore.of(30))
        );
    }

    @DisplayName("ComputableScore는 value로 생성한다")
    @Test
    void should_make_computable_score_with_score() {
        //arrange, act, assert
        assertAll(
                () -> assertThat(ComputableScore.of(CommonScore.of(0))).isEqualTo(ComputableScore.of(0)),
                () -> assertThat(ComputableScore.of(CommonScore.of(1))).isEqualTo(ComputableScore.of(1)),
                () -> assertThat(ComputableScore.of(CommonScore.of(30))).isEqualTo(ComputableScore.of(30))
        );
    }

    @DisplayName("ComputableScore value는 30을 넘기면 RangeArgumentException이 발생한다")
    @Test
    void should_throw_exception_value_over_30() {
        //arrange, act, assert
        assertThatThrownBy(() -> ComputableScore.of(31)).isInstanceOf(RangeArgumentException.class);
    }

    @DisplayName("ComputableScore는 계산가능하지 않다")
    @Test
    void should_return_true_computable_score_is_compute() {
        //arrange, act, assert
        assertTrue(ComputableScore.of(1).isCompute());
    }

    @DisplayName("ComputableScore는 add할 수 없고 IllegalStateException을 반환한다")
    @Test
    void should_add_computable_score() {
        //arrange, act, assert
        assertThatIllegalStateException().isThrownBy(() -> ComputableScore.of(1).add(ComputableScore.of(2)));
    }

}