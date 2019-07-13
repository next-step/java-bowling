package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-13 13:20
 */
public class PinTest {
    @DisplayName("핀을 넘어트린 개수 -> Pin 객체 생성")
    @Test
    void fallDown() {
        Pin pin = Pin.fallDown(3);
        assertThat(pin.fallCount()).isEqualTo(3);
    }
}
