package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    @DisplayName("스트라이크가 나오면 다음 프레임으로 넘어간다")
    public void bowlNextFrameWhenStrike() {
        Frame frame = new Frame(1);
        Frame nextFrame = frame.bowl(10);
        assertThat(nextFrame.getNumber()).isEqualTo(2);

        nextFrame = nextFrame.bowl(10);
        assertThat(nextFrame.getNumber()).isEqualTo(3);
    }

    @Test
    @DisplayName("스페어 처리 후 다음 프레임으로 넘어간다")
    public void bowlNextFrameWhenSpare() {
        Frame frame = new Frame(1);
        Frame nextFrame = frame.bowl(9);
        assertThat(nextFrame.getNumber()).isEqualTo(1);

        nextFrame = frame.bowl(1);
        assertThat(nextFrame.getNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("미스가 나도 2번을 던졌다면 다음 프레임으로 넘어간다")
    public void bowlNextFrameWhenMiss() {
        Frame frame = new Frame(1);
        Frame nextFrame = frame.bowl(7);
        assertThat(nextFrame.getNumber()).isEqualTo(1);

        nextFrame = frame.bowl(1);
        assertThat(nextFrame.getNumber()).isEqualTo(2);
    }
}
