package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    @DisplayName("Frames 생성")
    void createNormalFrames() {
        assertThat(Frames.init()).isInstanceOf(Frames.class);
    }

    @Test
    @DisplayName("Frame 추가 기능")
    void addFrame() {
        Pitch pitch1 = Pitch.from(10);
        Frame frame1 = Frame.init();
        frame1.add(pitch1);

        Pitch pitch2 = Pitch.from(8);
        Pitch pitch3 = Pitch.from(2);
        Frame frame2 = Frame.init();
        frame2.add(pitch2);
        frame2.add(pitch3);

        Frames frames = Frames.init();
        frames.add(frame1);
        frames.add(frame2);

        assertThat(frames.getNormalFrameSize()).isEqualTo(2);
    }

}