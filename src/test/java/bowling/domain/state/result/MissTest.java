package bowling.domain.state.result;

import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @DisplayName("Miss는 isClean를 false로 반환해야한다")
    @Test
    void should_return_false_is_clean() {
        //arrange
        Miss miss = Miss.of(Pins.of(1), Pins.of(9));

        //act, assert
        assertFalse(miss.isClean());
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

}