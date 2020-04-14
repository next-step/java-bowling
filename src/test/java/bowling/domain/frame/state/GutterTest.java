package bowling.domain.frame.state;

import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GutterTest {
    @DisplayName("첫번째, 두번째 투구 다 거터일경우 -|-를 출력한다.")
    @Test
    void gutter() {
        String expect = "-";
        State gutter = new Gutter(Pins.GUTTER_PINS, Pins.GUTTER_PINS);

        assertThat(gutter.toResult()).isEqualTo(expect);
    }

    @DisplayName("첫번째만 거터일경우 -|? 를 출력한다.")
    @Test
    void leftGutter() {
        String expect = "-|5";
        State gutter = new Gutter(Pins.GUTTER_PINS, Pins.of().knockOver(new BowlCount(5)));

        assertThat(gutter.toResult()).isEqualTo(expect);
    }

    @DisplayName("두번째만 거터일경우 ?|- 를 출력한다.")
    @Test
    void rightGutter() {
        String expect = "5|-";
        State gutter = new Gutter(Pins.of().knockOver(new BowlCount(5)), Pins.GUTTER_PINS);

        assertThat(gutter.toResult()).isEqualTo(expect);
    }
}