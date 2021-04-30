package bowling.domain.frame;

import bowling.domain.TestFixture;
import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FinalFrameScoreTest {

    @Test
    @DisplayName("Pins를 입력받아 FinalFrameScore를 생성한다.")
    void create() {
        // given
        final Pins pins = Pins.of(TestFixture.STRIKE_PIN, new Pin(0), TestFixture.STRIKE_PIN);

        // when
        final FrameScore frameScore = new FinalFrameScore(pins);

        // then
        assertThat(frameScore).isEqualTo(new FinalFrameScore(pins));
    }

    @Test
    @DisplayName("해당 Frame이 종료되었는지 반환한다.")
    void isEnded() {
        // given
        final FrameScore endedFrame = new FinalFrameScore(Pins.of(TestFixture.STRIKE_PIN, new Pin(0), TestFixture.STRIKE_PIN));
        final FrameScore notEndedFrame = new FinalFrameScore(Pins.of(null, null, null));

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
        final FrameScore expectedFrameScore = new FinalFrameScore(Pins.of(TestFixture.STRIKE_PIN));

        // when
        final FrameScore afterFrameScore = beforeFrameScore.knockDownPin(TestFixture.STRIKE_PIN);

        // then
        assertThat(afterFrameScore).isEqualTo(expectedFrameScore);
    }
}
