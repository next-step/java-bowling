package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Point;
import bowling.domain.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class FinalFrameTest {


    @Test
    @DisplayName("시작")
    void is_started() {
        //given
        FinalFrame finalFrame = new FinalFrame(Point.start());
        //then
        assertAll(
                () -> assertThat(finalFrame.isFinish()).isFalse(),
                () -> assertThat(finalFrame.getRemainPins()).isEqualTo(10),
                () -> assertThat(finalFrame.getState()).isInstanceOf(Started.class),
                () -> assertThat(finalFrame.getState().getRecord()).isEqualTo(List.of())
        );
    }

    @Test
    @DisplayName("진행중")
    void is_running() {
        //given
        FinalFrame finalFrame = new FinalFrame(Point.start());
        //when
        finalFrame.bowl(new Pin(5));
        //then
        assertAll(
                () -> assertThat(finalFrame.isFinish()).isFalse(),
                () -> assertThat(finalFrame.getRemainPins()).isEqualTo(5),
                () -> assertThat(finalFrame.getState()).isInstanceOf(Running.class),
                () -> assertThat(finalFrame.getState().getRecord()).isEqualTo(List.of(5))
        );
    }

    @Test
    @DisplayName("스트라이크")
    void is_strike() {
        //given
        FinalFrame finalFrame = new FinalFrame(Point.start());
        //when
        finalFrame.bowl(new Pin(10));
        //then
        assertAll(
                () -> assertThat(finalFrame.isFinish()).isFalse(),
                () -> assertThat(finalFrame.getRemainPins()).isZero(),
                () -> assertThat(finalFrame.getState()).isInstanceOf(Strike.class),
                () -> assertThat(finalFrame.getState().getRecord()).isEqualTo(List.of(10))
        );
    }

    @Test
    @DisplayName("스페어")
    void is_spare() {
        //given
        FinalFrame finalFrame = new FinalFrame(Point.start());
        //when
        finalFrame.bowl(new Pin(5));
        finalFrame.bowl(new Pin(5));
        //then
        assertAll(
                () -> assertThat(finalFrame.isFinish()).isFalse(),
                () -> assertThat(finalFrame.getRemainPins()).isZero(),
                () -> assertThat(finalFrame.getState()).isInstanceOf(Spare.class),
                () -> assertThat(finalFrame.getState().getRecord()).isEqualTo(List.of(5, 5))
        );
    }

    @Test
    @DisplayName("미스")
    void is_miss() {
        //given
        FinalFrame finalFrame = new FinalFrame(Point.start());
        //when
        finalFrame.bowl(new Pin(5));
        finalFrame.bowl(new Pin(4));
        //then
        assertAll(
                () -> assertThat(finalFrame.isFinish()).isTrue(),
                () -> assertThat(finalFrame.getRemainPins()).isNotZero(),
                () -> assertThat(finalFrame.getState()).isInstanceOf(Miss.class),
                () -> assertThat(finalFrame.getState().getRecord()).isEqualTo(List.of(5, 4))
        );
    }

    @Test
    @DisplayName("상태별 보너스 가능 확인")
    void bonus() {
        //given
        FinalFrame miss = new FinalFrame(Point.start());
        miss.bowl(new Pin(5));
        miss.bowl(new Pin(4));

        FinalFrame spare = new FinalFrame(Point.start());
        spare.bowl(new Pin(5));
        spare.bowl(new Pin(5));

        FinalFrame strike = new FinalFrame(Point.start());
        strike.bowl(new Pin(10));

        //then
        assertAll(
                () -> assertThatThrownBy(() -> miss.bowl(new Pin(5))).isInstanceOf(UnsupportedOperationException.class),
                () -> assertThatNoException().isThrownBy(() -> spare.bowl(new Pin(5))),
                () -> assertThatNoException().isThrownBy(() -> strike.bowl(new Pin(5)))
        );
    }

    @Test
    @DisplayName("레코드에서 보너스 값 확인")
    void record_bonus() {
        //given
        FinalFrame spare = new FinalFrame(Point.start());
        spare.bowl(new Pin(5));
        spare.bowl(new Pin(5));
        spare.bowl(new Pin(5));
        //then
        assertThat(spare.getBonus().get().getValue()).isEqualTo(5);
    }

}