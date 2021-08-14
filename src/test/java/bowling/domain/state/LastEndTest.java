package bowling.domain.state;

import bowling.domain.pin.Pins;
import bowling.domain.state.BunchState;
import bowling.domain.state.LastEnd;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.*;

class LastEndTest {

    @DisplayName("Finish 생성이 가능하다")
    @Test
    void should_make_finish() {
        //arrange, act, assert
        assertThat(LastEnd.of(BunchState.of())).isInstanceOf(LastEnd.class);
    }

    @DisplayName("Finish End이므로 hitPins는 IllegalStateException을 반환한다")
    @Test
    void should_throw_exception_when_end_hit_pins() {
        //arrange
        LastEnd lastEnd = LastEnd.of(BunchState.of());

        //act, assert
        assertThatIllegalStateException().isThrownBy(() -> lastEnd.hitPins(Pins.of(9)));
    }

    @DisplayName("Finish는 isFinish를 true로 반환해야한다")
    @Test
    void should_return_true_is_finish() {
        //arrange
        LastEnd lastEnd = LastEnd.of(BunchState.of());

        //act, assert
        assertTrue(lastEnd.isFinish());
    }

    @DisplayName("hit count 0을 반환한다")
    @Test
    void should_return_hit_count() {
        //arrange
        LastEnd lastEnd = LastEnd.of(BunchState.of());

        //act, assert
        assertThat(lastEnd.getHitPins().size()).isEqualTo(0);
    }

}