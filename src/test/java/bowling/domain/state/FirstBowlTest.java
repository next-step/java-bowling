package bowling.domain.state;

import bowling.domain.pins.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FirstBowlTest {

    @DisplayName("첫번째와 두번째 입력 pin 의 합이 10이면 스페어")
    @Test
    void spare() {
        State firstBowl = FirstBowl.of(Pins.of(3));

        assertThat(firstBowl.bowl(Pins.of(7))).isInstanceOf(Spare.class);
    }

    @DisplayName("첫번째와 두번째 입력 pin 의 합이 10보다 작으면 미스")
    @Test
    void miss() {
        State firstBowl = FirstBowl.of(Pins.of(3));

        assertThat(firstBowl.bowl(Pins.of(3))).isInstanceOf(Miss.class);
    }

    @DisplayName("첫번째와 두번째 입력 pin 의 합이 0 이면 거터")
    @Test
    void gutter() {
        State firstBowl = FirstBowl.of(Pins.of(0));

        assertThat(firstBowl.bowl(Pins.of(0))).isInstanceOf(Gutter.class);
    }

}