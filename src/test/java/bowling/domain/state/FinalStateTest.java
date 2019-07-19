package bowling.domain.state;

import bowling.domain.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-19 16:42
 */
public class FinalStateTest {
    @DisplayName("마지막 프레임 투구 상태 확인 - 세번 투구한 경우")
    @ParameterizedTest
    @CsvSource({
            "10, 10, 10, X|X|X",
            "10, 10, 1, X|X|1",
            "10, 1, 9, X|1|/",
            "10, 5, 5, X|5|/",
            "10, 0, 10, X|-|/",
            "0, 10, 10, -|/|X",
            "0, 10, 0, -|/|-",
            "5, 5, 10, 5|/|X",
            "5, 5, 1, 5|/|1"
    })
    void 마지막_프레임_투구_상태출력_세번_투구(int first, int second, int third, String display) {
        FinalState state = new FinalState();

        state.update(Point.of(first));
        state.update(Point.of(second));
        state.update(Point.of(third));
        assertThat(state.printState()).isEqualTo(display);
    }

    @DisplayName("마지막 프레임 투구 상태 확인 - 두번 투구한 경우")
    @ParameterizedTest
    @CsvSource({
            "9, 0, 9|-",
            "1, 8, 1|8",
            "0, 9, -|9",
            "5, 4, 5|4"
    })
    void 마지막_프레임_투구_상태출력_두번_투구(int first, int second, String display) {
        FinalState state = new FinalState();

        state.update(Point.of(first));
        state.update(Point.of(second));
        assertThat(state.printState()).isEqualTo(display);
    }
}
