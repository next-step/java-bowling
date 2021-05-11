package bowling.domain.frame;

import bowling.domain.state.Miss;
import bowling.domain.state.Start;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinalRoundTest {

    @DisplayName("10프레임 Spare나 Strike 후에 3투구 후 마지막 라운드 확인")
    @Test
    void isMaxRoundThridPitchTest() {
        FinalRound finalRound = new FinalRound(4, Start.createOf());
        assertTrue(finalRound.isMaxRound());
    }

    @DisplayName("10프레임 결과가 Miss일 경우 마지막 라운드 확인")
    @Test
    void isMaxRoundMissTest() {
        FinalRound finalRound = new FinalRound(3, new Miss(3,5));
        assertTrue(finalRound.isMaxRound());
    }
}