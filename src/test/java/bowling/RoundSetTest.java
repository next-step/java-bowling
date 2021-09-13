package bowling;

import bowling.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class RoundSetTest {
    @Test
    public void 일반라운드일때_스트라이크시_다음_라운드를_스킵한다() {
        //given
        int firstPoint = 10;

        //when
        RoundSet roundSet = new RoundSet(1);
        roundSet.play(1, firstPoint);

        //then
        assertThat(roundSet.size()).isEqualTo(1);
    }

    @Test
    public void 파이널라운드는_스트라이크시_다음_라운드를_스킵하지_않는다() {
        //given
        int firstPoint = 10;

        //when
        RoundSet roundSet = new RoundSet(10);
        roundSet.play(1, firstPoint);

        //then
        assertThat(roundSet.size()).isEqualTo(2);
    }

    @Test
    public void 파이널라운드는_스트라이크일_경우_보너스라운드를_준다() {
        //given
        int firstPoint = 10;
        Round round1 = new FinalRound(false, new Result(BowlingResult.EMPTY, BowlingResult.STRIKE));
        Round round2 = new FinalRound(true, new Result(BowlingResult.STRIKE, BowlingResult.EMPTY));
        RoundSet expect = new RoundSet(10, Arrays.asList(round1, round2));

        //when
        RoundSet roundSet = new RoundSet(10);
        roundSet.play(1, firstPoint);

        //then
        assertThat(roundSet).isEqualTo(expect);
    }

    @Test
    public void 파이널라운드는_스페어일_경우_보너스라운드를_준다() {
        //given
        int firstPoint = 1;
        int secondPoint = 9;
        Round round1 = new FinalRound(false, new Result(BowlingResult.EMPTY, BowlingResult.EMPTY));
        Round round2 = new FinalRound(false, new Result(BowlingResult.EMPTY, BowlingResult.SPARE));
        Round round3 = new FinalRound(true, new Result(BowlingResult.SPARE, BowlingResult.EMPTY));
        RoundSet expect = new RoundSet(10, Arrays.asList(round1, round2, round3));

        //when
        RoundSet roundSet = new RoundSet(10);
        roundSet.play(1, firstPoint);
        roundSet.play(2, secondPoint);

        //then
        assertThat(roundSet).isEqualTo(expect);
    }
}
