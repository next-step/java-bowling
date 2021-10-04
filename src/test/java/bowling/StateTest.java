package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import bowling.model.*;

public class StateTest {
    @Test
    public void 첫번째_시도에서_핀이_10개가_쓰러지면_스트라이크를_반환한다() throws CannotBowlException {
        //given
        State state = new Ready();
        int firstPin = 10;

        //when
        state = state.bowl(firstPin);

        //then
        assertThat(state).isEqualTo(new Strike());
    }

    @Test
    public void 두번째_시도에서_핀이_10개_쓰러지면_스페어를_반환한다 () throws CannotBowlException {
        //given
        State state = new Ready();
        int firstPin = 1;

        //when
        state = state.bowl(firstPin);

        //then
        assertThat(state).isEqualTo(new FirstBowl(firstPin));


        //given
        int secondPin = 9;

        //when
        state = state.bowl(secondPin);

        //then
        assertThat(state).isEqualTo(new Spare());
    }

    @Test
    public void 두번째_시도에서_핀이_10이하로_쓰러지면_미스를_반환한다 () throws CannotBowlException {
        //given
        State state = new Ready();
        int firstPin = 1;

        //when
        state = state.bowl(firstPin);

        //then
        assertThat(state).isEqualTo(new FirstBowl(firstPin));


        //given
        int secondPin = 2;

        //when
        state = state.bowl(secondPin);

        //then
        assertThat(state).isEqualTo(new Miss(new Point(firstPin + secondPin)));
    }


    @Test
    public void 연속_3번_스트라이크() throws CannotBowlException {
        //given
        State state = new Ready();

        //when
        state = state.bowl(10);

        //then
        assertThat(state).isEqualTo(new Strike());


        //when
        state = state.bowl(10);

        //then
        assertThat(state).isEqualTo(new SecondStrike());


        //when
        state = state.bowl(10);

        //then
        assertThat(state).isEqualTo(new ThirdStrike());
    }

    @Test
    public void 마지막_프레임_스트라이크_스페어() throws CannotBowlException {
        //given
        State state = new Ready();
        int firstPin = 10;

        //when
        state = state.bowl(firstPin);

        //then
        assertThat(state).isEqualTo(new Strike());


        //given
        int secondPin = 1;

        //when
        state = state.bowl(secondPin);

        //then
        assertThat(state).isEqualTo(new SecondBowl(secondPin));


        //given
        int thirdPin = 9;

        //when
        state = state.bowl(thirdPin);

        //then
        assertThat(state).isEqualTo(new ThirdSpare());
    }

    @Test
    public void 마지막_프레임_스페어_스트라이크() throws CannotBowlException {
        //given
        State state = new Ready();
        int firstPin = 1;

        //when
        state = state.bowl(firstPin);

        //then
        assertThat(state).isEqualTo(new FirstBowl(firstPin));


        //given
        int secondPin = 9;

        //when
        state = state.bowl(secondPin);

        //then
        assertThat(state).isEqualTo(new Spare());


        //given
        int thirdPin = 10;

        //when
        state = state.bowl(thirdPin);

        //then
        assertThat(state).isEqualTo(new ThirdStrike());
    }
}
