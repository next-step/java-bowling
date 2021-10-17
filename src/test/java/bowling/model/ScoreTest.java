package bowling.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoreTest {
    @Test(expected = CannotCalculateException.class)
    public void 점수계산_불가_예외발생() throws Exception {
        Score.ofStrike().getScore();
    }

    @Test
    public void getScoreWhenXX9() {
        assertEquals(29, Score.ofStrike().bowl(10).bowl(9).getScore());
    }

    @Test
    public void getScoreWhenX81() {
        assertEquals(19, Score.ofStrike().bowl(8).bowl(1).getScore());
    }

    @Test
    public void getScoreWhenSpare8() throws Exception {
        assertEquals(18, Score.ofSpare().bowl(8).getScore());
    }
}
