package bowling;

import bowling.domain.set.FrameSet;
import bowling.domain.set.NormalFrameSet;
import bowling.domain.state.State;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FrameSetTest {

    @Test
    void playTest() {
        FrameSet firstFrameSet = NormalFrameSet.create(1);
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
                .play(10) // Strike
                .play(10); // Strike (bonus)

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
                .play(1) // Hit
                .play(9) // Spare
                .play(10); // Strike (bonus)

        assertThat(lastState2.isEnd()).isTrue();
    }

    @Test
    void throwTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> NormalFrameSet.create(1).play(-1));
        assertThatIllegalArgumentException().isThrownBy(() -> NormalFrameSet.create(1).play(11));
    }
}
