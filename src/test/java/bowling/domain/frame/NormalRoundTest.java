package bowling.domain.frame;

import bowling.domain.state.Spare;
import bowling.domain.state.Start;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalRoundTest {

    @DisplayName("Normal 라운드의 마지막 라운드인지 테스트")
    @Test
    void isMaxRoundTest() {
        NormalRound normalRound = new NormalRound(9, Start.createOf());

        assertTrue(normalRound.isMaxRound());
    }
    

}