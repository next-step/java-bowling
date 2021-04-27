package bowling.domain.frame;

import bowling.domain.pin.NormalPins;
import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class NormalFrameScoreTest {

    @Test
    @DisplayName("Pins를 입력받아 FrameScore를 생성한다.")
    void create() {
        // given
        final Pins pins = new NormalPins(new Pin(), new Pin(0));

        // when
        final FrameScore frameScore = new NormalFrameScore(pins);

        // then
        assertThat(frameScore).isEqualTo(new NormalFrameScore(pins));
    }

    @Test
    @DisplayName("해당 Frame이 종료되었는지 반환한다.")
    void isEnded() {
        // given
        final FrameScore endedFrame = new NormalFrameScore(new NormalPins(new Pin(), new Pin(0)));
        final FrameScore notEndedFrame = new NormalFrameScore();

        // when
        // then
        assertAll(
                () -> assertThat(endedFrame.isEnded()).isTrue(),
                () -> assertThat(notEndedFrame.isEnded()).isFalse()
        );
    }

    @Test
    @DisplayName("Pin을 쓰러뜨리고, 새로운 FrameScore를 반환한다.")
    void knockDownPin() {
        // given
        final FrameScore beforeFrameScore = new NormalFrameScore();
        final Pin strikePin = new Pin();
        final FrameScore expectedFrameScore = new NormalFrameScore(new NormalPins(new Pin()));

        // when
        final FrameScore afterFrameScore = beforeFrameScore.knockDownPin(strikePin);

        // then
        assertThat(afterFrameScore).isEqualTo(expectedFrameScore);
    }
}
