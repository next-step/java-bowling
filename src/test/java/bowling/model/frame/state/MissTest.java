package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.Pins.MAX;
import static bowling.model.Pins.MIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class MissTest {

    @DisplayName("첫번째, 두번째 볼의 합한 점수가 " + MAX + "미만일 시 미스를 생성하는데 성공한다")
    @Test
    void getScore_success() {
        // given
        Pins first = Pins.DOWN_ZERO;
        Pins second = Pins.valueOf(MIN + 1);

        // when
        State result = Miss.valueOf(first, second);

        // then
        assertThat(result).isInstanceOf(Miss.class);
    }

    @DisplayName("볼의 합이 " + MAX + "이상일 경우 생성에 실패한다")
    @Test
    void createMiss_whenFirstSumSecond_fail() {
        // given
        Pins first = Pins.valueOf(MIN);
        Pins second = Pins.valueOf(MAX);

        // when
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Miss.valueOf(first, second));
    }

    @DisplayName("미스의 결과 갑을 화인하는데 성공한다")
    @Test
    void printResult_success() {
        // given
        Pins first = Pins.DOWN_ZERO;
        Pins second = Pins.valueOf(MIN + 1);

        // when
        String print = Miss.valueOf(first, second).printResult();

        // then
        assertThat(print).isEqualTo("  0|1   ");
    }
}