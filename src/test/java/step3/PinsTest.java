package step3;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step3.exceptions.PinNumberExecption;

public class pinsTest {
    @DisplayName("볼링핀 4개를 쓰러뜨리면, 남은 볼링핀의 개수는 6개이어야 한다.")
    @Test
    void throwBowl1() {
        Pins pins = new Pins();
        pins.bowl(4);
        assertThat(pins).isEqualTo(new Pins(6));
    }

    @DisplayName("볼링핀은 10개 이상 쓰러뜨릴 수 없다.")
    @Test
    void throwBowl2() {
        Pins pins = new Pins();
        pins.bowl(4);
        assertThatThrownBy(() -> {
            pins.bowl(7);
        }).isInstanceOf(PinNumberExecption.class);
    }
}
