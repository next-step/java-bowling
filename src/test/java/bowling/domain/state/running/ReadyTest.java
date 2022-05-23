package bowling.domain.state.running;

import bowling.domain.Pins;
import bowling.domain.state.State;
import bowling.domain.state.finish.Strike;
import bowling.domain.state.running.FirstBowl;
import bowling.domain.state.running.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    private final State ready = Ready.create();

    @Test
    @DisplayName("생성을 확인한다")
    void create() {
        assertThat(ready).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("빈 심볼을 반환한다")
    void getSymbol() {
        assertThat(ready.getSymbol()).isEqualTo("");
    }

    @Test
    @DisplayName("프레임을 종료된 상태이다")
    void isFrameEnd() {
        assertThat(ready.isFrameEnd()).isFalse();
    }

    @Test
    @DisplayName("첫 시도에 모든 핀을 제거할 경우 스트라이크를 반환한다")
    void createStrike() {
        //when, then
        assertThat(ready.pitch(Pins.create(10))).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("첫 시도에 모든핀을 제거하지 않으면 첫번쨰 볼을 반환한다")
    void createFirstBowl() {
        //given
        Pins pitchedPins = Pins.create(9);

        //when, then
        assertThat(ready.pitch(pitchedPins)).isInstanceOf(FirstBowl.class);
    }

}