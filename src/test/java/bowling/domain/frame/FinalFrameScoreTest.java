package bowling.domain.frame;

import bowling.domain.pin.FinalPins;
import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FinalFrameScoreTest {

    @Test
    @DisplayName("FinalPins를 입력받아 FinalFrameScore를 생성한다.")
    void create() {
        // given
        final FinalPins pins = new FinalPins(new Pin(), new Pin(0), new Pin());

        // when
        final FrameScore frameScore = new FinalFrameScore(pins);

        // then
        assertThat(frameScore).isEqualTo(new FinalFrameScore(pins));
    }

    @Test
    @DisplayName("해당 Frame이 종료되었는지 반환한다.")
    void isEnded() {
        // given
        final FrameScore endedFrame = new FinalFrameScore(new FinalPins(new Pin(), new Pin(0), new Pin()));
        final FrameScore notEndedFrame = new FinalFrameScore(new FinalPins(null, null, null));

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
        final FrameScore beforeFrameScore = new FinalFrameScore();
        final Pin strikePin = new Pin();
        final FrameScore expectedFrameScore = new FinalFrameScore(new FinalPins(new Pin()));

        // when
        final FrameScore afterFrameScore = beforeFrameScore.knockDownPin(strikePin);

        // then
        assertThat(afterFrameScore).isEqualTo(expectedFrameScore);
    }
}
