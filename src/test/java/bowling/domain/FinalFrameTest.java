package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.exception.CannotCalculateException;

class FinalFrameTest {

    @DisplayName("보너스 볼이 없을 경우 횟수를 초과할 경우 exception을 발생시킨다")
    @Test
    void noBonusBowl() {
        FinalFrame finalFrame = new FinalFrame(false);
        finalFrame.bowl(2);
        finalFrame.bowl(3);
        assertThatThrownBy(() -> {
            finalFrame.bowl(5);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("스트라이크를 두번 점수를 받아온다.")
    @Test
    void bowlStrike2() {
        FinalFrame finalFrame = new FinalFrame(true);
        finalFrame.bowl(10);
        finalFrame.bowl(10);
        Frame bowl = finalFrame.bowl(8);
        FrameResult frameResult = finalFrame.getFrameResult();
        assertThat(frameResult).isEqualTo(new FrameResult("X|X|8", 28));
    }

    @DisplayName("스트라이크를 세번친후 점수를 받아온다.")
    @Test
    void bowlStrike3() {
        FinalFrame finalFrame = new FinalFrame(true);
        finalFrame.bowl(10);
        finalFrame.bowl(10);
        Frame bowl = finalFrame.bowl(10);
        FrameResult frameResult = finalFrame.getFrameResult();
        assertThat(frameResult).isEqualTo(new FrameResult("X|X|X", 30));
    }

    @DisplayName("구번 프레임에서 스트라이크를 친경우")
    @Test
    public void getScoreNomalFrame9Strike() {
        FinalFrame finalFrame = new FinalFrame(true);
        Score score = Score.ofStrike();
        finalFrame.bowl(10).bowl(10).bowl(10);
        assertThat(finalFrame.calculateAdditionalScore(score)).isEqualTo(new Score(30));
    }

    @DisplayName("구번 프레임에 스페어를 친경우")
    @Test
    public void getScoreNineFrameSpare() {
        FinalFrame finalFrame = new FinalFrame(true);
        Score score = Score.ofSpare();
        finalFrame.bowl(5).bowl(1);
        assertThat(finalFrame.calculateAdditionalScore(score)).isEqualTo(new Score(15));
    }

    @Test
    public void getScoreNineFrameStrikeNotReady() {
        FinalFrame finalFrame = new FinalFrame(true);
        Score score = Score.ofStrike();
        finalFrame.bowl(10);
        assertThatThrownBy(() -> {
            finalFrame.calculateAdditionalScore(score).getScore();
        }).isInstanceOf(CannotCalculateException.class);
    }
}
