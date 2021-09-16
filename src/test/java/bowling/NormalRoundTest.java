package bowling;

import bowling.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class NormalRoundTest {
    @Test
    public void 스트라이크() {
        //given
        NormalRound expect = new NormalRound(new Result(new Miss(),new Strike()));

        //when
        NormalRound normalRound = new NormalRound();
        normalRound.play(new Point(10), 1);

        //then
        Assertions.assertThat(normalRound).isEqualTo(expect);
    }

    @Test
    public void 스페어() {
        //given
        NormalRound expect = new NormalRound(new Result(new Miss(), new Spare()));

        //when
        NormalRound normalRound = new NormalRound();
        normalRound.play(new Point(10), 2);

        //then
        Assertions.assertThat(normalRound).isEqualTo(expect);
    }

    @Test
    public void 미스() {
        //given
        NormalRound expect = new NormalRound(new Result(new Miss(), new Miss(new Point(9))));

        //when
        NormalRound normalRound = new NormalRound();
        normalRound.play(new Point(9), 2);

        //then
        Assertions.assertThat(normalRound).isEqualTo(expect);
    }

    @Test
    public void 거터() {
        //given
        NormalRound expect = new NormalRound(new Result(new Miss(), new Gutter()));

        //when
        NormalRound normalRound = new NormalRound();
        normalRound.play(new Point(0), 1);

        //then
        Assertions.assertThat(normalRound).isEqualTo(expect);
    }
}
