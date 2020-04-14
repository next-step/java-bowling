package bowling.domain.turn;

import bowling.domain.Pins;
import bowling.domain.Result;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TurnsTest {

    @DisplayName("공을 굴려 새로운 턴을 추가하고 반환한다")
    @Test
    public void bowl_success() throws Exception {
        //given
        Turns turns = new Turns(Arrays.asList(Turn.from()));
        Turns expect = new Turns(Arrays.asList(
                Turn.from(),
                new Turn(new Score(3), Result.MISS, new Pins(7), TurnState.SECOND)));

        //when
        turns = turns.bowl(3);

        //then
        assertTrue(turns.equals(expect));
    }
}
