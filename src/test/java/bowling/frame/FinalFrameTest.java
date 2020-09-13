package bowling.frame;

import bowling.ball.Ball;
import bowling.frame.FinalFrame;
import bowling.frame.Frame;
import bowling.pin.Pin;
import bowling.pin.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    private Ball ball;
    private Pin pin;
    private List<Pin> pinList;
    private Pins pins;
    private Frame finalFrame;

    @BeforeEach
    void setUp() {
        pinList = new ArrayList<>();
    }

    @Test
    @DisplayName("마지막 Frame 생성")
    void create() {
        for (int i = 1; i <= 2; i++) {
            ball = Ball.pitch("5", i);
            pin = Pin.of(pinList, ball);
            pins = Pins.eachPitchResult(pinList, pin);
        }
        finalFrame = FinalFrame.finalFrame(pins);
        assertThat(finalFrame.getNumber()).isEqualTo(10);
    }

}
