package bowling;

import bowling.model.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import static bowling.model.Score.*;

public class FinalRoundTest {
    @Test
    public void 스트라이크나_스페어가_아니면_프레임은_끝난다() throws CannotBowlException {
        //given
        int firstPin = 2;
        State state = new FirstBowl(firstPin);
        LinkedList<Score> scores = new LinkedList<>();
        scores.add(new Score(2, 1));

        //when
        FinalRound finalRound = new FinalRound();
        finalRound.bowl(firstPin);

        //then
        assertThat(finalRound).isEqualTo(new FinalRound(state, scores));
        assertFalse(finalRound.isFinish());


        //given
        int secondPin = 3;
        int totalPin = firstPin + secondPin;
        state = new Miss(new Point(totalPin));
        scores = new LinkedList<>();
        scores.add(new Score(totalPin, 0));

        //when
        finalRound.bowl(secondPin);

        //then
        assertThat(finalRound).isEqualTo(new FinalRound(state, scores));
        assertTrue(finalRound.isFinish());
    }

    @Test
    public void 스트라이크_보너스프레임이_주어진다() throws CannotBowlException {
        //given
        int firstPin = 10;
        State state = new Strike();
        LinkedList<Score> scores = new LinkedList<>();
        scores.add(ofStrike());

        //when
        FinalRound finalRound = new FinalRound();
        finalRound.bowl(firstPin);

        //then
        assertThat(finalRound).isEqualTo(new FinalRound(state, scores));


        //given
        int secondPin = 10;
        state = new SecondStrike();
        scores = new LinkedList<>();
        scores.add(new Score(20, 1));

        //when
        finalRound.bowl(secondPin);

        //then
        assertThat(finalRound).isEqualTo(new FinalRound(state, scores));

        //given
        int thirdPin = 10;
        state = new ThirdStrike();
        scores = new LinkedList<>();
        scores.add(new Score(30, 0));

        //when
        finalRound.bowl(thirdPin);

        //then
        assertThat(finalRound).isEqualTo(new FinalRound(state, scores));
    }


    @Test
    public void 스페어_보너스프레임이_주어진다() throws CannotBowlException {
        //given
        int firstPin = 9;
        State state = new FirstBowl(firstPin);
        LinkedList<Score> scores = new LinkedList<>();
        scores.add(new Score(9, 1));

        //when
        FinalRound finalRound = new FinalRound();
        finalRound.bowl(firstPin);

        //then
        assertThat(finalRound).isEqualTo(new FinalRound(state, scores));


        //given
        int secondPin = 1;
        state = new Spare();
        scores = new LinkedList<>();
        scores.add(ofSpare());

        //when
        finalRound.bowl(secondPin);

        //then
        assertThat(finalRound).isEqualTo(new FinalRound(state, scores));


        //given
        int thirdPin = 10;
        state = new ThirdStrike();
        scores = new LinkedList<>();
        scores.add(new Score(20, 0));

        //when
        finalRound.bowl(thirdPin);

        //then
        assertThat(finalRound).isEqualTo(new FinalRound(state, scores));
    }

}
