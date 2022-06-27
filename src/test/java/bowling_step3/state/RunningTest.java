package bowling_step3.state;

import bowling_step3.domain.Scores;
import bowling_step3.domain.state.FirstPitch;
import bowling_step3.domain.state.Ready;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RunningTest {
    @Test
    void isFinished() {
        assertThat(new FirstPitch(new Scores()).isFinished()).isFalse();
        assertThat(new Ready().isFinished()).isFalse();
    }

    @Test
    void getScoreThrowErr() {
        assertThatThrownBy(() -> new FirstPitch(new Scores()).getScore()).isInstanceOf(UnsupportedOperationException.class);
        assertThatThrownBy(() -> new Ready().getScore()).isInstanceOf(UnsupportedOperationException.class);
    }
}
