package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PinsTest {

    @DisplayName("Pins 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        Pins pins = Pins.init();

        assertThat(pins).isNotNull();
    }

    @DisplayName("Pins 인스턴스가 몇 개 맞았을 때, 남는 핀을 반환하는 테스트")
    @Test
    void 반환_남는_핀() {
        // given
        int hitCount = 5;

        // when
        Pins pins = Pins.init();
        Pins remain = Pins.hit(hitCount);

        assertThat(remain).isNotNull();
    }

}