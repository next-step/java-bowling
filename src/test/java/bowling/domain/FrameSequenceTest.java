package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FrameSequenceTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    public void create(int frame) {
        assertThat(FrameSequence.of(frame)).isInstanceOf(FrameSequence.class);
        assertThat(FrameSequence.of(frame)).isEqualTo(FrameSequence.of(frame));
        assertThat(FrameSequence.of(frame).toInt()).isEqualTo(frame);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 11})
    public void createFailed(int score) {
        assertThatIllegalArgumentException().isThrownBy(() -> FrameSequence.of(score));
    }

    @Test
    public void isLast() {
        assertThat(FrameSequence.of(9).isFinal()).isFalse();
        assertThat(FrameSequence.of(10).isFinal()).isTrue();
    }
}
