package bowling.domain.state;

import bowling.domain.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
 * create date  : 2019-07-19 11:57
 */
public class InitStateTest {
    private State state;

    @BeforeEach
    void 상태_초기화() {
        state = InitState.of();
    }

    @DisplayName("초기 상태값 설정")
    @Test
    void 상태값_초기화_생성() {
        State state = InitState.of();
        assertAll(
                () -> assertThat(state.isOver()).isFalse(),
                () -> assertThat(state.printState()).isEqualTo("| ")
        );
    }

    @DisplayName("Hit 상태로 반환")
    @Test
    void HIT_상태로_반환() {
        State updateState = state.update(Point.of(1));
        assertThat(updateState instanceof Hit).isTrue();
    }

    @DisplayName("Gutter 상태로 반환")
    @Test
    void GUTTER_상태로_반환() {
        State updateState = state.update(Point.of(0));
        assertThat(updateState instanceof Gutter).isTrue();
    }
}
