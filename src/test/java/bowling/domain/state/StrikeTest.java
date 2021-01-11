package bowling.domain.state;

import bowling.domain.PitchResults;
import bowling.domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class StrikeTest {
    private PitchResults pitchResults;

    @BeforeEach
    void init(){
        pitchResults = PitchResults.from(new ArrayList<>());
        pitchResults.addNewResult(10);
    }

    @Test
    void createScoreTest(){
        Strike strike = new Strike(pitchResults);
        Score score = strike.createScore(0);
        assertEquals(2, score.getLeftBonusCount());
    }
}
