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

}
