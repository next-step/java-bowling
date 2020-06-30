package bowling;

import bowling.domain.frame.FinalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {

    FinalFrame finalFrame = new FinalFrame();

    @DisplayName("일반 볼 테스트")
    @Test
    public void finalFrameTest() {
        finalFrame.addScore(4);
        finalFrame.addScore(4);

        assertThat(finalFrame.isStrike()).isFalse();
        assertThat(finalFrame.isSpare()).isFalse();
        assertThat(finalFrame.isMiss()).isFalse();
        assertThat(finalFrame.moveNextFrame()).isEqualTo(1);
    }

    @DisplayName("스트라이크 테스트")
    @Test
    public void finalFrameStrikeTest() {
        finalFrame.addScore(10);

        assertThat(finalFrame.isStrike()).isTrue();
        assertThat(finalFrame.moveNextFrame()).isEqualTo(0);
    }

    @DisplayName("스트라이크 테스트")
    @Test
    public void finalFrameSpareTest() {
        finalFrame.addScore(3);
        finalFrame.addScore(7);

        assertThat(finalFrame.isStrike()).isFalse();
        assertThat(finalFrame.isSpare()).isTrue();
        assertThat(finalFrame.moveNextFrame()).isEqualTo(0);

    }

    @DisplayName("miss 테스트")
    @Test
    public void normalFrameMissTest() {
        finalFrame.addScore(0);

        assertThat(finalFrame.isStrike()).isFalse();
        assertThat(finalFrame.isSpare()).isFalse();
        assertThat(finalFrame.isMiss()).isTrue();
        assertThat(finalFrame.moveNextFrame()).isEqualTo(0);
    }

}
