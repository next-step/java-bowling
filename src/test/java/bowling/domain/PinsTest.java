package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinsTest {

    @DisplayName("프레임 투구 확인 테스트")
    @Test
    void testCase1() {
        // given
        Pins first = Pins.first(1);
        Pins second = first.second(2);
        // when
        Pins expected = Pins.valueOf(1, 2);
        // then
        assertThat(second).isEqualTo(expected);
    }

    @Test
    void testCase2() {
        Pins first = Pins.first(10);

    }
}
