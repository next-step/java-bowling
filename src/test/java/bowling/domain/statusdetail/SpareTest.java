package bowling.domain.statusdetail;

import bowling.domain.frame.DownedPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class SpareTest {

    @Test
    @DisplayName("일반적인 상태")
    void testHasNormal() {
        List<DownedPin> pins = new ArrayList<>();
        DownedPin firstPitch = DownedPin.fromNumber(3);
        DownedPin secondPitch = firstPitch.fromSubordinateTry(DownedPin.fromNumber(7));

        pins.add(firstPitch);
        pins.add(secondPitch);

        assertThat(new Spare().interpretFrame(pins))
                .isEqualTo(" 3 | / ");
    }

    @Test
    @DisplayName("거터를 포함한 상태")
    void testHasGutter() {
        List<DownedPin> pins = new ArrayList<>();
        DownedPin firstPitch = DownedPin.fromNumber(0);
        DownedPin secondPitch = firstPitch.fromSubordinateTry(DownedPin.fromNumber(10));

        pins.add(firstPitch);
        pins.add(secondPitch);

        assertThat(new Spare().interpretFrame(pins))
                .isEqualTo(" - | / ");
    }
}
