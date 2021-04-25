package bowling.domain.frame;

import bowling.domain.pin.NormalPins;
import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameScoreTest {

    @Test
    @DisplayName("Pins를 입력받아 FrameScore를 생성한다.")
    void create() {
        // given
        final Pins pins = new NormalPins(new Pin(), new Pin(0));

        // when
        final FrameScore frameScore = new FrameScore(pins);

        // then
        assertThat(frameScore).isEqualTo(new FrameScore(pins));
    }
}
