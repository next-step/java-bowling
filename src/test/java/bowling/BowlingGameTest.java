package bowling;

import bowling.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class BowlingGameTest {
    @Test
    public void 일반라운드에서_다음_라운드를_스킵할_경우_시도횟수는_최대가_된다() {
        //given
        Round round = new NormalRound(false, new Result(BowlingResult.EMPTY, BowlingResult.STRIKE));
        RoundSet roundSet = new RoundSet(10, Arrays.asList(round));
        BowlingGame expect = new BowlingGame(1, roundSet);

        BowlingGame game = new BowlingGame();
        int tryCount = game.play(1, 1, 10);

        assertThat(tryCount).isEqualTo(2);
        assertThat(game).isEqualTo(expect);
    }

    @Test
    public void 일반라운드에서_다음_라운드를_스킵하지_않을_경우_시도횟수는_변화가_없다() {
        //given
        Round round1 = new NormalRound(false, new Result(BowlingResult.EMPTY, BowlingResult.EMPTY));
        Round round2 = new NormalRound(false, new Result(BowlingResult.EMPTY, BowlingResult.EMPTY));
        RoundSet roundSet = new RoundSet(9, Arrays.asList(round1, round2));
        BowlingGame expect = new BowlingGame(1, roundSet);

        BowlingGame game = new BowlingGame();
        int tryCount = game.play(1, 1, 9);

        assertThat(tryCount).isEqualTo(1);
        assertThat(game).isEqualTo(expect);
    }

    @Test
    public void 파이널라운드_결과가_스트라이크일시_시도횟수는_0이_된다() {
        //given
        Round round1 = new FinalRound(false, new Result(BowlingResult.EMPTY, BowlingResult.STRIKE));
        Round round2 = new FinalRound(true, new Result(BowlingResult.STRIKE, BowlingResult.EMPTY));
        RoundSet roundSet = new RoundSet(10, Arrays.asList(round1, round2));
        BowlingGame expect = new BowlingGame(10, roundSet);

        //when
        BowlingGame game = new BowlingGame();
        int tryCount = game.play(10, 1, 10);

        //then
        assertThat(game).isEqualTo(expect);
        assertThat(tryCount).isEqualTo(0);
    }

    @Test
    public void 파이널라운드_결과가_스페어일시_시도횟수는_1이_된다() {
        //given
        Round round1 = new FinalRound(false, new Result(BowlingResult.EMPTY, BowlingResult.EMPTY));
        Round round2 = new FinalRound(false, new Result(BowlingResult.EMPTY, BowlingResult.SPARE));
        Round round3 = new FinalRound(true, new Result(BowlingResult.SPARE, BowlingResult.STRIKE));

        Round emptyRound1 = new FinalRound(false, new Result(BowlingResult.EMPTY, BowlingResult.EMPTY));
        Round emptyRound2 = new FinalRound(true, new Result(BowlingResult.SPARE, BowlingResult.EMPTY));

        RoundSet roundSet1 = new RoundSet(1, Arrays.asList(round1, emptyRound1));
        RoundSet roundSet2 = new RoundSet(10, Arrays.asList(round1, round2, emptyRound2));
        RoundSet roundSet3 = new RoundSet(10, Arrays.asList(round1, round2, round3));

        BowlingGame expect1 = new BowlingGame(10, roundSet1);
        BowlingGame expect2 = new BowlingGame(10, roundSet2);
        BowlingGame expect3 = new BowlingGame(10, roundSet3);

        //when
        BowlingGame game = new BowlingGame();
        int tryCount = game.play(10, 1, 1);

        //then
        assertThat(tryCount).isEqualTo(1);
        assertThat(game).isEqualTo(expect1);

        //when
        tryCount = game.play(10, 2, 9);

        //then
        assertThat(tryCount).isEqualTo(1);
        assertThat(game).isEqualTo(expect2);

        //when
        tryCount = game.play(10, 2, 10);

        //then
        assertThat(tryCount).isEqualTo(2);
        assertThat(game).isEqualTo(expect3);
    }
}
