package bowling;

import bowling.domain.Bowling;
import bowling.domain.Player;
import bowling.domain.Score;
import bowling.domain.frame.Frames;
import bowling.view.ResultView;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    @Test
    void minScoreTest() {
        assertThatThrownBy(() -> {
            Score score = new Score(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void maxScoreTest() {
        assertThatThrownBy(() -> {
            Score score = new Score(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void name() {
        Bowling bowling = new Bowling();

        Player player = new Player("tt");
        ResultView resultView = new ResultView(player, bowling);

        //for (int frameCount = 0; frameCount <= Frames.BOWLING_GAME_FRAME;) {
        for (int frameCount = 0; frameCount <= 8;) {
            int nextCount = bowling.addPlayerScore(frameCount, 10);
            resultView.displayResult(frameCount);
            resultView.displayAmountScore(frameCount);
            frameCount += nextCount;
        }
    }
}
