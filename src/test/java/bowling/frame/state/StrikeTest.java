package bowling.frame.state;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bowling.frame.Score;

public class StrikeTest {
    @Test(expected = UnsupportedOperationException.class)
    public void bowl() {
        new Strike().bowl(8);
    }

    @Test
    public void getScore() throws Exception {
        Score score = new Score(10, 2);
        Strike strike = new Strike();
        score = strike.calculateAdditionalScore(score);
        assertEquals(new Score(20, 1), score);

        strike = new Strike();
        score = strike.calculateAdditionalScore(score);
        assertEquals(new Score(30, 0), score);
    }
}
