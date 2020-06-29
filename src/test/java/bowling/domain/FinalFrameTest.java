package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("FinalFrame 로직 테스트")
class FinalFrameTest {

    @Test
    public void 스트라이크_후_스트라이크() {
        Frame frame = new FinalFrame();
        frame.bowling(10);
        frame.bowling(10);
        assertThat(frame.getResult()).isEqualTo(Arrays.asList(ResultType.STRIKE, ResultType.STRIKE));
    }

    @Test
    public void 스페어_후_스트라이크() {
        Frame frame = new FinalFrame();
        frame.bowling(5);
        frame.bowling(5);
        frame.bowling(10);
        assertThat(frame.getResult()).isEqualTo(Arrays.asList(ResultType.FIVE, ResultType.SPARE, ResultType.STRIKE));
    }
    
    @Test
    public void 스페어_후_미스() {
        Frame frame = new FinalFrame();
        frame.bowling(5);
        frame.bowling(5);
        frame.bowling(5);
        assertThat(frame.getResult()).isEqualTo(Arrays.asList(ResultType.FIVE, ResultType.SPARE, ResultType.FIVE));
    }

    @Test
    public void 미스() {
        Frame frame = new FinalFrame();
        frame.bowling(5);
        frame.bowling(3);
        assertThat(frame.getResult()).isEqualTo(Arrays.asList(ResultType.FIVE, ResultType.THREE));
    }

    @Test
    public void 거터() {
        Frame frame = new FinalFrame();
        frame.bowling(0);
        frame.bowling(0);
        assertThat(frame.getResult()).isEqualTo(Arrays.asList(ResultType.GUTTER, ResultType.GUTTER));
    }

    @Test
    public void 핀카운트가_10개를_넘음() {
        Frame frame = new FinalFrame();
        assertThatThrownBy(() ->
                frame.bowling(11)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 프레임진행_중에는_종료안됨() {
        Frame frame = new FinalFrame();
        assertThat(frame.bowling(5)).isFalse();
    }

    @Test
    public void 스트라이크라도_프레임종료안됨() {
        Frame frame = new FinalFrame();
        assertThat(frame.bowling(10)).isFalse();
    }

    @Test
    public void 스페어라도_프레임종료안됨() {
        Frame frame = new FinalFrame();
        frame.bowling(5);
        assertThat(frame.bowling(5)).isFalse();
    }

    @Test
    public void 스트라이크라면_한번_더_투구하고_프레임종료() {
        Frame frame = new FinalFrame();
        frame.bowling(10);
        assertThat(frame.bowling(10)).isTrue();
    }

    @Test
    public void 스페어라면_한번_더_투구하고_프레임종료() {
        Frame frame = new FinalFrame();
        frame.bowling(5);
        frame.bowling(5);
        assertThat(frame.bowling(5)).isTrue();
    }
}
