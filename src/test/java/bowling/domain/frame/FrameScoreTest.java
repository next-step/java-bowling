package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameScoreTest {

    @Test
    @DisplayName("핀과 프레임의 상태를 가지고 생성한다.")
    void create() {
        // given
        final Pin pin = new Pin();
        final FrameStatus frameStatus = FrameStatus.NONE;

        // when
        final FrameScore frameScore = new FrameScore(pin, frameStatus);

        // then
        assertThat(frameScore).isEqualTo(new FrameScore(pin, frameStatus));
    }
}
