package bowling.domain.frame;

import bowling.domain.state.Miss;
import bowling.domain.state.Ready;
import bowling.domain.state.Start;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    private Round round;

    @BeforeEach
    void init() {
        round = new Round(1, new Start());
    }

    @DisplayName("nextStart 테스트")
    @Test
    void roundNextStartTest() {
        Round next = round.nextStart();

        assertTrue(next.getPitchState() instanceof Start);
        assertEquals(next.getRound(), 2);
    }

    @DisplayName("point 인자가 있는 next 테스트")
    @Test
    void roundNextWithPointTest() {
        Round next = round.next(4);

        assertEquals(next, new Round(1, new Ready(4)));
    }
    
    @DisplayName("point 인자가 없는 next 테스트")
    @Test
    void roundNextTest() {
        Round next = round.next();

        assertEquals(next.getRound(), 2);
    }


    @DisplayName("finalMaxRound 테스트 - true일 경우 (round가 3보다 클경우)")
    @Test
    void isFinalMaxRoundTrueByRoundTest() {
        Round round = new Round(4, new Start());

        boolean finalMaxRound = round.isFinalMaxRound();
        assertEquals(finalMaxRound, true);
    }

    @DisplayName("finalMaxRound 테스트 - true일 경우 " +
            "(round의 pitchState가 isFinal가 true거나 isNext가 false인 경우)")
    @Test
    void isFinalMaxRoundTrueByPitchStateTest() {
        Round round = new Round(1, new Miss(5, 4));

        boolean finalMaxRound = round.isFinalMaxRound();
        assertEquals(finalMaxRound, true);
    }

    @DisplayName("finalMaxRound 테스트 - false 경우")
    @Test
    void isFinalMaxRoundFalseTest() {

        boolean finalMaxRound = round.isFinalMaxRound();
        assertEquals(finalMaxRound, false);
    }
}