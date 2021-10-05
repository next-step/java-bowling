package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import bowling.model.Point;
import bowling.model.State;
import bowling.model.state.FirstBowl;
import bowling.model.state.Miss;
import bowling.model.state.Ready;

public class MissTest {
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
        assertThat(state).isEqualTo(new Miss(new Point(firstPin), new Point(secondPin)));
    }
}
