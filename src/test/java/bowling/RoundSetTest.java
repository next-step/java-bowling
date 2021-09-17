package bowling;

import bowling.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class RoundSetTest {
    @Test
    public void 일반라운드_스트라이크() {
        //given
        int firstPoint = 10;
        Round round1 = new NormalRound(new Result(new Miss(), new Strike()));
        Round round2 = new NormalRound(new Result(new Strike(), new Miss()));
        RoundSet expect = new RoundSet(0, Arrays.asList(round1, round2));

        //when
        RoundSet roundSet = new RoundSet(1);
        roundSet.play(firstPoint);

        //then
        assertThat(roundSet).isEqualTo(expect);
    }

    @Test
    public void 일반라운드_스페어() {
        //given
        int firstPoint = 1;
        Round round1 = new NormalRound(new Result(new Miss(), new Miss(new Point(firstPoint))));
        Round round2 = new NormalRound(new Result(new Miss(new Point(firstPoint)), new Miss()));
        RoundSet expect = new RoundSet(firstPoint, Arrays.asList(round1, round2));

        //when
        RoundSet roundSet = new RoundSet(1);
        roundSet.play(firstPoint);
        //then
        assertThat(roundSet).isEqualTo(expect);

        //given
        int secondPoint = 9;
        round2 = new NormalRound(new Result(new Miss(new Point(firstPoint)), new Spare()));
        Round round3 = new NormalRound(new Result(new Spare(), new Miss()));
        expect = new RoundSet(0, Arrays.asList(round1, round2, round3));

        //when
        roundSet.play(secondPoint);
        //then
        assertThat(roundSet).isEqualTo(expect);
    }

    @Test
    public void 일반라운드_거터() {
        //given
        int firstPoint = 0;
        Round round1 = new NormalRound(new Result(new Miss(), new Gutter()));
        Round round2 = new NormalRound(new Result(new Gutter(), new Miss()));
        RoundSet expect = new RoundSet(firstPoint, Arrays.asList(round1, round2));

        //when
        RoundSet roundSet = new RoundSet(1);
        roundSet.play(firstPoint);
        //then
        assertThat(roundSet).isEqualTo(expect);

        //given
        int secondPoint = 0;
        round2 = new NormalRound(new Result(new Gutter(), new Gutter()));
        Round round3 = new NormalRound(new Result(new Gutter(), new Miss()));
        expect = new RoundSet(0, Arrays.asList(round1, round2, round3));

        //when
        roundSet.play(secondPoint);
        //then
        assertThat(roundSet).isEqualTo(expect);
    }

    @Test
    public void 일반라운드_미스() {
        //given
        int firstPoint = 2;
        Round round1 = new NormalRound(new Result(new Miss(), new Miss(new Point(firstPoint))));
        Round round2 = new NormalRound(new Result(new Miss(new Point(firstPoint)), new Miss()));
        RoundSet expect = new RoundSet(firstPoint, Arrays.asList(round1, round2));

        //when
        RoundSet roundSet = new RoundSet(1);
        roundSet.play(firstPoint);
        //then
        assertThat(roundSet).isEqualTo(expect);

        //given
        int secondPoint = 3;
        round2 = new NormalRound(new Result(new Miss(new Point(firstPoint)), new Miss(new Point(5))));
        Round round3 = new NormalRound(new Result(new Miss(new Point(5)), new Miss()));
        expect = new RoundSet(5, Arrays.asList(round1, round2, round3));

        //when
        roundSet.play(secondPoint);
        //then
        assertThat(roundSet).isEqualTo(expect);
    }
}
