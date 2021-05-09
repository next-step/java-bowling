package bowling.domain.state;

import bowling.domain.state.finish.Strike;
import bowling.domain.state.running.Ready;
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

    @DisplayName("States 인스턴스에 인스턴스 추가 기능 테스트")
    @Test
    void 추가() {
        // given
        State expected = Strike.initialize();

        // when
        States states = States.initialize();
        states.add(expected);
        State actual = states.last();

        // then
        assertAll(
                () -> assertThat(actual).isSameAs(expected),
                () -> assertThat(actual).isEqualTo(expected)
        );

    }

    @DisplayName("States 인스턴스에 마지막 요소를 삭제하는 기능 테스트")
    @Test
    void 삭제() {
        // given
        State expected = Strike.initialize();
        State removed = Ready.initialize().bowl(Pins.valueOf(9));

        // when
        States states = States.initialize();
        states.add(expected);
        states.add(removed);
        states.remove();
        State actual = states.last();

        // then
        assertAll(
                () -> assertThat(actual).isSameAs(expected),
                () -> assertThat(actual).isEqualTo(expected)
        );

    }

//    @DisplayName("States 인스턴스가 알맞는 Score 반환 기능 테스트")
//    @Test
//    void 반환_score() {
//        // given
//        State first = Ready.initialize().bowl(Pins.valueOf(9));
//        State second = first.bowl(Pins.valueOf(0));
//
//        // when
//        States states = States.initialize();
//        states.add(first);
//        states.add(second);
//        State actual = states.last();
//
//        // then
//        assertAll(
//                () -> assertThat(actual).isSameAs(expected),
//                () -> assertThat(actual).isEqualTo(expected)
//        );
//
//    }

}