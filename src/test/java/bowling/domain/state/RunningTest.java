package bowling.domain.state;

import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class RunningTest {
    @Test
    @DisplayName("스코어가 10이면 진행중 생성 못함")
    void is_running() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Running(new Pin(10)));
    }

    @Test
    @DisplayName("남은 핀 갯수 반환")
    void remain_pins() {
        //given
        Running running = new Running(new Pin(5));
        //then
        assertThat(running.getRemainPins()).isEqualTo(5);
    }

    @Test
    @DisplayName("두번째 볼로 스페어 처리하면 스페어 생성")
    void bowl_spare() {
        //given
        Running running = new Running(new Pin(5));
        //when
        State state = running.bowl(new Pin(5));
        //then
        assertThat(state).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("두번째 볼로 스페어 처리 못하면 미스 생성")
    void bowl_miss() {
        //given
        Running running = new Running(new Pin(5));
        //when
        State state = running.bowl(new Pin(2));
        //then
        assertThat(state).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("종료 상태 아님")
    void is_not_finished() {
        //given
        Running running = new Running(new Pin(5));
        //then
        assertThat(running.isFinish()).isFalse();
    }

}