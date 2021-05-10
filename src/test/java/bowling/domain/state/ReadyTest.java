package bowling.domain.state;

import bowling.domain.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyTest {
    public static final Ready READY = Ready.of();

    @Test
    void 준비생성() {
        assertThat(READY.isSpare()).isFalse();
        assertThat(READY.isSpare()).isFalse();
        assertThat(READY.hasNext()).isTrue();
        assertThat(READY.canAccumulate()).isFalse();
        assertThat(READY.eval()).isEqualTo(Score.of());
    }
}
