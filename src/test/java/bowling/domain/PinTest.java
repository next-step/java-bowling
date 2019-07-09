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
 * create date  : 2019-07-09 12:58
 */
public class PinTest {
    @DisplayName("스트라이크 인지 확인")
    @Test
    void 스트라이크_핀_생성() {
        Pin pin = Pin.of(10);
        assertThat(pin.isStrike()).isTrue();
    }
}
