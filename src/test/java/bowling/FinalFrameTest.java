package bowling;

import bowling.model.*;
import bowling.model.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.*;

import static bowling.model.Score.*;

public class FinalFrameTest {
    private LinkedList<Score> scores;
    private FinalFrame finalFrame;

    @BeforeEach
    void setUp() {
        scores = new LinkedList<>();
        finalFrame = new FinalFrame();
    }

    @Test
    public void 스트라이크나_스페어가_아니면_프레임은_끝난다() throws CannotBowlException {
        //given
        int firstPin = 2;
        State state = new FirstBowl(firstPin);
        scores.add(new Score(firstPin, 1));
        //when
        finalFrame.bowl(firstPin);
        //then
        assertThat(finalFrame).isEqualTo(new FinalFrame(state, scores));


        //given
        int secondPin = 3;
        state = new Miss(new Pin(firstPin), new Pin(secondPin));
        scores = new LinkedList<>();
        scores.add(new Score(firstPin + secondPin, 0));
        //when
        finalFrame.bowl(secondPin);
        //then
        assertThat(finalFrame).isEqualTo(new FinalFrame(state, scores));
    }

    @Test
    public void 스트라이크_보너스프레임이_주어진다() throws CannotBowlException {
        //given
        int firstPin = 10;
        State state = new Strike();
        scores.add(ofStrike());
        //when
        finalFrame.bowl(firstPin);
        //then
        assertThat(finalFrame).isEqualTo(new FinalFrame(state, scores));


        //given
        int secondPin = 10;
        state = new SecondStrike();
        scores = new LinkedList<>();
        scores.add(new Score(20, 1));
        //when
        finalFrame.bowl(secondPin);
        //then
        assertThat(finalFrame).isEqualTo(new FinalFrame(state, scores));


        //given
        int thirdPin = 10;
        state = new ThirdStrike();
        scores = new LinkedList<>();
        scores.add(new Score(30, 0));
        //when
        finalFrame.bowl(thirdPin);
        //then
        assertThat(finalFrame).isEqualTo(new FinalFrame(state, scores));
    }


    @Test
    public void 스페어_보너스프레임이_주어진다() throws CannotBowlException {
        //given
        int firstPin = 9;
        State state = new FirstBowl(firstPin);
        scores.add(new Score(firstPin, 1));
        //when
        finalFrame.bowl(firstPin);
        //then
        assertThat(finalFrame).isEqualTo(new FinalFrame(state, scores));


        //given
        int secondPin = 1;
        state = new Spare();
        scores = new LinkedList<>();
        scores.add(ofSpare());
        //when
        finalFrame.bowl(secondPin);
        //then
        assertThat(finalFrame).isEqualTo(new FinalFrame(state, scores));


        //given
        int thirdPin = 10;
        state = new ThirdStrike();
        scores = new LinkedList<>();
        scores.add(new Score(20, 0));
        //when
        finalFrame.bowl(thirdPin);
        //then
        assertThat(finalFrame).isEqualTo(new FinalFrame(state, scores));
    }

}
