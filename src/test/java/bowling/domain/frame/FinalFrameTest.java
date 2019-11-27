package bowling.domain.frame;

import bowling.domain.frame.FinalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @Test
    @DisplayName("Spare 처리하면 한번 더 투구 할 수 있다.")
    void checkIsSpare() {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.bowl(8);
        finalFrame.bowl(2);

        assertThat(finalFrame.isEnd()).isFalse();
    }

    @Test
    @DisplayName("스트라이크나 Spare 처리하지 못하면 2번 투구 후 프레임이 종료된다.")
    void isEnd() {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.bowl(6);
        finalFrame.bowl(2);

        assertThat(finalFrame.isEnd()).isTrue();
    }

}
