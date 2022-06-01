package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.OptionalInt;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @Test
    void 마지막_프레임은_보너스점수_없이_다_던지면_점수가_바로_계산된다() {
        FinalFrame finalFrame = new FinalFrame(null, null);

        finalFrame.shot(10);
        finalFrame.shot(10);
        finalFrame.shot(3);

        assertThat(finalFrame.scoreCalculated()).isEqualTo(OptionalInt.of(23));
    }

    @Test
    void 마지막_프레임은_보너스투구_없는_상태에서_세번째_투구를하면_예외가_발생한다() {
        FinalFrame finalFrame = new FinalFrame(null, null);

        finalFrame.shot(4);
        finalFrame.shot(4);

        assertThatThrownBy(() -> {
            finalFrame.shot(2);
        }).isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> {
            finalFrame.shot(3);
        }).isInstanceOf(IllegalStateException.class);
    }

}
