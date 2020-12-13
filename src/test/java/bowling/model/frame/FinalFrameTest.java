package bowling.model.frame;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FinalFrameTest {

    @Test
    void bowling_스페어_보너스_투구() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowling(8);
        finalFrame.bowling(2);
        finalFrame.bowling(0);
        assertThat(finalFrame.isFinished()).isTrue();
    }

    @Test
    void bowling_첫_투구_스트라이크_아닌_경우_계속_던짐() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowling(8);
        finalFrame.bowling(1);
        assertThatIllegalArgumentException()
                .isThrownBy(() ->finalFrame.bowling(10))
                .withMessage("해당 프레임에서는 더 이상 던질 수 없습니다.");
    }

    @Test
    void bowling_첫_투구_스트라이크_후_스트라이크() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowling(10);
        assertThat(finalFrame.isFinished()).isFalse();

        finalFrame.bowling(10);

        assertThat(finalFrame.isFinished()).isTrue();
    }
}