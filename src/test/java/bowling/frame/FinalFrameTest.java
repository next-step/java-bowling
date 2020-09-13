package bowling.frame;

import bowling.pin.Pin;
import bowling.pin.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

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
            pins = Pins.eachPitchResult(pinList, "5", i);
        }
        finalFrame = FinalFrame.finalFrame(pins);
        assertThat(finalFrame.getNumber()).isEqualTo(10);
    }

}
