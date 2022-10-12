package bowling.step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LeftOverPinsTest {
    public static final LeftOverPins LEFT_OVER_PINS = new LeftOverPins();
    
    @Test
    @DisplayName("볼링 핀을 쓰러트린 후, 0개인지 아닌지 확인")
    void knock_down_pins() {
        assertAll(() -> {
                    final LeftOverPins leftOverPins = LEFT_OVER_PINS.knockDown(6);
                    assertThat(leftOverPins.isExistLeftOverPins()).isTrue();
                },
                () -> {
                    final LeftOverPins leftOverPins = LEFT_OVER_PINS.knockDown(10);
                    assertThat(leftOverPins.isExistLeftOverPins()).isFalse();
                }
        );
    }
}