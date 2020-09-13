package bowling.frame;

import bowling.ball.Ball;
import bowling.frame.Frame;
import bowling.frame.NormalFrame;
import bowling.pin.Pin;
import bowling.pin.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    private Ball ball;
    private Pin pin;
    private List<Pin> pinList;
    private Pins pins;
    private Frame nomalFrame;

    @BeforeEach
    void setUp() {
        pinList = new ArrayList<>();
    }

    @Test
    @DisplayName("게임 단위인 Frame 생성")
    void createFrame() {
        for (int i = 1; i <= 2; i++) {
            ball = Ball.pitch("5", i);
            pin = Pin.of(pinList, ball);
            pins = Pins.eachPitchResult(pinList, pin);
        }
        nomalFrame = NormalFrame.newFrame(1, pins);
        assertThat(nomalFrame.getNumber()).isEqualTo(1);
    }

}
