package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import bowling.domain.exception.CannotCalculateException;
import bowling.domain.state.progress.Ready;
import bowling.domain.state.progress.Running;
import bowling.domain.state.result.Strike;

public class ReadyTest {

    @Test
    void 진행중_상태_생성_테스트() {
        // given
        State state = new Ready();
        // when & then
        assertThat(state.bowl(BowlingPin.of(5))).isInstanceOf(Running.class);
    }

    @Test
    void 스트라이크_상태_생성_테스트() {
        // given
        State state = new Ready();
        // when & then
        assertThat(state.bowl(BowlingPin.of(10))).isInstanceOf(Strike.class);
    }

    @Test
    void 상태_종료_여부_테스트() {
        // given
        State state = new Ready();
        // when & then
        assertThat(state.isDone()).isFalse();
    }

    @Test
    void 점수_반환_테스트() {
        // given
        State state = new Ready();
        // when & then
        Assertions.assertThrows(CannotCalculateException.class, () -> state.score());
    }

    @Test
    void 현재_투구_쓰러뜨린_핀_반환_테스트() {
        // given
        State state = new Ready();
        // when & then
        Assertions.assertThrows(CannotCalculateException.class, () -> state.currentBowlingPin());
    }
}
