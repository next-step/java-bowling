package bowling.step2.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class FrameTest {
    @Test
    void 노멀프레임_스트라이크를_쳤을_때() {
        Frame frame = new NormalFrame();
        frame.add("10");
        assertSoftly(softly -> {
            assertThat(frame.isContainingStrike()).isTrue();
            assertThat(frame.isSpare()).isFalse();
            assertThat(frame.isEndedOneFrame()).isTrue();
            assertThat(frame.isFinalFrame()).isFalse();
        });
    }

    @Test
    void 노멀프레임_스트라이트가_아닐_때() {
        Frame frame = new NormalFrame();
        frame.add("2");
        assertSoftly(softly -> {
            assertThat(frame.isContainingStrike()).isFalse();
            assertThat(frame.isSpare()).isFalse();
            assertThat(frame.isEndedOneFrame()).isFalse() ;
            assertThat(frame.isFinalFrame()).isFalse();
        });
    }

    @Test
    void 파이널판_스트라이크쳤을_때() {
        Frame frame = new FinalFrame();
        frame.add("10");
        assertSoftly(softly -> {
            assertThat(frame.isContainingStrike()).isTrue();
            assertThat(frame.hasBonusCondition()).isTrue();
            assertThat(frame.isSpare()).isFalse();
            assertThat(frame.isEndedOneFrame()).isFalse();
            assertThat(frame.isFinalFrame()).isTrue();
        });
    }

    @Test
    void 파이널판_스페어쳤을_때() {
        Frame frame1 = new FinalFrame();
        frame1.add("10");
        frame1.add("2");
        frame1.add("8");
        assertSoftly(softly -> {
            assertThat(frame1.isContainingStrike()).isTrue();
            assertThat(frame1.hasBonusCondition()).isTrue();
            assertThat(frame1.isSpare()).isTrue();
            assertThat(frame1.isEndedOneFrame()).isTrue();
            assertThat(frame1.isFinalFrame()).isTrue();
        });

        Frame frame2 = new FinalFrame();
        frame2.add("2");
        frame2.add("8");
        assertSoftly(softly -> {
            assertThat(frame2.isContainingStrike()).isFalse();
            assertThat(frame2.hasBonusCondition()).isTrue();
            assertThat(frame2.isSpare()).isTrue();
            assertThat(frame2.isEndedOneFrame()).isFalse();
            assertThat(frame2.isFinalFrame()).isTrue();
        });
    }
}