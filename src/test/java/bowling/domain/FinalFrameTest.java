package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @Test
    @DisplayName("Spare 인지 확인한다.")
    void checkIsSpare() {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.addScore(8);
        finalFrame.addScore(2);

        assertThat(finalFrame.isSpare()).isTrue();
    }

    @Test
    @DisplayName("스트라이크나 Spare 처리하지 못하면 2번 투구 후 프레임이 종료된다.")
    void isEnd() {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.addScore(6);
        finalFrame.addScore(2);

        assertThat(finalFrame.isEnd()).isTrue();
    }

}
