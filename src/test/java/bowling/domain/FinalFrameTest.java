package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalFrameTest {
    @Test
    void create() {
        FinalFrame finalFrame = new FinalFrame();

        assertThat(finalFrame).isEqualTo(new FinalFrame());
    }

    @Test
    void pitch() {
        FinalFrame frame = new FinalFrame();
        frame.pitch(1);
        frame.pitch(8);

        assertThat(frame.getPins()).containsExactly(new Pin(1), new Pin(8));
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 9, 1", "10, 10, 10", "10, 1, 2"})
    void pitch(int first, int second, int third) {
        FinalFrame frame = new FinalFrame();
        frame.pitch(first);
        frame.pitch(second);
        frame.pitch(third);

        assertThat(frame.getPins()).containsExactly(new Pin(first), new Pin(second), new Pin(third));
    }

    @Test
    void pitch_noBonus() {
        FinalFrame frame = new FinalFrame();
        frame.pitch(1);
        frame.pitch(8);

        assertThatThrownBy(() -> frame.pitch(10)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void pitch_overCount() {
        FinalFrame frame = new FinalFrame();
        frame.pitch(10);
        frame.pitch(10);
        frame.pitch(10);

        assertThatThrownBy(() -> frame.pitch(10)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void pitch_overPin() {
        FinalFrame frame = new FinalFrame();
        frame.pitch(9);

        assertThatThrownBy(() -> frame.pitch(10))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
