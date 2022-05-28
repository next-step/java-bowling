package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @Test
    void 마지막_프레임은_보너스점수_없이_다_던지면_점수가_바로_계산된다() {
        FinalFrame finalFrame = new FinalFrame(null, null);

        finalFrame.shot(10);
        finalFrame.shot(10);
        finalFrame.shot(3);

        assertThat(finalFrame.scoreCalculated()).contains(23);
    }

}
