package bowling.frame;

import bowling.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @DisplayName("2번 투구한 경우 다음 Frame으로 넘어감")
    @Test
    public void isMovableToNextFrame_2번투구() {
        NormalFrame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(Score.valueOf(5));
        normalFrame.bowl(Score.valueOf(5));

        assertThat(normalFrame.isMovableToNextFrame()).isTrue();
    }

    @DisplayName("Strike를 한 경우 Frame으로 넘어감")
    @Test
    public void isMovableToNextFrame_스트라이크() {
        NormalFrame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(Score.valueOf(10));

        assertThat(normalFrame.isMovableToNextFrame()).isTrue();
    }
}
