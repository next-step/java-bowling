package bowling.model.frame.state;

import bowling.model.DownPin;
import bowling.model.frame.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.DownPin.MAX;
import static bowling.model.DownPin.MIN;
import static org.assertj.core.api.Assertions.assertThat;

class MissTest {

    @DisplayName("첫번째, 두번째 볼의 합한 점수가 " + MAX + "미만일 시 미스를 생성하는데 성공한다")
    @Test
    void getScore_success() {
        // given
        DownPin first = DownPin.valueOf(MIN);
        DownPin second = DownPin.valueOf(MIN + 1);

        // when
        State result = Miss.valueOf(first, second);

        // then
        assertThat(result).isInstanceOf(Miss.class);
    }

    @DisplayName("미스의 결과 값을 확인하는데 성공한다")
    @Test
    void printResult_success() {
        // given
        DownPin first = DownPin.valueOf(MIN);
        DownPin second = DownPin.valueOf(MIN + 1);

        // when
        String print = Miss.valueOf(first, second).printResult();

        // then
        assertThat(print).isEqualTo("-|1");
    }
}