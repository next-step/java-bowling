package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    public void bowlWhenStrike() {
        Frame frame = new Frame(1);
        Frame nextFrame = frame.bowl(10);
        assertThat(nextFrame.getNumber()).isEqualTo(2);

        nextFrame = nextFrame.bowl(10);
        assertThat(nextFrame.getNumber()).isEqualTo(3);
    }
}
