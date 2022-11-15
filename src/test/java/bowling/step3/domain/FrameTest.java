package bowling.step3.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {
    @Test
    void 노멀프레임_스트라이크를_쳤을_때() {
        Frame frame = new NormalFrame();
        frame.add(10);
        assertThat(frame.isEndedFrame()).isTrue();
    }

    @Test
    void 노멀프레임_스트라이트가_아닐_때() {
        Frame frame = new NormalFrame();
        frame.add(2);
        assertThat(frame.isEndedFrame()).isFalse();
    }

    @Test
    void 파이널판_스트라이크쳤을_때() {
        Frame frame = new FinalFrame();
        frame.add(10);
        assertThat(frame.isEndedFrame()).isFalse();
    }

    @Test
    void 파이널판_스페어쳤을_때() {
        Frame frame = new FinalFrame();
        frame.add(2);
        frame.add(8);
        assertThat(frame.isEndedFrame()).isFalse();
    }
}