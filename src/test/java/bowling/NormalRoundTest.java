package bowling;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bowling.model.*;
import bowling.model.state.FirstBowl;
import bowling.model.state.Miss;
import bowling.model.state.Ready;
import bowling.model.state.Strike;

public class NormalRoundTest {
    private LinkedList<Score> scores;
    private NormalRound normalRound;

    @BeforeEach
    void setUp() {
        scores = new LinkedList<>();
        normalRound = new NormalRound();
    }

    @Test
    public void 결과가_스트라이크이면_1회투구만_진행한다() throws CannotBowlException {
        //given
        State state = new Strike();
        scores.add(Score.ofStrike());

        //when
        normalRound.bowl(10);

        //then
        assertThat(normalRound).isEqualTo(new NormalRound(state, scores));
    }

    @Test
    public void 투구2회시_프레임은_끝난다() throws CannotBowlException {
        //given
        int firstPin = 2;
        State state = new FirstBowl(firstPin);
        scores.add(new Score(2, 1));
        //when
        normalRound.bowl(firstPin);
        //then
        assertThat(normalRound).isEqualTo(new NormalRound(state, scores));


        //given
        int secondPin = 3;
        state = new Miss(new Point(firstPin), new Point(secondPin));
        scores = new LinkedList<>();
        scores.add(new Score(firstPin + secondPin, 0));
        //when
        normalRound.bowl(secondPin);
        //then
        assertThat(normalRound).isEqualTo(new NormalRound(state, scores));
    }

    @Test
    public void 스트라이크일때_다음라운드_스코어_생성() throws CannotBowlException {
        //given
        scores.add(Score.ofStrike());
        scores.add(new Score());

        //when
        normalRound.bowl(10);

        //then
        assertThat(normalRound.nextScore()).isEqualTo(scores);
    }

    @Test
    public void 스페어일때_다음_라운드_스코어_생성() throws CannotBowlException {
        //given
        scores.add(Score.ofSpare());
        scores.add(new Score());

        //when
        normalRound.bowl(2);
        normalRound.bowl(8);

        //then
        assertThat(normalRound.nextScore()).isEqualTo(scores);
    }

    @Test
    public void 미스일때_다음_라운드_스코어_생성() throws CannotBowlException {
        //given
        scores.add(new Score());

        //when
        normalRound.bowl(1);
        normalRound.bowl(8);
        normalRound.getScore();

        //then
        assertThat(normalRound.nextScore()).isEqualTo(scores);
    }

    @Test
    public void 일반라운드는_다음라운드를_생성한다() throws CannotBowlException {
        //given
        State state = new Ready();
        scores.add(Score.ofStrike());
        scores.add(new Score());

        //when
        normalRound.bowl(10);

        //then
        assertThat(normalRound.next(1)).isEqualTo(new NormalRound(state, scores));
    }

    @Test
    public void 마지막_라운드_전_라운드는_파이널_라운드를_생성한다() throws CannotBowlException {
        //given
        State state = new Ready();
        scores.add(Score.ofStrike());
        scores.add(new Score());

        //when
        normalRound.bowl(10);

        //then
        assertThat(normalRound.next(9)).isEqualTo(new FinalRound(state, scores));
    }
}
