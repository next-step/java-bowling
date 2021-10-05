package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import bowling.model.State;
import bowling.model.state.*;

public class StrikeTest {
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
}
