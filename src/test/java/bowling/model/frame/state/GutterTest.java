package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.Pins.MAX;
import static bowling.model.Pins.MIN;
import static org.assertj.core.api.Assertions.assertThat;

class GutterTest {

    @DisplayName(MIN + "이면 거터이다")
    @Test
    void getScore_isZero() {
        // when
        boolean result = Gutter.isMatch(Pins.DOWN_ZERO);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName(MAX + "개를 모두 쓰러트렸을 시 스페어를 반환한다")
    @Test
    void bowl_whenPinsTen_thenSpare() {
        // given
        Pins second = Pins.DOWN_ALL;

        // when
        State result = new Gutter().bowl(second);

        // then
        assertThat(result).isInstanceOf(Spare.class);
    }

    @DisplayName(MAX + "미만 일 시 미스를 반환한다")
    @Test
    void bowl_whenPinsLessThanMax_thenMiss() {
        // given
        Pins second = Pins.valueOf(MAX - 1);

        // when
        State result = new Gutter().bowl(second);

        // then
        assertThat(result).isInstanceOf(Miss.class);
    }

    @DisplayName("거터일 경우 프린트 확인한다")
    @Test
    void printResult_success() {
        // when
        String print = new Gutter().printResult();

        // then
        assertThat(print).isEqualTo("   -    ");
    }
}