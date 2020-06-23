package bowling.domain.frame;


import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.state.PinsState;
import bowling.domain.state.ScoreType;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @DisplayName("miss는 두번의 라운드를 진행할수있다.")
    @Test
    void miss() {
        Frame finalFrame = Frame.last();
        finalFrame.play(1);
        finalFrame.play(2);

        assertThat(finalFrame.createPinState())
            .isEqualTo(new PinsState(Arrays.asList(1, 2), Arrays.asList(ScoreType.MISS)));
    }

    @DisplayName("spare는 세번의 라운드를 진행할수있다.")
    @Test
    void spare() {
        Frame finalFrame = Frame.last();
        finalFrame.play(1);
        finalFrame.play(9);
        finalFrame.play(9);

        assertThat(finalFrame.createPinState())
            .isEqualTo(new PinsState(Arrays.asList(1, 9, 9), Arrays.asList(ScoreType.SPARE)));
    }

    @DisplayName("strike는 세번의 라운드를 진행할수있다.")
    @Test
    void strike() {
        Frame finalFrame = Frame.last();

        finalFrame.play(10);
        finalFrame.play(9);
        finalFrame.play(9);

        assertThat(finalFrame.createPinState())
            .isEqualTo(new PinsState(Arrays.asList(10, 9, 9), Arrays.asList(ScoreType.STRIKE)));
    }

}
