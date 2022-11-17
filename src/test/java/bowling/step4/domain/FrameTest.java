package bowling.step4.domain;

import bowling.step4.domain.Frame.FinalFrame;
import bowling.step4.domain.Frame.Frame;
import bowling.step4.domain.Frame.NormalFrame;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class FrameTest {
    @Test
    void 노멀프레임_스트라이크를_쳤을_때() {
        Frame frame = new NormalFrame(pitches);
        frame.add(10);
        assertThat(frame.isEndedFrame()).isTrue();
    }

    @Test
    void 노멀프레임_스트라이트가_아닐_때() {
        Frame frame = new NormalFrame(pitches);
        frame.add(2);
        assertSoftly(softly -> {
            assertThat(frame.isEndedFrame()).isFalse();
            assertThat(frame.isFinalFrame()).isFalse();
        });
    }

    @Test
    void 파이널판_스트라이크쳤을_때() {
        Frame frame = new FinalFrame(pitches);
        frame.add(10);
        assertSoftly(softly -> {
            assertThat(frame.isEndedFrame()).isFalse();
            assertThat(frame.isFinalFrame()).isTrue();
        });
    }

    @Test
    void 파이널판_스페어쳤을_때() {
        Frame frame = new FinalFrame(pitches);
        frame.add(2);
        frame.add(8);
        assertSoftly(softly -> {
            assertThat(frame.isEndedFrame()).isFalse();
            assertThat(frame.firstPitch()).isEqualTo(2);
        });
    }

    @Test
    void 점수_계산() {
        Frame frame = new FinalFrame(pitches);
        frame.add(2);
        frame.add(8);
        frame.addPoint(10,0);
        assertThat(frame.score().value()).isEqualTo(20);

    }
}
