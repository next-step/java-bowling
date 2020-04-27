package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    public void bowlWhenStrike() {
        Frame frame = new Frame();
        Frame nextFrame = frame.bowl(10);

        assertThat(nextFrame.getNumber()).isEqualTo(2);
    }
}
