package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PinsTest {
    @DisplayName("생성 테스트")
    @Test
    void create(){
        Pins pins = Pins.of(4);

        assertThat(pins).isEqualTo(Pins.of(4));
    }

    @DisplayName("get 테스트")
    @Test
    void getFallenPins(){
        Pins pins = Pins.of(4);

        assertThat(pins.getFallenPins()).isEqualTo(4);
    }

    @DisplayName("10개니? 메시지 테스트")
    @Test
    void isStrike(){
        Pins pins = Pins.of(10);

        assertThat(pins.isStrike()).isTrue();
    }
}
