package bowling.domain.frame;

import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("FrameStatus Test")
class FrameStatusTest {

    @Test
    @DisplayName("Strike 프레임")
    void strikes() {
        //given
        Pins pins = Pins.of(10);

        //when
        FrameStatus frameStatus = pins.getStatus();

        //then
        assertThat(frameStatus).isEqualTo(FrameStatus.STRIKE);
    }

    @Test
    @DisplayName("Spare 프레임")
    void spare() {
        //given
        Pins pins = Pins.of(9, 1);

        //when
        FrameStatus frameStatus = pins.getStatus();

        //then
        assertThat(frameStatus).isEqualTo(FrameStatus.SPARE);
    }

    @Test
    @DisplayName("스트라이크나 스페어가 아닌 프레임")
    void notStrikeAndSpareFrame() {
        //given
        Pins pins = Pins.of(5, 4);

        //when
        FrameStatus frameStatus = pins.getStatus();

        //then
        assertThat(frameStatus).isEqualTo(FrameStatus.NORMAL);
    }
}