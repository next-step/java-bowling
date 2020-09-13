package bowling.frame;

import bowling.pin.Pin;
import bowling.pin.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

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
            pins = Pins.eachPitchResult(pinList, "5", i);
        }
        nomalFrame = NormalFrame.newFrame(1, pins);
        assertThat(nomalFrame.getNumber()).isEqualTo(1);
    }

}
