package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {


    @Test
    void create() {
        int frameNumber = 1;
        Frame frame = new Frame(frameNumber);

        assertThat(frame.number()).isEqualTo(frameNumber);
    }


    @Test
    void add_pin_counts() {
        int pinCount = 5;
        Frame frame = new Frame(1);
        frame.addPintCount(pinCount);
        List<Integer> pinCounts = frame.pinCounts();

        assertThat(pinCounts).containsExactly(pinCount);
    }
}
