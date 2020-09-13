package bowling.frame;

import bowling.ball.Ball;
import bowling.global.exception.OutOfFrameRangeException;
import bowling.pin.Pin;
import bowling.pin.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class FramesTest {

    private Ball ball;
    private Pin pin;
    private List<Pin> pinList;
    private Pins pins;
    private List<Frame> frameList;

    @BeforeEach
    void setUp() {
        frameList = new ArrayList<>();
        pinList = new ArrayList<>();
    }

    @Test
    @DisplayName("1개의 프레임 생성 2개의 Pins 보유")
    void createFrames() {
        for (int i = 1; i <= 2; i++) {
            ball = Ball.pitch("5", i);
            pin = Pin.of(pinList, ball);
            pins = Pins.eachPitchResult(pinList, pin);
        }
        Frames frames = Frames.nextFrame(frameList, pins);
        assertThat(frames.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("허용(1 ~ 9)되지 않은 프레임 번호가 들어오면 Exception 발생 - 성공 후 접근제어자 private로 변경")
    void invalidNomalFrameNumberException() {
        assertThatExceptionOfType(OutOfFrameRangeException.class)
                .isThrownBy(() -> {
                    ball = Ball.pitch("5", 1);
                    pin = Pin.of(pinList, ball);
//                    Frames.createFrame(11, Pins.eachPitchResult(pinList, pin));
                });
    }

}
