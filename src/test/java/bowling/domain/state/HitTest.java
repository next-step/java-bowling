package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.state.exception.IllegalIndexOfExcpetion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @DisplayName("Spare 상태로 반환")
    @Test
    void SPARE_상태로_반환() {
        State updateState = state.update(Point.of(1));
        State updateStateSpare = updateState.update(Point.of(9));
        assertThat(updateStateSpare instanceof Spare).isTrue();
    }

    @DisplayName("Miss 상태로 반환")
    @Test
    void MISS_상태로_반환() {
        State updateState = state.update(Point.of(1));
        State updateStateSpare = updateState.update(Point.of(8));
        assertThat(updateStateSpare instanceof Miss).isTrue();
    }

    @DisplayName("점수 가져오기 예외처리")
    @Test
    void 점수_가져오기_예외처리() {
        assertThatExceptionOfType(IllegalIndexOfExcpetion.class).isThrownBy(() -> {
            state.update(Point.of(3)).getSecondBowl();
        }).withMessageContaining("현재 INDEX는 데이터가 없습니다.");
    }
}
