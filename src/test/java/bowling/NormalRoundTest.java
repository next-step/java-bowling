package bowling;

import bowling.model.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class NormalRoundTest {
    @Test
    public void 결과가_스트라이크이면_1회투구만_진행한다() throws CannotBowlException {
        //given
        State state = new Strike();
        Queue<Score> scores = new LinkedList<>();
        scores.add(new Score(10, 1));

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
        Queue<Score> scores = new LinkedList<>();
        scores.add(new Score(2, 1));

        //when
        NormalRound normalRound = new NormalRound();
        normalRound.bowl(firstPin);

        //then
        assertThat(normalRound).isEqualTo(new NormalRound(state, scores));
        assertFalse(normalRound.isFinish());


        //given
        int secondPin = 3;
        state = new Miss(new Point(firstPin + secondPin));
        scores = new LinkedList<>();

        //when
        normalRound.bowl(secondPin);

        //then
        assertThat(normalRound).isEqualTo(new NormalRound(state, scores));
        assertTrue(normalRound.isFinish());
    }
}
