package bowling.domain.state.result;

import bowling.domain.pin.Pins;
import bowling.domain.state.BunchState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.*;

class FinishTest {

    @DisplayName("Finish 생성이 가능하다")
    @Test
    void should_make_finish() {
        //arrange, act, assert
        assertThat(Finish.of(BunchState.of())).isInstanceOf(Finish.class);
    }

    @DisplayName("Finish End이므로 hitPins는 IllegalStateException을 반환한다")
    @Test
    void should_throw_exception_when_end_hit_pins() {
        //arrange
        Finish finish = Finish.of(BunchState.of());

        //act, assert
        assertThatIllegalStateException().isThrownBy(() -> finish.hitPins(Pins.of(9)));
    }

    @DisplayName("Finish는 isFinish를 true로 반환해야한다")
    @Test
    void should_return_true_is_finish() {
        //arrange
        Finish finish = Finish.of(BunchState.of());

        //act, assert
        assertTrue(finish.isFinish());
    }

    @DisplayName("Finish는 isClean를 false로 반환해야한다")
    @Test
    void should_return_false_is_clean() {
        //arrange
        Finish finish = Finish.of(BunchState.of());

        //act, assert
        assertFalse(finish.isAllHit());
    }

    @DisplayName("Finish는 isMiss을 false를 반환해야한다")
    @Test
    void should_return_true_is_miss() {
        //arrange
        Finish finish = Finish.of(BunchState.of());

        //act, assert
        assertFalse(finish.isMiss());
    }

    @DisplayName("hit count 0을 반환한다")
    @Test
    void should_return_hit_count() {
        //arrange
        Finish finish = Finish.of(BunchState.of());

        //act, assert
        assertThat(finish.getHitPins().size()).isEqualTo(0);
    }

}