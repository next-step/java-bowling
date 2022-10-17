package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.state.Miss;
import bowling.domain.state.Running;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class NormalFrameTest {

    @Test
    @DisplayName("첫 시도에 스트라이크면 프레임 종료")
    void is_finish_when_strike() {
        //given
        NormalFrame normalFrame = new NormalFrame();
        //when
        normalFrame.bowl(new Pin(10));
        //then
        assertAll(
                () -> assertThat(normalFrame.isFinish()).isTrue(),
                () -> assertThat(normalFrame.getRemainPins()).isZero(),
                () -> assertThat(normalFrame.getState()).isInstanceOf(Strike.class),
                () -> assertThat(normalFrame.getState().getRecord()).isEqualTo(List.of(10))
        );
    }

    @Test
    @DisplayName("첫 시도에 스트라이크가 아니면 프레임 종료 아님 && 남은 핀 개수")
    void is_not_finish_and_remain_pins() {
        //given
        NormalFrame normalFrame = new NormalFrame();
        //when
        normalFrame.bowl(new Pin(5));
        //then
        assertAll(
                () -> assertThat(normalFrame.isFinish()).isFalse(),
                () -> assertThat(normalFrame.getRemainPins()).isEqualTo(5),
                () -> assertThat(normalFrame.getState()).isInstanceOf(Running.class),
                () -> assertThat(normalFrame.getState().getRecord()).isEqualTo(List.of(5))
        );
    }

    @Test
    @DisplayName("스페어 처리하면 프레임 종료")
    void is_finish_when_spare() {
        //given
        NormalFrame normalFrame = new NormalFrame();
        //when
        normalFrame.bowl(new Pin(5));
        normalFrame.bowl(new Pin(5));
        //then
        assertAll(
                () -> assertThat(normalFrame.isFinish()).isTrue(),
                () -> assertThat(normalFrame.getRemainPins()).isZero(),
                () -> assertThat(normalFrame.getState()).isInstanceOf(Spare.class),
                () -> assertThat(normalFrame.getState().getRecord()).isEqualTo(List.of(5, 5))
        );
    }

    @Test
    @DisplayName("두번 던지면 프레임 종료")
    void is_finish_when_miss() {
        //given
        NormalFrame normalFrame = new NormalFrame();
        //when
        normalFrame.bowl(new Pin(5));
        normalFrame.bowl(new Pin(2));
        //then
        assertAll(
                () -> assertThat(normalFrame.isFinish()).isTrue(),
                () -> assertThat(normalFrame.getRemainPins()).isEqualTo(3),
                () -> assertThat(normalFrame.getState()).isInstanceOf(Miss.class),
                () -> assertThat(normalFrame.getState().getRecord()).isEqualTo(List.of(5, 2))
        );
    }

    @Test
    @DisplayName("한번 던지기")
    void bowl_1() {
        //given
        NormalFrame normalFrame = new NormalFrame();
        //when
        normalFrame.bowl(new Pin(5));
        //then
        assertAll(
                () -> assertThat(normalFrame.isFinish()).isFalse(),
                () -> assertThat(normalFrame.getRemainPins()).isEqualTo(5),
                () -> assertThat(normalFrame.getState()).isInstanceOf(Running.class),
                () -> assertThat(normalFrame.getState().getRecord()).isEqualTo(List.of(5))
        );
    }

}