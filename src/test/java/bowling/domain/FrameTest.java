package bowling.domain;

import bowling.domain.state.FirstBowl;
import bowling.domain.state.Miss;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {
    @Test
    @DisplayName("투구 점수를 반영한다.")
    void addBall() {
        int frameNumber = 1;
        Frame frame = new Frame(frameNumber);
        frame.fallDown(5);

        assertThat(frame.getScore(0)).isEqualTo(Score.ofNoneScore());

        frame.fallDown(5);

        assertThat(frame.getScore(0)).isEqualTo(Score.ofSpare(0));
    }

    @Test
    @DisplayName("score에 보너스를 더하는지 테스트 한다.")
    void addBonus() {
        Score strike = Score.ofStrike(0);
        Frame firstFallDown = new Frame(2, new FirstBowl(5));
        Frame secondFallDown = new Frame(2, new Miss(5, 4));

        assertThat(firstFallDown.addBonus(strike)).isEqualTo(new Score(15, 1));
        assertThat(secondFallDown.addBonus(strike)).isEqualTo(new Score(19, 0));

    }
}
