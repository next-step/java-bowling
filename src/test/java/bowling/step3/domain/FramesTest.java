package bowling.step3.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class FramesTest {

    @Test
    void 열개의_프레임_생성() {
        Frames frame = new Frames();
        assertSoftly(softly -> {
            assertThat(frame.frameMap().size()).isEqualTo(10);
            assertThat(frame.frameMap().get(9)).isInstanceOf(NormalFrame.class);
            assertThat(frame.frameMap().get(10)).isInstanceOf(FinalFrame.class);
        });
    }

    @Test
    void 노멀프레임_스트라이크를_쳤을_때() {
        Frames frames = new Frames();
        frames.add(2, 10);
        assertThat(frames.isEndedFrame(2)).isTrue();
    }

    @Test
    void 노멀프레임_스트라이트가_아닐_때() {
        Frames frames = new Frames();
        frames.add(2, 8);
        assertThat(frames.isEndedFrame(2)).isFalse();
    }


    @Test
    void 파이널판_스트라이크쳤을_때() {
        Frames frames = new Frames();
        frames.add(10, 10);
        assertThat(frames.isEndedFrame(10)).isTrue();
    }
}