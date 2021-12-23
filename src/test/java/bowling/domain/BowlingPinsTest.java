package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingPinsTest {

    @Test
    @DisplayName("볼링 핀 객체 생성")
    void create() {
        BowlingPins pins = new BowlingPins(10);
        assertThat(pins).isEqualTo(new BowlingPins(10));
    }

    @Test
    @DisplayName("볼링핀 쓰러뜨리고 남는 볼링개수 반환")
    void down() {
        BowlingPins pins = new BowlingPins(10);
        BowlingPins remains = pins.down(7);
        assertThat(remains).isEqualTo(new BowlingPins(3));
    }



}
