package bowling.domain.state;

import bowling.domain.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-20 01:04
 */
public class DoubleStrikeTest {
    private State state;

    @BeforeEach
    void 상태_초기화() {
        state = InitState.of();
    }

    @DisplayName("DoubleStrike 상태출력")
    @ParameterizedTest
    @CsvSource({"10,10"})
    void DOUBLESTRIKE_상태_출력(int firstBowl, int secondBowl) {
        State first = state.update(Point.of(firstBowl), true);
        State second = first.update(Point.of(secondBowl), true);
        assertThat(second.printState()).isEqualTo("X|X");
    }

    @DisplayName("DoubleStrike 게임종료 상태")
    @ParameterizedTest
    @CsvSource({"10,10"})
    void DOUBLESTRIKE_종료_상태(int firstBowl, int secondBowl) {
        State first = state.update(Point.of(firstBowl), true);
        State second = first.update(Point.of(secondBowl), true);
        assertThat(second.isOver(true)).isFalse();
    }

    @DisplayName("세번째 투구 - 상태객체")
    @ParameterizedTest
    @CsvSource({"10,10"})
    void DOUBLESTRIKE_투구_상태객체(int firstBowl, int secondBowl) {
        State first = state.update(Point.of(firstBowl), true);
        State second = first.update(Point.of(secondBowl), true);
        assertAll(
                () -> assertThat(
                        second.update(Point.of(0), true) instanceof FinalState).isTrue(),
                () -> assertThat(
                        second.update(Point.of(1), true) instanceof FinalState).isTrue(),
                () -> assertThat(
                        second.update(Point.of(10), true) instanceof FinalState).isTrue()
        );
    }

    @DisplayName("점수계산")
    @ParameterizedTest
    @CsvSource({"10,10"})
    void 점수계산(int firstBowl, int secondBowl) {
        State first = state.update(Point.of(firstBowl), true);
        State second = first.update(Point.of(secondBowl), true);
        assertThat(second.stateScore().getScore()).isEqualTo(20);
    }
}
