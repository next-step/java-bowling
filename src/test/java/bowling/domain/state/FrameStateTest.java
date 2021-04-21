package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FrameStateTest {

    @DisplayName("State Ready 상태에서 투구 시 상태 확인 테스트")
    @Test
    void testCase2() {
        assertAll(
                () -> assertThat(new Ready().bowl(10).printResult()).isEqualTo("X"),
                () -> assertThat(new Ready().bowl(10)).isInstanceOf(Strike.class),
                () -> assertThat(new Ready().bowl(2).printResult()).isEqualTo("2|"),
                () -> assertThat(new Ready().bowl(2)).isInstanceOf(Hit.class)
        );
    }

    @DisplayName("State 정보 확인")
    @Test
    void testCase1() {
        assertAll(
                () -> assertThat(new Hit(1).printResult()).isEqualTo("1|"),
                () -> assertThat(new Miss(1, 8).printResult()).isEqualTo("1|8"),
                () -> assertThat(new Spare(1, 9).printResult()).isEqualTo("1|/"),
                () -> assertThat(new Strike().printResult()).isEqualTo("X")
        );
    }

    @DisplayName("State ")
    @Test
    void testCase3() {

    }
}
