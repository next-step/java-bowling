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
 * create date  : 2019-07-19 12:06
 */
public class HitTest {
    private State state;

    @BeforeEach
    void 상태_초기화() {
        state = InitState.of();
    }

    @DisplayName("Hit 상태출력")
    @Test
    void HIT_상태_출력() {
        State hit = state.update(Point.of(1));
        assertThat(hit.printState()).isEqualTo("1");
    }

    @DisplayName("Hit 게임종료 상태")
    @Test
    void HIT_종료_상태() {
        State hit = state.update(Point.of(1));
        assertThat(hit.isOver()).isFalse();
    }
}
