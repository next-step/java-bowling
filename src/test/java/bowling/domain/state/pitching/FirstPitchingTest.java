package bowling.domain.state.pitching;

import bowling.domain.pin.Pins;
import bowling.domain.state.result.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FirstPitchingTest {

    @DisplayName("FirstPitching 생성이 가능하다")
    @Test
    void should_make_first_pitching() {
        //arrange, act, assert
        assertThat(FirstPitching.of()).isInstanceOf(FirstPitching.class);
    }

    @DisplayName("pins 10개이면 Strike가 반환된다")
    @Test
    void should_return_strike() {
        //arrange
        FirstPitching firstPitching = FirstPitching.of();
        Pins pins = Pins.of(10);

        //act, assert
        assertThat(firstPitching.hitPins(pins)).isInstanceOf(Strike.class);
    }

    @DisplayName("pins 10개이하이면 SecondPitching이 반환된다")
    @Test
    void should_return_second() {
        //arrange
        FirstPitching firstPitching = FirstPitching.of();
        Pins pins = Pins.of(9);

        //act, assert
        assertThat(firstPitching.hitPins(pins)).isInstanceOf(SecondPitching.class);
    }

    @DisplayName("hit Pins는 empty로 반환된다")
    @Test
    void should_return_hit_pins_empty() {
        //arrange
        FirstPitching firstPitching = FirstPitching.of();

        //act, assert
        assertThat(firstPitching.getHitPins()).isEmpty();
    }

}