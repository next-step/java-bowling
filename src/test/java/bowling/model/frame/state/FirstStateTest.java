package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static bowling.model.Pins.*;
import static org.assertj.core.api.Assertions.assertThat;

class FirstStateTest {

    @DisplayName("핀이 " + MIN + "일 시 거터를 생성한다")
    @Test
    void of_whenPinsZero_thanGutter() {
        // given
        Pins pins = Pins.DOWN_ZERO;

        // when
        State result = FirstState.of(pins);

        // then
        assertThat(result).isInstanceOf(Gutter.class);
    }

    @DisplayName("모두 쓰러트렸을 시 스트라이크를 반환한다")
    @Test
    void of_whenPinsAll_thanStrike() {
        // given
        Pins pins = DOWN_ALL;

        // when
        State result = FirstState.of(pins);

        // then
        assertThat(result).isInstanceOf(Strike.class);
    }

    @DisplayName("일부 쓰러트렸을 시 히트를 반환한다")
    @Test
    void bowl_pinsMax_thanStrike() {
        // given
        Pins pins = Pins.valueOf(MAX - 1);

        // when
        State result = FirstState.of(pins);

        // then
        assertThat(result).isInstanceOf(Hit.class);
    }

    @DisplayName("쓰러트린 핀의 개수가 " + MAX + "미만일 시 현 프레임을 계속 진행할 수 있다")
    @ParameterizedTest
    @CsvSource({
            MIN + ", false",
            MAX - 1 + ", false",
            MAX + ", true",
    })
    void isFinished_false(int downPins, boolean expectedResult) {
        // given
        Pins firstPins = Pins.valueOf(downPins);

        // when
        State strike = FirstState.of(firstPins);

        // than
        assertThat(strike.isFinished()).isEqualTo(expectedResult);
    }
}