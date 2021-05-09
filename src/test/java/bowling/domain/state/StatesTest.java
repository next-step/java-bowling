package bowling.domain.state;

import bowling.domain.state.finish.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatesTest {

    @DisplayName("States 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        States states = States.initialize();

        // then
        assertThat(states).isNotNull();
    }

    @DisplayName("States 인스턴스의 마지막 값 반환 테스트")
    @Test
    void 반환_last() {
        // when
        States states = States.initialize();

        assertAll(
                () -> assertThat(states.last()).isNotNull(),
                () -> assertThat(states.last()).isInstanceOf(State.class)
        );

    }

//    @DisplayName("States 인스턴스에 인스턴스 추가 기능 테스트")
//    @Test
//    void 추가() {
//        // given
//        State strike = Strike.initialize();
//
//        // when
//        States states = States.initialize();
//        states.add(strike);
//
//        // then
//        assertThat(states).isNotNull();
//    }

}