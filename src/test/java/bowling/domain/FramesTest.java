package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    @Test
    void addSizeTest() {
        Frames frames = new Frames();
        frames.addFirst(new NormalFrame(1));

        assertThat(frames.size()).isEqualTo(1);
    }

    @DisplayName("Next Frame 상황이 아니면 delete 후 add -> size 변화 없음")
    @Test
    void addTest() {
        Frames frames = new Frames();
        Frame frame = new NormalFrame(1);
        frames.addFirst(frame);

        frame.bowl(Pins.of(3));

        frames.add(frame);
        assertThat(frames.size()).isEqualTo(1);
    }

    @DisplayName("Next Frame 으로 넘어 갈 상황이면 add -> size + 1")
    @Test
    void addNextFrameTest() {
        Frames frames = new Frames();
        Frame frame = new NormalFrame(1);
        frames.addFirst(frame);

        frame.bowl(Pins.of(3));
        frame.bowl(Pins.of(3));

        Frame nextFrame = frame.next();

        frames.add(nextFrame);

        assertThat(frames.size()).isEqualTo(2);

    }
}
