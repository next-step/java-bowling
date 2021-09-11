package bowling;

import bowling.model.BowlingResult;
import bowling.model.FinalRound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FinalRoundTest {
    private FinalRound finalRound;

    @BeforeEach
    public void setUp() {
        finalRound = new FinalRound();
    }

    @Test
    public void 마지막_라운드결과가_스트라이크면_보너스_라운드를_준다() {
        //when
        boolean isBonus = finalRound.isBonus(false, BowlingResult.STRIKE);

        //then
        assertTrue(isBonus);
    }

    @Test
    public void 마지막_라운드결과가_스페어면_보너스_라운드를_준다() {
        //when
        boolean isBonus = finalRound.isBonus(false, BowlingResult.SPARE);

        //then
        assertTrue(isBonus);
    }

    @Test
    public void 보너스_라운드_없는_경우() {
        //when
        boolean isBonus = finalRound.isBonus(false, BowlingResult.MISS);
        //then
        assertFalse(isBonus);

        //when
        isBonus = finalRound.isBonus(false, BowlingResult.GUTTER);
        //then
        assertFalse(isBonus);
    }
}
