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

    @Test
    @DisplayName("점수 계산")
    void calculate_point() {
        //given
        NormalFrame frame1 = new NormalFrame();
        frame1.bowl(new Pin(10));
        //when
        //두번째 볼링 8
        NormalFrame frame2 = new NormalFrame(frame1);
        Pin turn2 = new Pin(8);
        frame2.bowl(turn2);
        if (frame1.canAddPoint()) {
            frame1.addPoint(turn2);
        }
        //세번재 볼링 2
        Pin turn3 = new Pin(2);
        frame2.bowl(turn3);
        if(frame1.canAddPoint()){
            frame1.addPoint(turn3);
        }
        //네번째 볼링 8
        NormalFrame frame3 = new NormalFrame(frame2);
        Pin turn4 = new Pin(8);
        frame3.bowl(turn4);
        if(frame1.canAddPoint()){
            frame1.addPoint(turn4);
        }
        if(frame2.canAddPoint()){
            frame2.addPoint(turn4);
        }
        //다섯번째 볼링 1
        Pin turn5 = new Pin(1);
        frame3.bowl(turn5);
        if(frame1.canAddPoint()){
            frame1.addPoint(turn5);
        }
        if(frame2.canAddPoint()){
            frame2.addPoint(turn5);
        }

        //then

        assertAll(
                () -> assertThat(frame1.getPoint()).isEqualTo(20),
                () -> assertThat(frame2.getPoint()).isEqualTo(38),
                () -> assertThat(frame3.getPoint()).isEqualTo(47)
        );
    }

}