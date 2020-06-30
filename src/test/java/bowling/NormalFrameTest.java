package bowling;

import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {


    NormalFrame normalFrame = new NormalFrame();

    @DisplayName("일반 볼 테스트")
    @Test
    public void normalFrameTest() {
        normalFrame.addScore(4);
        normalFrame.addScore(4);

        assertThat(normalFrame.isStrike()).isFalse();
        assertThat(normalFrame.isSpare()).isFalse();
        assertThat(normalFrame.isMiss()).isFalse();
        assertThat(normalFrame.moveNextFrame()).isEqualTo(1);
    }

    @DisplayName("스트라이크 테스트")
    @Test
    public void normalFrameStrikeTest() {
        normalFrame.addScore(10);

        assertThat(normalFrame.isStrike()).isTrue();
        assertThat(normalFrame.moveNextFrame()).isEqualTo(1);
    }

    @DisplayName("스트라이크 테스트")
    @Test
    public void normalFrameSpareTest() {
        normalFrame.addScore(3);
        normalFrame.addScore(7);

        assertThat(normalFrame.isStrike()).isFalse();
        assertThat(normalFrame.isSpare()).isTrue();
        assertThat(normalFrame.moveNextFrame()).isEqualTo(1);

    }

    @DisplayName("miss 테스트")
    @Test
    public void normalFrameMissTest() {
        normalFrame.addScore(0);

        assertThat(normalFrame.isStrike()).isFalse();
        assertThat(normalFrame.isSpare()).isFalse();
        assertThat(normalFrame.isMiss()).isTrue();
        assertThat(normalFrame.moveNextFrame()).isEqualTo(0);
    }
}
