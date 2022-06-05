package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    @Test
    void scoreGiven_addScore() {
        Frame frame = new Frame(1);
        frame.addScore(3);
        assertThat(frame.scores()).isEqualTo(List.of(3));
    }
}
