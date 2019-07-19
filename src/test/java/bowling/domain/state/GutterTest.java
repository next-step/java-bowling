package bowling.domain.state;

import bowling.domain.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
        State hit = state.update(Point.of(0));
        assertThat(hit.printState()).isEqualTo("-");
    }

    @DisplayName("GUTTER 게임종료 상태")
    @Test
    void GUTTER_종료_상태() {
        State hit = state.update(Point.of(0));
        assertThat(hit.isOver()).isFalse();
    }

    @DisplayName("Spare 상태로 반환")
    @Test
    void SPARE_상태로_반환() {
        State updateState = state.update(Point.of(0));
        State updateStateSpare = updateState.update(Point.of(10));
        assertThat(updateStateSpare instanceof Spare).isTrue();
    }

    @DisplayName("Miss 상태로 반환")
    @Test
    void MISS_상태로_반환() {
        State updateState = state.update(Point.of(0));
        State updateStateSpare = updateState.update(Point.of(9));
        assertThat(updateStateSpare instanceof Miss).isTrue();
    }

    @DisplayName("DoubleGutter 상태로 반환")
    @Test
    void DoubleGutter_상태로_반환() {
        State updateState = state.update(Point.of(0));
        State updateStateSpare = updateState.update(Point.of(0));
        assertThat(updateStateSpare instanceof DoubleGutter).isTrue();
    }
}