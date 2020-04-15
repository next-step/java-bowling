package bowling.domain.frame.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ReadyTest {

    @DisplayName("ready 상태에서 핀을 10개 치면 strike 를 반환한다")
    @Test
    public void bowl_success_strike() throws Exception {
        //given
        Ready ready = new Ready();

        //when
        State bowl = ready.bowl(10);

        //then
        assertThat(bowl).isInstanceOf(Strike.class);
    }

    @DisplayName("ready 상태에서 친 핀이 10개가 아니면 FirstBowl 을 반환한다")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9})
    public void bowl_success_firstBowl(int pinCount) throws Exception {
        //given
        Ready ready = new Ready();

        //when
        State bowl = ready.bowl(pinCount);

        //then
        assertThat(bowl).isInstanceOf(FirstBowl.class);
    }

    @DisplayName("ready 상태는 마무리 상태가 아니다")
    @Test
    public void isFinish_success() throws Exception {
        //given
        Ready ready = new Ready();

        //then
        assertFalse(ready.isFinish());
    }
}
