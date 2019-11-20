package bowling;

import bowling.domain.FrameSet;
import bowling.domain.state.State;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameSetTest {

    @Test
    void playTest() {
        FrameSet firstFrameSet = FrameSet.create(1);
        State lastState = firstFrameSet
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10); // Strike

        assertThat(lastState.isEnd()).isTrue();

        State lastState2 = firstFrameSet
                .play(10) // Strike
                .play(8) // Hit
                .play(2) // Spare
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10); // Strike

        assertThat(lastState2.isEnd()).isTrue();
    }
}
