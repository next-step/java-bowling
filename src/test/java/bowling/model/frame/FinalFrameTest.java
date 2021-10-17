package bowling.model.frame;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import bowling.model.CannotCalculateException;
import bowling.model.GameOverException;
import bowling.model.Score;

public class FinalFrameTest {
    private FinalFrame finalFrame;

    @Before
    public void setup() {
        finalFrame = new FinalFrame();
    }

    @Test
    public void 미스이면_프레임은_끝난다() {
        finalFrame.bowl(8).bowl(1);
        assertTrue(finalFrame.isFinish());
    }

    @Test
    public void 스페어이면_보너스_프레임이_주어진다() {
        finalFrame.bowl(8).bowl(2);
        assertFalse(finalFrame.isFinish());
    }

    @Test
    public void 스페어_게임종료() {
        finalFrame.bowl(8).bowl(2).bowl(3);
        assertTrue(finalFrame.isFinish());
    }

    @Test (expected = GameOverException.class)
    public void 게임종료생_후_투구시_예외발생() {
        finalFrame.bowl(8).bowl(2).bowl(3).bowl(4);
    }

    @Test
    public void 미스_스코어() {
        finalFrame.bowl(8).bowl(1);
        assertThat(finalFrame.getScore()).isEqualTo(new Score(9, 0));
    }

    @Test(expected = CannotCalculateException.class)
    public void 한번_투구시_계산_불가() {
        finalFrame.bowl(8);
        finalFrame.getScore();
    }

    @Test
    public void 스페어_스코어() {
        finalFrame.bowl(8).bowl(2);
        assertThat(finalFrame.getScore()).isEqualTo(new Score(10, 1));
    }

    @Test
    public void 스트라이크_두번_스코어() {
        finalFrame.bowl(10).bowl(10).bowl(8);
        assertThat(finalFrame.getScore()).isEqualTo(new Score(28, 0));
    }

    @Test
    public void 스트라이크_세번_스코어() {
        finalFrame.bowl(10).bowl(10).bowl(10);
        assertThat(finalFrame.getScore()).isEqualTo(new Score(30, 0));
    }

    @Test
    public void 스코어_9프레임_스트라이크() {
        Score score = Score.ofStrike();
        finalFrame.bowl(10).bowl(10);
        assertThat(finalFrame.calculateAdditionalScore(score)).isEqualTo(new Score(30, 0));
    }

    @Test
    public void 스코어_9프레임_스페어() {
        Score score = Score.ofSpare();
        finalFrame.bowl(9).bowl(1);
        assertThat(finalFrame.calculateAdditionalScore(score)).isEqualTo(new Score(19, 0));
    }

    @Test(expected = CannotCalculateException.class)
    public void 스코어_9프레임_스트라이크_예외발생() {
        Score score = Score.ofStrike();
        finalFrame.bowl(10);
        finalFrame.calculateAdditionalScore(score).getScore();
    }

    @Test
    public void getDesc_3Strike() {
        finalFrame.bowl(10).bowl(10).bowl(10);
        assertThat(finalFrame.getDesc()).isEqualTo("X | X | X");
    }

    @Test
    public void getDesc_Spare() {
        finalFrame.bowl(8).bowl(2).bowl(10);
        assertThat(finalFrame.getDesc()).isEqualTo("8 | / | X");
    }

    @Test
    public void getDesc_Miss() {
        finalFrame.bowl(8).bowl(1);
        assertThat(finalFrame.getDesc()).isEqualTo("8 | 1");
    }

}
