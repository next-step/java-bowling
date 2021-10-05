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

public class NormalFrameTest {
    private LinkedList<Score> scores;
    private NormalFrame normalFrame;

    @BeforeEach
    void setUp() {
        scores = new LinkedList<>();
        normalFrame = new NormalFrame();
    }

    @Test
    public void 결과가_스트라이크이면_1회투구만_진행한다() throws CannotBowlException {
        //given
        State state = new Strike();
        scores.add(Score.ofStrike());

        //when
        normalFrame.bowl(10);

        //then
        assertThat(normalFrame).isEqualTo(new NormalFrame(state, scores));
    }

    @Test
    public void 투구2회시_프레임은_끝난다() throws CannotBowlException {
        //given
        int firstPin = 2;
        State state = new FirstBowl(firstPin);
        scores.add(new Score(2, 1));
        //when
        normalFrame.bowl(firstPin);
        //then
        assertThat(normalFrame).isEqualTo(new NormalFrame(state, scores));


        //given
        int secondPin = 3;
        state = new Miss(new Pin(firstPin), new Pin(secondPin));
        scores = new LinkedList<>();
        scores.add(new Score(firstPin + secondPin, 0));
        //when
        normalFrame.bowl(secondPin);
        //then
        assertThat(normalFrame).isEqualTo(new NormalFrame(state, scores));
    }

    @Test
    public void 스트라이크일때_다음라운드_스코어_생성() throws CannotBowlException {
        //given
        scores.add(Score.ofStrike());
        scores.add(new Score());

        //when
        normalFrame.bowl(10);

        //then
        assertThat(normalFrame.nextScore()).isEqualTo(scores);
    }

    @Test
    public void 스페어일때_다음_라운드_스코어_생성() throws CannotBowlException {
        //given
        scores.add(Score.ofSpare());
        scores.add(new Score());

        //when
        normalFrame.bowl(2);
        normalFrame.bowl(8);

        //then
        assertThat(normalFrame.nextScore()).isEqualTo(scores);
    }

    @Test
    public void 미스일때_다음_라운드_스코어_생성() throws CannotBowlException {
        //given
        scores.add(new Score());

        //when
        normalFrame.bowl(1);
        normalFrame.bowl(8);
        normalFrame.getScore();

        //then
        assertThat(normalFrame.nextScore()).isEqualTo(scores);
    }

    @Test
    public void 일반라운드는_다음라운드를_생성한다() throws CannotBowlException {
        //given
        State state = new Ready();
        scores.add(Score.ofStrike());
        scores.add(new Score());

        //when
        normalFrame.bowl(10);

        //then
        assertThat(normalFrame.next(1)).isEqualTo(new NormalFrame(state, scores));
    }

    @Test
    public void 마지막_라운드_전_라운드는_파이널_라운드를_생성한다() throws CannotBowlException {
        //given
        State state = new Ready();
        scores.add(Score.ofStrike());
        scores.add(new Score());

        //when
        normalFrame.bowl(10);

        //then
        assertThat(normalFrame.next(9)).isEqualTo(new FinalFrame(state, scores));
    }
}
