package bowling.domain.state.result;

import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @DisplayName("Spare는 isFinish를 true로 반환해야한다")
    @Test
    void should_return_true_is_finish() {
        //arrange
        Spare spare = Spare.of(Pins.of(1));

        //act, assert
        assertTrue(spare.isFinish());
    }

    @DisplayName("Spare는 isAllHit를 true로 반환해야한다")
    @Test
    void should_return_true_is_all_hit() {
        //arrange
        Spare spare = Spare.of(Pins.of(1));

        //act, assert
        assertTrue(spare.isAllHit());
    }

    @DisplayName("Spare는 isMiss을 false로 반환해야한다")
    @Test
    void should_return_false_is_miss() {
        //arrange
        Spare spare = Spare.of(Pins.of(1));

        //act, assert
        assertFalse(spare.isMiss());
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

}