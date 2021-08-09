package bowling.domain.state.result;

import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @DisplayName("Strike는 isClean를 true로 반환해야한다")
    @Test
    void should_return_true_is_clean() {
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

}