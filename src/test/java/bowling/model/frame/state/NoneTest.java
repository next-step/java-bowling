package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NoneTest {

    @DisplayName("시작 시 기본 상태이며, 첫번째 공이 0일 시 거터를 반환한다")
    @Test
    void bowl_pinsZero_thanGutter() {
        // given
        Pins pins = Pins.DOWN_ZERO;

        // when
        State result = new None().bowl(pins);

        // then
        assertThat(result).isInstanceOf(Gutter.class);
    }

    @DisplayName("첫번째 공을 모두 쓰러트렸을 시 스트라이크를 반환한다")
    @Test
    void bowl_pinsMax_thanStrike() {
        // given
        Pins pins = Pins.DOWN_ALL;

        // when
        State result = new None().bowl(pins);

        // then
        assertThat(result).isInstanceOf(Strike.class);
    }

    @DisplayName("결과 값의 출력값을 확인한다")
    @Test
    void printResult() {
        // when
        String result = new None().printResult();

        // then
        assertThat(result).isEqualTo("        ");
    }
}