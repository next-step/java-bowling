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
        finalRound.play(10, 1, BowlingResult.EMPTY);
        boolean isBonus = finalRound.isBonus();

        //then
        assertTrue(isBonus);
    }

    @Test
    public void 마지막_라운드결과가_스페어면_보너스_라운드를_준다() {
        //when
        finalRound.play(10, 2, BowlingResult.EMPTY);
        boolean isBonus = finalRound.isBonus();

        //then
        assertTrue(isBonus);
    }

    @Test
    public void 보너스_라운드_없는_경우() {
        //when
        finalRound.play(3, 2, BowlingResult.EMPTY);
        boolean isBonus = finalRound.isBonus();
        //then
        assertFalse(isBonus);

        //when
        finalRound.play(0, 2, BowlingResult.GUTTER);
        isBonus = finalRound.isBonus();
        //then
        assertFalse(isBonus);
    }
}
