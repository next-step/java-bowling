package bowling.domain.state;

import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StartedTest {

    @Test
    @DisplayName("첫번째 스트라이크면 스트라이크 생성")
    void bowl_strike() {
        //given
        Started started = new Started();
        //when
        State state = started.bowl(new Pin(10));
        //then
        assertThat(state).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("첫번째 볼 스트라이크 아니면 진행중 샹송")
    void bowl_not_strike() {
        //given
        Started started = new Started();
        //when
        State state = started.bowl(new Pin(5));
        //then
        assertThat(state).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("종료 상태 아님")
    void is_not_finished() {
        //given
        Started started = new Started();
        //then
        assertThat(started.isFinish()).isFalse();
    }

    @Test
    @DisplayName("남은 핀 개수는 10")
    void remain_pins_10() {
        //given
        Started started = new Started();
        //then
        assertThat(started.getRemainPins()).isEqualTo(10);
    }
}