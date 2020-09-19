package bowling.model.frame;

import bowling.model.Result;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class NormalFrameTest {
    @Test
    public void addResult_Strike() {
        Frame frame = NormalFrame.start();
        frame.addResult(Result.MAX_PIN_COUNT);

        assertThat(frame.isEnded()).isTrue();
        assertThat(frame.hasStrike()).isTrue();
        assertThat(frame.hasSpare()).isFalse();

        assertThatIllegalStateException().isThrownBy(() -> frame.addResult(1));
    }

    @Test
    public void addResult_Spare() {
        Frame frame = NormalFrame.start();
        frame.addResult(5);
        frame.addResult(5);

        assertThat(frame.isEnded()).isTrue();
        assertThat(frame.hasStrike()).isFalse();
        assertThat(frame.hasSpare()).isTrue();

        assertThatIllegalStateException().isThrownBy(() -> frame.addResult(1));
    }

    @Test
    public void addResult_NotEnded() {
        Frame frame = NormalFrame.start();
        frame.addResult(5);

        assertThat(frame.isEnded()).isFalse();
        assertThat(frame.hasStrike()).isFalse();
        assertThat(frame.hasSpare()).isFalse();
    }

    @Test
    public void addResult_ShouldThrow_IllegalArgumentException() {
        Frame frame = NormalFrame.start();
        assertThatIllegalArgumentException().isThrownBy(() -> frame.addResult(-1));
        assertThatIllegalArgumentException().isThrownBy(() -> frame.addResult(Result.MAX_PIN_COUNT + 1));
    }
}
