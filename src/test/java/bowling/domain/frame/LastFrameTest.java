package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LastFrameTest {

    @Test
    @DisplayName("MISS 상태일 때 2회 투구")
    void testTwoPitch() {
        LastFrame frame = new LastFrame();

        assertThat(frame.isEnd()).isFalse();
        frame.record(4);
        assertThat(frame.isEnd()).isFalse();
        frame.record(3);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("첫 Strike 상태일 때 3회 투구")
    void testStrike() {
        LastFrame frame = new LastFrame();

        assertThat(frame.isEnd()).isFalse();
        frame.record(10);
        assertThat(frame.isEnd()).isFalse();
        frame.record(3);
        assertThat(frame.isEnd()).isFalse();
        frame.record(4);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("첫 Spare 상태일 때 3회 투구")
    void testSpare() {
        LastFrame frame = new LastFrame();

        assertThat(frame.isEnd()).isFalse();
        frame.record(4);
        assertThat(frame.isEnd()).isFalse();
        frame.record(6);
        assertThat(frame.isEnd()).isFalse();
        frame.record(4);
        assertThat(frame.isEnd()).isTrue();
    }
}
