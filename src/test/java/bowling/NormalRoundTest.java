package bowling;

import bowling.model.BowlingResult;
import bowling.model.NormalRound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NormalRoundTest {
    private NormalRound normalRound;

    @BeforeEach
    public void setUp() {
        normalRound = new NormalRound();
    }

    @Test
    public void 스트라이크일_경우_바로_다음_라운드는_스킵한다() {
        //when
        normalRound.play(10, 1, BowlingResult.EMPTY);
        boolean isSkip = normalRound.isSkipNextRound();

        //then
        assertTrue(isSkip);
    }

    @Test
    public void 스트라이크가_아닐_경우_다음_라운드는_스킵하지않는다() {
        //when
        normalRound.play(0, 1, BowlingResult.EMPTY);
        boolean isSkip1 = normalRound.isSkipNextRound();
        //then
        assertFalse(isSkip1);

        //when
        normalRound.play(3, 2, BowlingResult.GUTTER);
        boolean isSkip2 = normalRound.isSkipNextRound();
        //then
        assertFalse(isSkip2);
    }
}
