package bowling.domain.frame;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @DisplayName("현재 프레임과 동일한 프레임은 Frames 에 추가하지 않음")
    @Test
    public void doNotAddFrame() {
        Frames frames = Frames.newInstance();

        Frame frame = NormalFrame.ofFirst();
        frame.addFrame(frames);
        frame.addFrame(frames);
        frame.addFrame(frames);

        assertThat(frames.getFrameNumber())
                .isEqualTo(frame.getNo());
    }

    @DisplayName("현재 프레임과 동일하지 않은 프레임은 Frames 에 추가")
    @Test
    public void addFrameNotSameFrame() {
        Frames frames = Frames.newInstance();

        Frame frame = NormalFrame.newInstance(FrameNumber.of(2));
        frame.addFrame(frames);

        assertThat(frames.getFrameNumber())
                .isEqualTo(frame.getNo());
    }
}
