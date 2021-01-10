package bowling.domain.state;

import bowling.domain.PitchResults;
import bowling.domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SpareTest {
    private PitchResults pitchResults;

    @BeforeEach
    void init(){
        pitchResults = PitchResults.from(new ArrayList<>());
        pitchResults.addNewResult(1);
        pitchResults.addNewResult(9);
    }

    @Test
    void createScoreTest(){
        Spare spare = new Spare(pitchResults);
        Score score = spare.createScore(1);
        assertEquals(1, score.getLeftBonusCount());
    }
}
