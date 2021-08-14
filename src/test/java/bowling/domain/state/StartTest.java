package bowling.domain.state;

import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StartTest {

    @DisplayName("Start 생성이 가능하다")
    @Test
    void should_make_first_pitching() {
        //arrange, act, assert
        assertThat(Start.of()).isInstanceOf(Start.class);
    }

    @DisplayName("pins 10개이면 Strike가 반환된다")
    @Test
    void should_return_strike() {
        //arrange
        Start start = Start.of();
        Pins pins = Pins.of(10);

        //act, assert
        assertThat(start.hitPins(pins)).isInstanceOf(Strike.class);
    }

    @DisplayName("pins 10개이하이면 Progress가 반환된다")
    @Test
    void should_return_second() {
        //arrange
        Start start = Start.of();
        Pins pins = Pins.of(9);

        //act, assert
        assertThat(start.hitPins(pins)).isInstanceOf(Progress.class);
    }

    @DisplayName("hit Pins는 empty로 반환된다")
    @Test
    void should_return_hit_pins_empty() {
        //arrange
        Start start = Start.of();

        //act, assert
        assertThat(start.getHitPins()).isEmpty();
    }

    @DisplayName("프레임에서 상태값이 Start라면 bowler를 바꿔야하는 상태이다")
    @Test
    void should_is_start_true() {
        //arrange
        Start start = Start.of();

        //act, assert
        assertTrue(start.isStart());
    }
    
}