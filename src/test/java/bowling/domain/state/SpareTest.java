package bowling.domain.state;

import bowling.domain.Point;
import org.junit.jupiter.api.BeforeEach;
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
 * create date  : 2019-07-19 13:18
 */
class SpareTest {
    private State state;

    @BeforeEach
    void 상태_초기화() {
        state = InitState.of();
    }

    @DisplayName("Spare 상태출력")
    @ParameterizedTest
    @CsvSource({"1,9"})
    void SPARE_상태_출력(int firstBowl, int secondBowl) {
        State first = state.update(Point.of(firstBowl));
        State second = first.update(Point.of(secondBowl));
        assertThat(second.printState()).isEqualTo("1|/ |");
    }

    @DisplayName("Spare 게임종료 상태")
    @ParameterizedTest
    @CsvSource({"1,9"})
    void SPARE_종료_상태(int firstBowl, int secondBowl) {
        State first = state.update(Point.of(firstBowl));
        State second = first.update(Point.of(secondBowl));
        assertThat(second.isOver()).isTrue();
    }
}