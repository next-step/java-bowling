package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import bowling.model.State;
import bowling.model.state.FirstBowl;
import bowling.model.state.Ready;
import bowling.model.state.Spare;
import bowling.model.state.ThirdStrike;

public class SpareTest {
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
