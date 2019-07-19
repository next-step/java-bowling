package bowling.domain.state;

import bowling.domain.Point;
import bowling.exception.IllegalBowlCountException;
import bowling.exception.IllegalIndexOfExcpetion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-19 12:50
 */
class StrikeTest {
    private State state;

    @BeforeEach
    void 상태_초기화() {
        state = InitState.of();
    }

    @DisplayName("스트라이크 상태출력")
    @Test
    void STRIKE_상태_출력() {
        State hit = state.update(Point.of(10), false);
        assertThat(hit.printState()).isEqualTo("X");
    }

    @DisplayName("스트라이크 게임종료 상태")
    @Test
    void STRIKE_종료_상태() {
        State hit = state.update(Point.of(10), false);
        assertThat(hit.isOver(false)).isTrue();
    }

    @DisplayName("두번째 투구 예외처리")
    @Test
    void 두번째_투구_예외처리() {
        assertThatExceptionOfType(IllegalBowlCountException.class).isThrownBy(() -> {
            state.update(Point.of(10), false).update(Point.of(1), false);
        }).withMessageContaining("프레임 종료되었습니다.");
    }

    @DisplayName("점수 가져오기 예외처리")
    @Test
    void 점수_가져오기_예외처리() {
        assertThatExceptionOfType(IllegalIndexOfExcpetion.class).isThrownBy(() -> {
            state.update(Point.of(10), false).getSecondBowl();
        }).withMessageContaining("현재 INDEX는 데이터가 없습니다.");
    }

    @DisplayName("더블 스트라이크")
    @Test
    void 두번째_투구_스트라이크() {
        assertThat(
                state.update(Point.of(10), true).update(Point.of(10), true) instanceof DoubleStrike
        ).isTrue();
    }

    @DisplayName("마지막 프레임 첫 스트라이크 후 투구 상태")
    @Test
    void 마지막_프레임_첫_스트라이크() {
        State next = state.update(Point.of(10), true);
        assertAll(
                () -> assertThat(next.update(Point.of(0), true) instanceof FinalState).isTrue(),
                () -> assertThat(next.update(Point.of(1), true) instanceof FinalState).isTrue(),
                () -> assertThat(next.update(Point.of(10), true) instanceof DoubleStrike).isTrue()
        );
    }
}