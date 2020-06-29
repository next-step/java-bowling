package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Frame 로직 테스트")
class FrameTest {

    @Test
    public void test_stlike() {
        Frame frame = new Frame();
        frame.addScore(10);
        assertThat(frame.getResult()).isEqualTo(Arrays.asList(ResultType.STRIKE));
    }

    @Test
    public void test_spare() {
        Frame frame = new Frame();
        frame.addScore(5);
        frame.addScore(5);
        assertThat(frame.getResult()).isEqualTo(Arrays.asList(ResultType.FIVE, ResultType.SPARE));
    }

    @Test
    public void test_miss() {
        Frame frame = new Frame();
        frame.addScore(5);
        frame.addScore(3);
        assertThat(frame.getResult()).isEqualTo(Arrays.asList(ResultType.FIVE, ResultType.THREE));
    }

    @Test
    public void test_gutter() {
        Frame frame = new Frame();
        frame.addScore(0);
        frame.addScore(0);
        assertThat(frame.getResult()).isEqualTo(Arrays.asList(ResultType.GUTTER, ResultType.GUTTER));
    }

    @Test
    public void test_핀카운트가_10개를_넘음() {
        Frame frame = new Frame();
        assertThatThrownBy(() ->
                frame.addScore(11)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
