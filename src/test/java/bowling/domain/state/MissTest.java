package bowling.domain.state;

import bowling.domain.Point;
import bowling.exception.IllegalBowlCountException;
import bowling.exception.IllegalIndexOfExcpetion;
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
 * create date  : 2019-07-19 13:31
 */
class MissTest {
    private State state;

    @BeforeEach
    void 상태_초기화() {
        state = InitState.of();
    }

    @DisplayName("Miss 상태출력")
    @ParameterizedTest
    @CsvSource({"0,9"})
    void MISS_상태_출력(int firstBowl, int secondBowl) {
        State first = state.update(Point.of(firstBowl), false);
        State second = first.update(Point.of(secondBowl), false);
        assertThat(second.printState()).isEqualTo("-|9");
    }

    @DisplayName("Miss 게임종료 상태")
    @ParameterizedTest
    @CsvSource({"1,8"})
    void Miss_종료_상태(int firstBowl, int secondBowl) {
        State first = state.update(Point.of(firstBowl), false);
        State second = first.update(Point.of(secondBowl), false);
        assertThat(second.isOver(false)).isTrue();
    }

    @DisplayName("세번째 투구 예외처리")
    @ParameterizedTest
    @CsvSource({"0,9"})
    void 세번쨰_투구_예외처리(int firstBowl, int secondBowl) {
        State first = state.update(Point.of(firstBowl), false);
        State second = first.update(Point.of(secondBowl), false);
        assertThatExceptionOfType(IllegalBowlCountException.class).isThrownBy(() -> {
            second.update(Point.of(1), false);
        }).withMessageContaining("프레임 종료되었습니다.");
    }
}