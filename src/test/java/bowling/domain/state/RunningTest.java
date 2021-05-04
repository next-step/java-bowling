package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import bowling.domain.exception.CannotCalculateException;
import bowling.domain.state.progress.Ready;
import bowling.domain.state.progress.Running;
import bowling.domain.state.result.Miss;
import bowling.domain.state.result.Spare;

public class RunningTest {

    @Test
    void 생성_예외_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Running.of(BowlingPin.of(10)));
    }

    @Test
    void 미스_상태_생성_테스트() {
        // given
        State state = new Ready().bowl(BowlingPin.of(5));
        State state2 = Running.of(BowlingPin.of(5));
        // when & then
        assertThat(state.bowl(BowlingPin.of(3))).isInstanceOf(Miss.class);
        assertThat(state2.bowl(BowlingPin.of(3))).isInstanceOf(Miss.class);
    }

    @Test
    void 스페어_상태_생성_테스트() {
        // given
        State state = new Ready().bowl(BowlingPin.of(5));
        State state2 = Running.of(BowlingPin.of(5));
        // when & then
        assertThat(state.bowl(BowlingPin.of(5))).isInstanceOf(Spare.class);
        assertThat(state2.bowl(BowlingPin.of(5))).isInstanceOf(Spare.class);
    }

    @Test
    void 상태_종료_여부_테스트() {
        // given
        State state = new Ready().bowl(BowlingPin.of(5));
        State state2 = Running.of(BowlingPin.of(5));
        // when & then
        assertThat(state.isDone()).isFalse();
        assertThat(state2.isDone()).isFalse();
    }

    @Test
    void 점수_반환_테스트() {
        // given
        State state = new Ready().bowl(BowlingPin.of(5));
        // when & then
        Assertions.assertThrows(CannotCalculateException.class, () -> state.score());
    }

    @Test
    void 현재_투구_쓰러뜨린_핀_반환_테스트() {
        // given
        State state = new Ready().bowl(BowlingPin.of(5));
        // when & then
        assertThat(state.currentBowlingPin()).isEqualTo(5);
    }

    @Test
    void 모든핀_제거_여부_테스트() {
        // given
        State state = new Ready().bowl(BowlingPin.of(5));
        State state2 = Running.of(BowlingPin.of(5));
        // when & then
        assertThat(state.isClear()).isFalse();
        assertThat(state2.isClear()).isFalse();
    }
}
