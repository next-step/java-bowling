package bowling.domain.frame;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FinalFrameTest {

    @Test
    @DisplayName("스트라이크 는 공을 세번 굴린다.")
    public void strikeBall() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.addPin(10);
        assertThat(finalFrame.getBallCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("스페어는 공을 세번 굴린다.")
    public void spareBall() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.addPin(2);
        finalFrame.addPin(8);
        assertThat(finalFrame.getBallCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("남은 핀이 있으면 공을 두번굴린다.")
    public void normalBall() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.addPin(2);
        finalFrame.addPin(7);
        assertThat(finalFrame.getBallCount()).isEqualTo(2);
    }

}
