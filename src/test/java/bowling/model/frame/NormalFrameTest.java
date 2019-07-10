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
        Frame frame = NormalFrame.ofFirst();
        Frame resultFrame = frame.bowl(first);

        // then
        assertThat(resultFrame).isEqualTo(frame);
    }

    @Test
    void bowl_strikeFirstPinsTen_thenHasNextFrame() {
        // given
        Pins first = Pins.valueOf(MAX);

        // when
        NormalFrame frame = NormalFrame.ofFirst();
        Frame resultFrame = frame.bowl(first);

        // then
        assertThat(resultFrame).isNotEqualTo(frame);
    }

    @Test
    void bowl_pinsOneAndNine_thenHasNextFrame() {
        // given
        Pins first = Pins.valueOf(1);
        Pins second = Pins.valueOf(9);

        // when
        NormalFrame frame = NormalFrame.ofFirst();
        Frame resultFrame = frame.bowl(first).bowl(second);

        // then
        assertThat(resultFrame).isNotEqualTo(frame);
    }
}