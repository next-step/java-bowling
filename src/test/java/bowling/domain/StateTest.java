package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StateTest {

//    @DisplayName("생성 테스트")
//    @Test
//    void create(){
//        State state = new State();
//
//    }
    @DisplayName("bowl 테스트")
    @Test
    void bowlTest(){
        State state = new State();

        state.bowl(4);

        assertThat(state.getFirstPin()).isEqualTo(Pins.of(4));
    }

    @DisplayName("안쳤을 때는 False, 한번 만 첬을 때는 False, 두번 다 쳤을 때는 True 테스트")
    @Test
    void isNormalFinish(){
        State state = new State();

        assertThat(state.isFinish()).isFalse();

        state.bowl(4);
        assertThat(state.isFinish()).isFalse();

        state.bowl(3);
        assertThat(state.isFinish()).isTrue();
    }

    @DisplayName("스트라이크 쳤을 때는 바로 True")
    @Test
    void isStrikeFinish(){
        State state = new State();

        state.bowl(10);
        assertThat(state.isFinish()).isTrue();

    }
}
