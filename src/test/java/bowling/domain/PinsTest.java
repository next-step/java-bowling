package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PinsTest {

    @DisplayName("Pins 인스턴스 생성 테스트")
    @Test
    void 생성() {
        // given
        int hitCount = 10;

        // when
        Pins pins = Pins.valueOf(hitCount);

        // then
        assertThat(pins).isNotNull();
    }
}