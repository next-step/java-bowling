package bowling;

import bowling.model.BowlingResult;
import bowling.model.FinalRound;
import bowling.model.NormalRound;
import bowling.model.RoundSet;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class RoundSetTest {
    @Test
    public void 라운드_전체_점수를_계산한다() {
        //given
        boolean isBonusRound = false;
        int firstRoundPoint = 8;
        int secondRoundPoint = 1;

        //when
        RoundSet roundSet = new RoundSet(1);
        roundSet.calcTotalPoint(isBonusRound, firstRoundPoint);
        roundSet.calcTotalPoint(isBonusRound, secondRoundPoint);

        //then
        assertThat(roundSet).isEqualTo(new RoundSet(9, Arrays.asList(new NormalRound())));
    }

    @Test
    public void 보너스_라운드일_경우_전체_점수를_계산하지_않는다() {
        //given
        boolean isBonusRound = true;
        int firstRoundPoint = 8;
        int secondRoundPoint = 1;

        //when
        RoundSet roundSet = new RoundSet(1);
        roundSet.calcTotalPoint(isBonusRound, firstRoundPoint);
        //then
        assertThat(roundSet).isEqualTo(new RoundSet(firstRoundPoint, Arrays.asList(new NormalRound())));

        //when
        roundSet.calcTotalPoint(isBonusRound, secondRoundPoint);
        //then
        assertThat(roundSet).isEqualTo(new RoundSet(secondRoundPoint, Arrays.asList(new NormalRound())));
    }

    @Test
    public void 마지막_라운드는_final_round를_생성한다() {
        //given
        RoundSet roundSet = new RoundSet(10);

        //then
        assertThat(roundSet).isEqualTo(new RoundSet(0, Arrays.asList(new FinalRound())));
    }

    @Test
    public void 알맞은_게임_결과를_반환한다() {
        //given
        boolean isBonusRound = false;
        int firstRoundPoint = 8;
        int secondRoundPoint = 2;
        BowlingResult beforeResult = BowlingResult.EMPTY;

        //when
        RoundSet roundSet = new RoundSet(1);
        roundSet.calcTotalPoint(isBonusRound, firstRoundPoint);
        BowlingResult actual = roundSet.play(1, beforeResult);
        beforeResult = actual;
        //then
        assertThat(actual).isEqualTo(BowlingResult.EMPTY);

        //when
        roundSet.calcTotalPoint(isBonusRound, secondRoundPoint);
        actual = roundSet.play(2, beforeResult);
        //then
        assertThat(actual).isEqualTo(BowlingResult.SPARE);
    }

    @Test
    public void 스트라이크일_경우_다음_라운드를_스킵한다() {
        //given
        boolean isBonusRound = false;
        int firstRoundPoint = 10;
        BowlingResult beforeResult = BowlingResult.EMPTY;

        //when
        RoundSet roundSet = new RoundSet(1);
        roundSet.calcTotalPoint(isBonusRound, firstRoundPoint);
        roundSet.play(1, beforeResult);
        boolean actual = roundSet.isSkipNextRound();

        //then
        assertThat(actual).isEqualTo(true);
    }

    @Test
    public void 마지막_라운드_결과가_스트라이크일_경우_보너스_라운드를_부여한다() {
        //given
        boolean isBonusRound = false;
        int firstRoundPoint = 10;
        BowlingResult beforeResult = BowlingResult.EMPTY;

        //when
        RoundSet roundSet = new RoundSet(10);
        roundSet.calcTotalPoint(isBonusRound, firstRoundPoint);
        roundSet.play(1, beforeResult);
        isBonusRound = roundSet.isBonus();
        //then
        assertThat(isBonusRound).isEqualTo(true);
    }

    @Test
    public void 마지막_라운드_결과가_스페어일_경우_보너스_라운드를_부여한다() {
        //given
        boolean isBonusRound = false;
        int firstRoundPoint = 9;
        int secondRoundPoint = 1;
        BowlingResult beforeResult = BowlingResult.EMPTY;

        //when
        RoundSet roundSet = new RoundSet(10);
        roundSet.calcTotalPoint(isBonusRound, firstRoundPoint);
        BowlingResult bowlingResult = roundSet.play(1, beforeResult);
        beforeResult = bowlingResult;
        isBonusRound = roundSet.isBonus();
        //then
        assertThat(isBonusRound).isEqualTo(false);

        //when
        roundSet.calcTotalPoint(isBonusRound, secondRoundPoint);
        roundSet.play(1, beforeResult);
        isBonusRound = roundSet.isBonus();
        //then
        assertThat(isBonusRound).isEqualTo(true);
    }

    @Test
    public void 마지막_라운드_다음_라운드는_final_round를_생성한다() {
        //when
        RoundSet roundSet = new RoundSet(10);
        roundSet.nextRound();

        //then
        assertThat(roundSet).isEqualTo(new RoundSet(0, Arrays.asList(new FinalRound(), new FinalRound())));
    }

    @Test
    public void 일반_라운드_다음_라운드는_normal_round를_생성한다() {
        //when
        RoundSet roundSet = new RoundSet(9);
        roundSet.nextRound();

        //then
        assertThat(roundSet).isEqualTo(new RoundSet(0, Arrays.asList(new NormalRound(), new NormalRound())));
    }


}
