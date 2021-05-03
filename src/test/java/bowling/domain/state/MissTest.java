package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import bowling.domain.score.Score;
import bowling.domain.state.progress.Ready;
import bowling.domain.state.progress.Running;
import bowling.domain.state.result.Miss;

public class MissTest {

    @Test
    void 생성_예외_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Miss.of(BowlingPin.of(3), BowlingPin.of(7)));
    }

    @Test
    void 상태_생성_테스트() {
        // given
        State state = Running.of(BowlingPin.of(5));
        // when & then
        assertThat(state.bowl(BowlingPin.of(3))).isInstanceOf(Miss.class);
    }

    @Test
    void 상태_종료_여부_테스트() {
        // given
        State state = new Ready().bowl(BowlingPin.of(5)).bowl(BowlingPin.of(3));
        State state2 = Running.of(BowlingPin.of(5)).bowl(BowlingPin.of(5));
        State state3 = Miss.of(BowlingPin.of(5), BowlingPin.of(3));

        // when & then
        assertThat(state.isDone()).isTrue();
        assertThat(state2.isDone()).isTrue();
        assertThat(state3.isDone()).isTrue();
    }

    @Test
    void 점수_반환_테스트() {
        // given
        State state = Running.of(BowlingPin.of(5)).bowl(BowlingPin.of(3));
        // when & then
        assertThat(state.score()).isEqualTo(Score.of(8));
    }

    @Test
    void 현재_투구_쓰러뜨린_핀_반환_테스트() {
        // given
        State state = Running.of(BowlingPin.of(5)).bowl(BowlingPin.of(3));
        // when & then
        assertThat(state.currentBowlingPin()).isEqualTo(3);
    }
}
