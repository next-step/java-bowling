package bowling;

import bowling.domain.FinalFrame;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {

    @Test
    public void create_test() {
        FinalFrame finalFrame = new FinalFrame();
        assertThat(finalFrame).isEqualTo(new FinalFrame());
    }

    @Test
    public void is_finished_bonus_test() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(1);
        finalFrame.bowl(9);
        assertThat(finalFrame.isFinished()).isEqualTo(false);
    }

    @Test
    public void is_finished_three_bowl_test() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(1);
        finalFrame.bowl(9);
        finalFrame.bowl(10);

        assertThat(finalFrame.isFinished()).isEqualTo(true);
    }
}
