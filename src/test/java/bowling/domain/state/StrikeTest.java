package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import bowling.domain.score.Score;
import bowling.domain.state.progress.Ready;
import bowling.domain.state.progress.Running;
import bowling.domain.state.result.Strike;

public class StrikeTest {

    @Test
    void 생성_예외_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Strike.of(BowlingPin.of(3)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Strike.of(BowlingPin.of(9)));
    }

    @Test
    void 상태_생성_테스트() {
        // given
        State state = new Ready();
        State state2 = Strike.of(BowlingPin.of(10));
        // when & then
        assertThat(state.bowl(BowlingPin.of(10))).isInstanceOf(Strike.class);
        assertThat(state2).isInstanceOf(Strike.class);
    }

    @Test
    void 상태_종료_여부_테스트() {
        // given
        State state = new Ready().bowl(BowlingPin.of(10));
        State state2 = Strike.of(BowlingPin.of(10));
        // when & then
        assertThat(state.isDone()).isTrue();
        assertThat(state2.isDone()).isTrue();
    }


    @Test
    void 점수_반환_테스트() {
        // given
        State state = new Ready().bowl(BowlingPin.of(10));
        // when & then
        assertThat(state.score()).isEqualTo(Score.ofStrike());
    }

    @Test
    void 현재_투구_쓰러뜨린_핀_반환_테스트() {
        // given
        State state = new Ready().bowl(BowlingPin.of(10));
        // when & then
        assertThat(state.currentBowlingPin()).isEqualTo(10);
    }
}
