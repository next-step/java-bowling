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
    @DisplayName("NormalFrame 추가 기능")
    void addNormalFrame() {
        Pitch pitch1 = Pitch.from(10);
        NormalFrame normalFrame1 = NormalFrame.init();
        normalFrame1.add(pitch1);

        Pitch pitch2 = Pitch.from(8);
        Pitch pitch3 = Pitch.from(2);
        NormalFrame normalFrame2 = NormalFrame.init();
        normalFrame2.add(pitch2);
        normalFrame2.add(pitch3);

        Frames frames = Frames.init();
        frames.add(normalFrame1);
        frames.add(normalFrame2);

        assertThat(frames.getNormalFrameSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("NormalFrame에 FinalFrame 추가 기능")
    void addAllFrame() {
        Pitch pitch1 = Pitch.from(10);
        Frame normalFrame1 = NormalFrame.init();
        normalFrame1.add(pitch1);

        Pitch pitch2 = Pitch.from(8);
        Pitch pitch3 = Pitch.from(2);
        Frame normalFrame2 = NormalFrame.init();
        normalFrame2.add(pitch2);
        normalFrame2.add(pitch3);

        Pitch pitch4 = Pitch.from(10);
        Pitch pitch5 = Pitch.from(10);
        Pitch pitch6 = Pitch.from(10);
        Frame finalFrame = FinalFrame.init();
        finalFrame.add(pitch4);
        finalFrame.add(pitch5);
        finalFrame.add(pitch6);

        Frames frames = Frames.init();
        frames.add(normalFrame1);
        frames.add(normalFrame2);
        frames.add(finalFrame);

        assertThat(frames.getNormalFrameSize()).isEqualTo(3);
    }

}