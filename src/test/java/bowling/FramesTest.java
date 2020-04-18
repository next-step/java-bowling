package bowling;

import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = new Frames();

        frames.play(10);    // Strike - add Frame
        frames.play(5);     // Playing
        frames.play(5);     // Spare - add Frame
        frames.play(0);     // Playing
        frames.play(3);     // Miss - add Frame
        frames.play(5);     // Playing
    }

    @Test
    @DisplayName("Frames 생성시에는 첫번째의 NormalFrame 인스턴스를 생성하여 갖는다.")
    void initFrames() {
        assertThat(new Frames().getValue().get(0)).isInstanceOf(NormalFrame.class);
    }

    @Test
    @DisplayName("play시, 한 프레임이 끝나는 play에서 다음 Frame 인스턴스를 생성해 추가한다.")
    void playFrames() {
        assertThat(frames.getValue().size()).isEqualTo(4);
    }

    @Test
    @DisplayName("가장 나중에 추가된 Frame이 현재 Frame이다.")
    void getCurrentFrame() {
        assertThat(frames.getCurrentFrame()).isEqualTo(frames.getValue().get(3));
    }

    @Test
    @DisplayName("가장 나중에 추가된 Frame이 현재 FrameNumber이다.")
    void getCurrentFrameNumber() {
        assertThat(frames.getCurrentFrameNumber()).isEqualTo(4);
    }
}
