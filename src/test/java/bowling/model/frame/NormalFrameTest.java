package bowling.model.frame;

import bowling.model.Pins;
import org.junit.jupiter.api.Test;

import static bowling.model.Pins.MAX;
import static bowling.model.Pins.MIN;
import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    @Test
    void bowl_hit_pinsZero() {
        // given
        Pins first = Pins.valueOf(MIN);

        // when
        NormalFrame frame = NormalFrame.ofFirst();
        frame.bowl(first);
        NormalFrame nextFrame = frame.nextFrame();

        // then
        assertThat(nextFrame).isEqualTo(frame);
    }

    @Test
    void bowl_strikeFirstPinsTen_thenHasNextFrame() {
        // given
        Pins first = Pins.valueOf(MAX);

        // when
        NormalFrame frame = NormalFrame.ofFirst();
        frame.bowl(first);
        NormalFrame nextFrame = frame.nextFrame();

        // then
        assertThat(nextFrame).isNotEqualTo(frame);
    }

    @Test
    void bowl_pinsOneAndNine_thenHasNextFrame() {
        // given
        Pins first = Pins.valueOf(1);
        Pins second = Pins.valueOf(9);

        // when
        NormalFrame frame = NormalFrame.ofFirst();
        frame.bowl(first);
        frame.bowl(second);
        NormalFrame nextFrame = frame.nextFrame();

        // then
        assertThat(nextFrame).isNotEqualTo(frame);
    }
}