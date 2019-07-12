package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.Pins.MAX;
import static org.assertj.core.api.Assertions.assertThat;

class HitTest {

    @DisplayName("첫번째 볼이 " + MAX + "미만으로 던졌을 시 힛을 생성한다")
    @Test
    void createState() {
        // given
        Pins pins = Pins.valueOf(1);

        // when
        State result = Hit.valueOf(pins);

        // then
        assertThat(result).isInstanceOf(Hit.class);
    }

    @DisplayName("결과 값의 출력값을 확인한다")
    @Test
    void printResult() {
        // given
        Pins pins = Pins.valueOf(1);

        // when
        String result = Hit.valueOf(pins).printResult();

        // then
        assertThat(result).isEqualTo("1");
    }
}