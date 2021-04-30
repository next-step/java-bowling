package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
    void 출력_테스트() {
        // given
        State state = new Ready();
        // when & then
        assertThat(state.toSymbol()).isEqualTo("");
    }

    @Test
    void 모든핀_제거_여부_테스트() {
        // given
        State state = new Ready();
        // when & then
        assertThat(state.isClear()).isFalse();
    }
}
