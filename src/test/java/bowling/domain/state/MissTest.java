package bowling.domain.state;

import bowling.domain.PitchResults;
import bowling.domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MissTest {
    private PitchResults pitchResults;

    @BeforeEach
    void init(){
        pitchResults = PitchResults.from(new ArrayList<>());
        pitchResults.addNewResult(1);
        pitchResults.addNewResult(2);
    }

    @Test
    void createScoreTest(){
        Miss miss = new Miss(pitchResults);
        Score score = miss.createScore(1);
        assertEquals(0, score.getLeftBonusCount());
    }
}
