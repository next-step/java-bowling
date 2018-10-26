package bowling.frame;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LastFrameTest {
    private LastFrame lastFrame;

    @Before
    public void setup() {
        lastFrame = new LastFrame();
    }

    @Test
    public void miss() {
        lastFrame.bowl(8).bowl(1);
        assertThat(lastFrame.isGameEnd()).isTrue();
    }

    @Test
    public void spare_isnot_endgame() {
        lastFrame.bowl(8).bowl(2);
        assertThat(lastFrame.isGameEnd()).isFalse();
    }

    @Test
    public void spare_is_endgame() {
        lastFrame.bowl(8).bowl(2).bowl(7);
        assertThat(lastFrame.isGameEnd()).isTrue();
    }

    @Test (expected = GameOverException.class)
    public void game_over() {
        lastFrame.bowl(8).bowl(2).bowl(7).bowl(3);
    }

    @Test
    public void getScore_miss() {
        lastFrame.bowl(8).bowl(1);
        assertThat(lastFrame.getScore()).isEqualTo(new Score(9));
    }

    @Test(expected = CannotCalculateException.class)
    public void getScore_cannot_calculate() {
        lastFrame.bowl(8);
        lastFrame.getScore();
    }

    @Test
    public void getScore_spare() {
        lastFrame.bowl(8).bowl(2).bowl(9);
        assertThat(lastFrame.getScore()).isEqualTo(new Score(19));
    }

    @Test
    public void getScore_twoStrike() {
        lastFrame.bowl(10).bowl(10).bowl(8);
        assertThat(lastFrame.getScore()).isEqualTo(new Score(28));
    }

    @Test
    public void getScore_threeStrike() {
        lastFrame.bowl(10).bowl(10).bowl(10);
        assertThat(lastFrame.getScore()).isEqualTo(new Score(30));
    }

    @Test
    public void getScore_9프레임_Strike() {
        Score score = Score.strike();
        lastFrame.bowl(10).bowl(10);
        assertThat(lastFrame.calculateAdditionalScore(score)).isEqualTo(new Score(30));
    }

    @Test
    public void getScore_9프레임_Spare() {
        Score score = Score.spare();
        lastFrame.bowl(9).bowl(1);
        assertThat(lastFrame.calculateAdditionalScore(score)).isEqualTo(new Score(19));
    }

    @Test (expected = CannotCalculateException.class)
    public void getScore_9프레임_Strike_notReady() {
        Score score = Score.strike();
        lastFrame.bowl(10);
        lastFrame.calculateAdditionalScore(score).getScore();
    }

    @Test
    public void getDesc_3Strike() {
        lastFrame.bowl(10).bowl(10).bowl(10);
        assertThat(lastFrame.getDesc()).isEqualTo("X | X | X");
    }

    @Test
    public void getDesc_Spare() {
        lastFrame.bowl(8).bowl(2).bowl(10);
        assertThat(lastFrame.getDesc()).isEqualTo("8 | / | X");
    }

    @Test
    public void getDesc_Miss() {
        lastFrame.bowl(8).bowl(1);
        assertThat(lastFrame.getDesc()).isEqualTo("8 | 1");
    }
}
