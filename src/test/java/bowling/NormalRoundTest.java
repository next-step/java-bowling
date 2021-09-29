package bowling;

import bowling.model.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class NormalRoundTest {
    @Test
    public void 결과가_스트라이크이면_1회투구만_진행한다() throws CannotBowlException {
        //given
        State state = new Strike();
        LinkedList<Score> scores = new LinkedList<>();
        scores.add(Score.ofStrike());

        //when
        NormalRound normalRound = new NormalRound();
        normalRound.bowl(10);

        //then
        assertThat(normalRound).isEqualTo(new NormalRound(state, scores));
        assertTrue(normalRound.isFinish());
    }

    @Test
    public void 투구2회시_프레임은_끝난다() throws CannotBowlException {
        //given
        int firstPin = 2;
        State state = new FirstBowl(firstPin);
        LinkedList<Score> scores = new LinkedList<>();
        scores.add(new Score(2, 1));

        //when
        NormalRound normalRound = new NormalRound();
        normalRound.bowl(firstPin);

        //then
        assertThat(normalRound).isEqualTo(new NormalRound(state, scores));
        assertFalse(normalRound.isFinish());


        //given
        int secondPin = 3;
        int totalPin = firstPin + secondPin;
        state = new Miss(new Point(totalPin));
        scores = new LinkedList<>();
        scores.add(new Score(totalPin, 0));


        //when
        normalRound.bowl(secondPin);

        //then
        assertThat(normalRound).isEqualTo(new NormalRound(state, scores));
        assertTrue(normalRound.isFinish());
    }

    @Test
    public void 스트라이크일때_다음라운드_스코어_생성() throws CannotBowlException {
        //given
        LinkedList<Score> expect = new LinkedList<>();
        expect.add(Score.ofStrike());
        expect.add(new Score());

        //when
        NormalRound normalRound = new NormalRound();
        normalRound.bowl(10);

        //then
        assertThat(normalRound.nextScore()).isEqualTo(expect);
    }

    @Test
    public void 스페어일때_다음_라운드_스코어_생성() throws CannotBowlException {
        //given
        LinkedList<Score> expect = new LinkedList<>();
        expect.add(Score.ofSpare());
        expect.add(new Score());

        //when
        NormalRound normalRound = new NormalRound();
        normalRound.bowl(2);
        normalRound.bowl(8);

        //then
        assertThat(normalRound.nextScore()).isEqualTo(expect);
    }

    @Test
    public void 미스일때_다음_라운드_스코어_생성() throws CannotBowlException {
        //given
        LinkedList<Score> expect = new LinkedList<>();
        expect.add(new Score());

        //when
        NormalRound normalRound = new NormalRound();
        normalRound.bowl(1);
        normalRound.bowl(8);
        normalRound.getScore();
        LinkedList<Score> actual = normalRound.nextScore();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    public void 일반라운드는_다음라운드를_생성한다() throws CannotBowlException {
        //given
        LinkedList<Score> scores = new LinkedList<>();
        scores.add(Score.ofStrike());
        scores.add(new Score());
        State state = new Ready();

        //when
        NormalRound normalRound = new NormalRound();
        normalRound.bowl(10);
        Round expect = normalRound.next(1);

        //then
        assertThat(expect).isEqualTo(new NormalRound(state, scores));
    }

    @Test
    public void 마지막_라운드_전_라운드는_파이널_라운드를_생성한다() throws CannotBowlException {
        //given
        LinkedList<Score> scores = new LinkedList<>();
        scores.add(Score.ofStrike());
        scores.add(new Score());
        State state = new Ready();

        //when
        NormalRound normalRound = new NormalRound();
        normalRound.bowl(10);
        Round expect = normalRound.next(10);

        //then
        assertThat(expect).isEqualTo(new FinalRound(state, scores));
    }
}
