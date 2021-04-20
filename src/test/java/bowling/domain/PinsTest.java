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

    @DisplayName("1구 스트라이크 점수 확인")
    @Test
    void testCase2() {
        // given
        Pins first = Pins.first(10);
        // when
        int totalScore = first.totalScore();
        // then
        assertThat(totalScore).isEqualTo(10);
    }

}
