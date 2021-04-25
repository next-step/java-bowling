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

    @Test
    @DisplayName("기본 생성자로 생성시 Pin은 10개, Frame의 상태는 결정되지 않은 상태다.")
    void defaultConstructor() {
        // given
        final int defaultPinCount = 10;
        final FrameStatus defaultFrameStatus = FrameStatus.NONE;

        // when
        final FrameScore frameScore = new FrameScore();

        // then
        assertThat(frameScore).isEqualTo(new FrameScore(new Pin(defaultPinCount), defaultFrameStatus));
    }
}
