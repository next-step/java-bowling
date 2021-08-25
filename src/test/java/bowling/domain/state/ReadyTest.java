package bowling.domain.state;

import bowling.domain.pins.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    @DisplayName("첫 투구에 10개를 쓰러트리면 스트라이크")
    @Test
    void strike() {
        assertThat(Ready.of().bowl(Pins.of(10))).isInstanceOf(Strike.class);
    }

    @DisplayName("첫 투구에 10개 이하를 쓰러트리면 FirstBowl")
    @Test
    void first_bowl() {
        assertThat(Ready.of().bowl(Pins.of(3))).isInstanceOf(FirstBowl.class);
    }
}