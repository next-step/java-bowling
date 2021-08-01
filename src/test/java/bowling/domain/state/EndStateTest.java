package bowling.domain.state;

import bowling.domain.pin.DownedPins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("종료 상태 테스트")
class EndStateTest {

    @DisplayName("종료 상태에서는 핀을 쓰러뜨릴 수 없으며, 예외를 발생 시킨다")
    @Test
    void downPinsException() {
        SomeEndState someEndState = new SomeEndState();

        assertThatThrownBy(() -> someEndState.downPins(DownedPins.from(5))).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("기본적으로 종료 상태는 끝이다")
    @Test
    void isEnd() {
        SomeEndState someEndState = new SomeEndState();

        assertThat(someEndState.isEnd()).isTrue();
    }

    class SomeEndState extends EndState {}
}
