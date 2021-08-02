package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static bowling.domain.Fixture.DOWNED_PINS_5;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("종료 상태 테스트")
class EndStateTest {

    @DisplayName("종료 상태에서는 핀을 쓰러뜨릴 수 없으며, 예외를 발생 시킨다")
    @Test
    void downPinsException() {
        SomeEndState someEndState = new SomeEndState();

        assertThatThrownBy(() -> someEndState.downPins(DOWNED_PINS_5)).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("기본적으로 종료 상태는 끝이다")
    @Test
    void isEnd() {
        SomeEndState someEndState = new SomeEndState();

        assertThat(someEndState.isEnd()).isTrue();
    }

    static class SomeEndState extends EndState {
        @Override
        public List<Integer> getDownedPins() {
            return null;
        }
    }
}
