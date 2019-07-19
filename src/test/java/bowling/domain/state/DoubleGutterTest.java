package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.state.exception.IllegalBowlCountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-19 13:41
 */
public class DoubleGutterTest {
    private State state;

    @BeforeEach
    void 상태_초기화() {
        state = InitState.of();
    }

    @DisplayName("DoubleGutter 상태출력")
    @ParameterizedTest
    @CsvSource({"0,0"})
    void DOUBLEGUTTER_상태_출력(int firstBowl, int secondBowl) {
        State first = state.update(Point.of(firstBowl));
        State second = first.update(Point.of(secondBowl));
        assertThat(second.printState()).isEqualTo("-|- |");
    }

    @DisplayName("DoubleGutter 게임종료 상태")
    @ParameterizedTest
    @CsvSource({"0,0"})
    void DOUBLEGUTTER_종료_상태(int firstBowl, int secondBowl) {
        State first = state.update(Point.of(firstBowl));
        State second = first.update(Point.of(secondBowl));
        assertThat(second.isOver()).isTrue();
    }

    @DisplayName("세번째 투구 예외처리")
    @ParameterizedTest
    @CsvSource({"0,0"})
    void 세번쨰_투구_예외처리(int firstBowl, int secondBowl) {
        State first = state.update(Point.of(firstBowl));
        State second = first.update(Point.of(secondBowl));
        assertThatExceptionOfType(IllegalBowlCountException.class).isThrownBy(() -> {
           second.update(Point.of(1));
        }).withMessageContaining("프레임 종료되었습니다.");
    }
}
