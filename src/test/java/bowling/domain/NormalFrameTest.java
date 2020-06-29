package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@DisplayName("NormalFrame 로직 테스트")
class NormalFrameTest {

    @Test
    public void test_stlike() {
        Frame frame = new NormalFrame();
        frame.bowling(10);
        assertThat(frame.getResult()).isEqualTo(Arrays.asList(ResultType.STRIKE));
    }

    @Test
    public void test_spare() {
        Frame frame = new NormalFrame();
        frame.bowling(5);
        frame.bowling(5);
        assertThat(frame.getResult()).isEqualTo(Arrays.asList(ResultType.FIVE, ResultType.SPARE));
    }

    @Test
    public void test_miss() {
        Frame frame = new NormalFrame();
        frame.bowling(5);
        frame.bowling(3);
        assertThat(frame.getResult()).isEqualTo(Arrays.asList(ResultType.FIVE, ResultType.THREE));
    }

    @Test
    public void test_gutter() {
        Frame frame = new NormalFrame();
        frame.bowling(0);
        frame.bowling(0);
        assertThat(frame.getResult()).isEqualTo(Arrays.asList(ResultType.GUTTER, ResultType.GUTTER));
    }

    @Test
    public void test_핀카운트가_10개를_넘음() {
        Frame frame = new NormalFrame();
        assertThatThrownBy(() ->
                frame.bowling(11)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void test_스트라이크라면_프레임종료() {
        Frame frame = new NormalFrame();
        assertThat(frame.bowling(10)).isTrue();
    }

    @Test
    public void test_스페어라면_프레임종료() {
        Frame frame = new NormalFrame();
        frame.bowling(5);
        assertThat(frame.bowling(5)).isTrue();
    }

    @Test
    public void test_스페어처리해야되면_프레임종료안됨() {
        Frame frame = new NormalFrame();
        assertThat(frame.bowling(5)).isFalse();
    }

    @Test
    public void test_미스라면_프레임종료() {
        Frame frame = new NormalFrame();
        frame.bowling(5);
        assertThat(frame.bowling(3)).isTrue();
    }
}
