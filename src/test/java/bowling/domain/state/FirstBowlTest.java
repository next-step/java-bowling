package bowling.domain.state;


import bowling.domain.PitchResults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FirstBowlTest {

    private PitchResults pitchResults;

    @BeforeEach
    void init(){
        pitchResults = PitchResults.from(new ArrayList<>());
        pitchResults.addNewResult(1);
    }

    @Test
    void when_spare_in_firstBowl(){
        FirstBowl firstBowl = new FirstBowl(pitchResults);
        State state = firstBowl.pitch(9);
        assertTrue(state instanceof Spare);
    }

    @Test
    void when_miss_in_firstBowl(){
        FirstBowl firstBowl = new FirstBowl(pitchResults);
        State state = firstBowl.pitch(2);
        assertTrue(state instanceof Miss);
    }
}
