package bowling.frame;

import static org.junit.Assert.*;

import org.junit.Test;

import bowling.frame.CannotCalculateException;
import bowling.frame.Score;

public class ScoreTest {
    @Test(expected = CannotCalculateException.class)
    public void getScoreWhenUnReady() throws Exception {
        new Score(10, 2).getScore();
    }

    @Test
    public void getScoreWhenXX9() {
        Score score = new Score(10, 2);
        assertEquals(29, score.bowl(10).bowl(9).getScore());
    }

    @Test
    public void getScoreWhenX81() {
        Score score = new Score(10, 2);
        assertEquals(19, score.bowl(8).bowl(1).getScore());
    }

    @Test
    public void getScoreWhenSpare8() throws Exception {
        Score score = new Score(10, 1);
        assertEquals(18, score.bowl(8).getScore());
    }
}
