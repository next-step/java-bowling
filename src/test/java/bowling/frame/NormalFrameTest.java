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

    @DisplayName("1번 투구한 경우 넘어갈수 없음")
    @Test
    public void isMovableToNextFrame_1번() {
        NormalFrame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(Score.valueOf(3));

        assertThat(normalFrame.isMovableToNextFrame()).isFalse();
    }

    @DisplayName("기존 Frame을 통해 다음 Frame을 생성함")
    @Test
    public void next_2번() {
        NormalFrame normalFrame = NormalFrame.initiate();

        assertThat(normalFrame.getIndex()).isEqualTo(1);

        NormalFrame nextFrame = normalFrame.next();

        assertThat(nextFrame.getIndex()).isEqualTo(2);
    }
}
