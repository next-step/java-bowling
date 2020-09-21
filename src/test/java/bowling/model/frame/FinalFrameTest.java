package bowling.model.frame;

import bowling.model.Result;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FinalFrameTest {
    @Test
    public void addResult_Strike() {
        Frame frame = new FinalFrame();
        frame.addResult(Result.MAX_PIN_COUNT);

        assertThat(frame.isEnded()).isFalse();
        assertThat(frame.hasStrike()).isTrue();
        assertThat(frame.hasSpare()).isFalse();

        frame.addResult(NormalFrame.MAX_FRAME_INDEX);
        frame.addResult(NormalFrame.MAX_FRAME_INDEX);
        assertThat(frame.isEnded()).isTrue();

        assertThatIllegalStateException().isThrownBy(() -> frame.addResult(1));
    }

    @Test
    public void addResult_Spare() {
        Frame frame = new FinalFrame();
        frame.addResult(5);
        frame.addResult(5);

        assertThat(frame.isEnded()).isFalse();
        assertThat(frame.hasStrike()).isFalse();
        assertThat(frame.hasSpare()).isTrue();

        frame.addResult(10);
        assertThat(frame.isEnded()).isTrue();

        assertThatIllegalStateException().isThrownBy(() -> frame.addResult(1));
    }

    @Test
    public void addResult_NotEnded() {
        Frame frame = new FinalFrame();
        frame.addResult(5);

        assertThat(frame.isEnded()).isFalse();
        assertThat(frame.hasStrike()).isFalse();
        assertThat(frame.hasSpare()).isFalse();
    }

    @Test
    public void addResult_AlreadyEnded() {
        Frame frame = new FinalFrame();
        frame.addResult(5);
        frame.addResult(2);

        assertThat(frame.isEnded()).isTrue();
        assertThat(frame.hasStrike()).isFalse();
        assertThat(frame.hasSpare()).isFalse();

        assertThatIllegalStateException().isThrownBy(() -> frame.addResult(1));
    }

    @Test
    public void addResult_ShouldThrow_WhenOverTen() {
        Frame frame = new FinalFrame();
        frame.addResult(5);
        assertThatIllegalArgumentException().isThrownBy(() -> frame.addResult(10));
    }

    @Test
    public void addResult_ShouldThrow_IllegalArgumentException() {
        Frame frame = new FinalFrame();
        assertThatIllegalArgumentException().isThrownBy(() -> frame.addResult(-1));
        assertThatIllegalArgumentException().isThrownBy(() -> frame.addResult(Result.MAX_PIN_COUNT + 1));
    }
}
