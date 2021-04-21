package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("State 테스트")
class FrameStateTest {

    @DisplayName("Ready 상태에서 투구 시 상태 확인 테스트")
    @Test
    void testCase1() {
        assertAll(
                () -> assertThat(new Ready().bowl(10).printResult()).isEqualTo("X"),
                () -> assertThat(new Ready().bowl(10)).isInstanceOf(Strike.class),
                () -> assertThat(new Ready().bowl(2).printResult()).isEqualTo("2|"),
                () -> assertThat(new Ready().bowl(2)).isInstanceOf(Hit.class)
        );
    }


    @DisplayName("Hit 상태 2구 시 상태 테스트")
    @Test
    void testCase2() {
        assertAll(
                () -> assertThat(new Hit(0).bowl(10).printResult()).isEqualTo("-|/"),
                () -> assertThat(new Hit(0).bowl(10)).isInstanceOf(Spare.class),
                () -> assertThat(new Hit(2).bowl(7).printResult()).isEqualTo("2|7"),
                () -> assertThat(new Hit(2).bowl(7)).isInstanceOf(Miss.class),
                () -> assertThat(new Hit(2)).isInstanceOf(Hit.class)
        );
    }

    @DisplayName("State 정보 확인")
    @Test
    void testCase3() {
        assertAll(
                () -> assertThat(new Miss(1, 8).printResult()).isEqualTo("1|8"),
                () -> assertThat(new Miss(1, 8)).isInstanceOf(Miss.class),
                () -> assertThat(new Miss(0, 0).printResult()).isEqualTo("-|-"),
                () -> assertThat(new Miss(0, 0)).isInstanceOf(Miss.class),
                () -> assertThat(new Spare(1, 9).printResult()).isEqualTo("1|/"),
                () -> assertThat(new Spare(1, 9)).isInstanceOf(Spare.class),
                () -> assertThat(new Strike().printResult()).isEqualTo("X"),
                () -> assertThat(new Strike()).isInstanceOf(Strike.class)
        );
    }
}
