package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFramesTest {

    @Test
    @DisplayName("NormalFrames 생성")
    void createNormalFrames() {
        assertThat(NormalFrames.init()).isInstanceOf(NormalFrames.class);
    }

    @Test
    @DisplayName("NormalFrame 추가 기능")
    void addFrame() {
        Pitch pitch1 = Pitch.from(10);
        NormalFrame normalFrame1 = NormalFrame.init();
        normalFrame1.add(pitch1);

        Pitch pitch2 = Pitch.from(8);
        Pitch pitch3 = Pitch.from(2);
        NormalFrame normalFrame2 = NormalFrame.init();
        normalFrame2.add(pitch2);
        normalFrame2.add(pitch3);

        NormalFrames normalFrames = NormalFrames.init();
        normalFrames.add(normalFrame1);
        normalFrames.add(normalFrame2);

        assertThat(normalFrames.getNormalFrameSize()).isEqualTo(2);
    }

}