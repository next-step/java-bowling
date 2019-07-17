package bowling.model.frame.state;

import bowling.model.Pin;
import bowling.model.frame.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.Pin.MAX;
import static bowling.model.Pin.MIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class MissTest {

    @DisplayName("첫번째, 두번째 볼의 합한 점수가 " + MAX + "미만일 시 미스를 생성하는데 성공한다")
    @Test
    void getScore_success() {
        // given
        Pin first = Pin.DOWN_ZERO;
        Pin second = Pin.valueOf(MIN + 1);

        // when
        State result = Miss.valueOf(first, second);

        // then
        assertThat(result).isInstanceOf(Miss.class);
    }

    @DisplayName("스트라이크 일 경우 생성에 실패한다")
    @Test
    void createMiss_whenFirstSumSecond_fail() {
        // given
        Pin first = Pin.valueOf(MAX);
        Pin second = Pin.valueOf(MIN);

        // when
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Miss.valueOf(first, second));
    }

    @DisplayName("스페어 일 경우 생성에 실패한다")
    @Test
    void createMiss_whenFirstMinSumSecondMax_fail() {
        // given
        Pin first = Pin.valueOf(MIN);
        Pin second = Pin.valueOf(MAX);

        // when
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Miss.valueOf(first, second));
    }

    @DisplayName("미스의 결과 값을 확인하는데 성공한다")
    @Test
    void printResult_success() {
        // given
        Pin first = Pin.DOWN_ZERO;
        Pin second = Pin.valueOf(MIN + 1);

        // when
        String print = Miss.valueOf(first, second).printResult();

        // then
        assertThat(print).isEqualTo("0|1");
    }
}