package bowling.domain.state.pitching;

import bowling.domain.pin.Pins;
import bowling.domain.state.result.Miss;
import bowling.domain.state.result.Spare;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SecondPitchingTest {

    @DisplayName("SecondPitching 생성이 가능하다")
    @Test
    void should_make_second_pitching() {
        //arrange, act, assert
        assertThat(SecondPitching.of(Pins.of(10))).isInstanceOf(SecondPitching.class);
    }

    @DisplayName("pins hit 총합 10개이면 Sparer가 반환된다")
    @Test
    void should_return_spare() {
        //arrange
        SecondPitching secondPitching = SecondPitching.of(Pins.of(9));

        //act, assert
        assertThat(secondPitching.hitPins(Pins.of(1))).isInstanceOf(Spare.class);
    }

    @DisplayName("pins hit 총합 10개이하이면 Miss이 반환된다")
    @Test
    void should_return_second() {
        //arrange
        SecondPitching secondPitching = SecondPitching.of(Pins.of(3));

        //act, assert
        assertThat(secondPitching.hitPins(Pins.of(1))).isInstanceOf(Miss.class);
    }

    @DisplayName("hit Pins는 총 pins수를 반환한다")
    @Test
    void should_return_hit_pins_empty() {
        //arrange
        SecondPitching secondPitching = SecondPitching.of(Pins.of(3));

        //act, assert
        assertThat(secondPitching.getHitPins()).contains(3);
    }

    @DisplayName("secondPitching은 isFinish를 false로 반환해야한다")
    @Test
    void should_return_true_is_finish() {
        //arrange
        SecondPitching secondPitching = SecondPitching.of(Pins.of(10));

        //act, assert
        assertFalse(secondPitching.isFinish());
    }

    @DisplayName("secondPitching은 isAllHit를 false로 반환해야한다")
    @Test
    void should_return_false_is_all_hit() {
        //arrange
        SecondPitching secondPitching = SecondPitching.of(Pins.of(10));

        //act, assert
        assertFalse(secondPitching.isAllHit());
    }

    @DisplayName("secondPitching은 isMiss을 false로 반환해야한다")
    @Test
    void should_return_true_is_miss() {
        //arrange
        SecondPitching secondPitching = SecondPitching.of(Pins.of(10));

        //act, assert
        assertFalse(secondPitching.isMiss());
    }

}