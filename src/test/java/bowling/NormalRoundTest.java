package bowling;

import bowling.model.BowlingResult;
import bowling.model.NormalRound;
import bowling.model.Result;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class NormalRoundTest {
    @Test
    public void 스트라이크() {
        //given
        NormalRound expect = new NormalRound(false, new Result(BowlingResult.EMPTY, BowlingResult.STRIKE));

        //when
        NormalRound normalRound = new NormalRound();
        normalRound.play(10, 1);

        //then
        Assertions.assertThat(normalRound).isEqualTo(expect);
    }

    @Test
    public void 스페어() {
        //given
        NormalRound expect = new NormalRound(false, new Result(BowlingResult.EMPTY, BowlingResult.SPARE));

        //when
        NormalRound normalRound = new NormalRound();
        normalRound.play(10, 2);

        //then
        Assertions.assertThat(normalRound).isEqualTo(expect);
    }

    @Test
    public void 미스() {
        //given
        NormalRound expect = new NormalRound(false, new Result(BowlingResult.EMPTY, BowlingResult.MISS));

        //when
        NormalRound normalRound = new NormalRound();
        normalRound.play(9, 2);

        //then
        Assertions.assertThat(normalRound).isEqualTo(expect);
    }

    @Test
    public void 거터() {
        //given
        NormalRound expect = new NormalRound(false, new Result(BowlingResult.EMPTY, BowlingResult.GUTTER));

        //when
        NormalRound normalRound = new NormalRound();
        normalRound.play(0, 1);

        //then
        Assertions.assertThat(normalRound).isEqualTo(expect);
    }
}
