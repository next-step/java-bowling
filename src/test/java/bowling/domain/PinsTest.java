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
        Pins remain = pins.hit(hitCount);

        // then
        assertThat(remain).isNotNull();
    }

    @DisplayName("Pins 인스턴스가 가진 갯수가 동일한 경우 같은 인스턴스로 비교하는지 테스트")
    @Test
    void 비교_핀_갯수_기준() {
        // given
        int hitCount = 5;

        // when
        Pins pins = Pins.init();
        Pins expected = pins.hit(hitCount);
        Pins actual = pins.hit(hitCount);

        // then
        assertThat(actual).isEqualTo(expected);
    }

}