package bowling.domain.state;

import bowling.domain.Point;
import bowling.exception.IllegalIndexOfExcpetion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
 * create date  : 2019-07-19 12:43
 */
class GutterTest {
    private State state;

    @BeforeEach
    void 상태_초기화() {
        state = InitState.of();
    }

    @DisplayName("Gutter 상태출력")
    @Test
    void GUTTER_상태_출력() {
        assertThat(state.update(Point.of(0), false).printState()).isEqualTo("-");
    }

    @DisplayName("GUTTER 게임종료 상태")
    @Test
    void GUTTER_종료_상태() {
        State hit = state.update(Point.of(0), false);
        assertThat(hit.isOver(false)).isFalse();
    }

    @DisplayName("Spare 상태로 반환")
    @Test
    void SPARE_상태로_반환() {
        State updateState = state.update(Point.of(0), false);
        State updateStateSpare = updateState.update(Point.of(10), false);
        assertThat(updateStateSpare instanceof Spare).isTrue();
    }

    @DisplayName("Miss 상태로 반환")
    @Test
    void MISS_상태로_반환() {
        State updateState = state.update(Point.of(0), false);
        State updateStateSpare = updateState.update(Point.of(9), false);
        assertThat(updateStateSpare instanceof Miss).isTrue();
    }

    @DisplayName("DoubleGutter 상태로 반환")
    @Test
    void DoubleGutter_상태로_반환() {
        State updateState = state.update(Point.of(0), false);
        State updateStateSpare = updateState.update(Point.of(0), false);
        assertThat(updateStateSpare instanceof DoubleGutter).isTrue();
    }

    @DisplayName("점수 가져오기 예외처리")
    @Test
    void 점수_가져오기_예외처리() {
        assertThatExceptionOfType(IllegalIndexOfExcpetion.class).isThrownBy(() -> {
            state.update(Point.of(0), false).getSecondBowl();
        }).withMessageContaining("현재 INDEX는 데이터가 없습니다.");
    }

    @DisplayName("점수계산")
    @ParameterizedTest
    @CsvSource({"0"})
    void 점수계산(int firstBowl) {
        State first = state.update(Point.of(firstBowl), false);
        assertThat(first.stateScore().getScore()).isEqualTo(0);
    }
}