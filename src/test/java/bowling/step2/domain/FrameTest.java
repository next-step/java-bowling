package bowling.step2.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {
    @Test
    void 첫판에_스트라이크를_쳤을_때() {
        Frame frame = new Frame("10");
        assertThat(frame.hasNext(1)).isFalse();
    }

    @Test
    void 첫판에_스트라이트가_아닐_때() {
        Frame frame = new Frame("9");
        assertThat(frame.hasNext(1)).isTrue();
    }

    @Test
    void 열번째판_스트라이크쳤을_때() {
        Frame frame = new Frame("10");
        assertThat(frame.hasBonus(10)).isTrue();
    }

    @Test
    void 열번째판_스페어쳤을_때() {
        Frame frame = new Frame("2");
        frame.add("8");
        assertThat(frame.hasBonus(10)).isTrue();
    }
}