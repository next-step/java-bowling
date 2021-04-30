package bowling_refactor;

import bowling_refactor.domain.Frame;
import bowling_refactor.domain.Score;
import bowling_refactor.domain.state.complete_state.Miss;
import bowling_refactor.domain.state.middel_state.FirstBowl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    @DisplayName("투구 점수 확인 테스트")
    void confirmScore() {
        Frame frame = new Frame(1);

        frame.hit(5);
        assertThat(frame.getScore(0)).isEqualTo(Score.ofNoneScore());

        frame.hit(5);
        assertThat(frame.getScore(0)).isEqualTo(Score.ofSpare(0));
    }

    @Test
    @DisplayName("스코어에 보너스 플러스 테스트")
    void addBonus() {
        Score strike = Score.ofStrike(0);
        Frame firstHit = new Frame(5, new FirstBowl(5));
        Frame secondHit = new Frame(5, new Miss(5, 4));

        assertThat(firstHit.addBonus(strike)).isEqualTo(new Score(1, 15));
        assertThat(secondHit.addBonus(strike)).isEqualTo(new Score(0, 19));
    }
}

