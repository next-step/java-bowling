package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import bowling.domain.state.progress.Running;
import bowling.domain.state.result.Miss;

public class MissTest {

    @Test
    void 생성_예외_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Miss.of(BowlingPin.of(10)));
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
        State state = Running.of(BowlingPin.of(5));
        // when & then
        assertThat(state.bowl(BowlingPin.of(3)).isDone()).isTrue();
    }
}
