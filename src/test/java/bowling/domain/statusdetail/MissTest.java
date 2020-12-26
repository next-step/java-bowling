package bowling.domain.statusdetail;

import bowling.domain.frame.DownedPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MissTest {

    @Test
    @DisplayName("초기 상태의 출력 상태")
    void testMiss() {
        List<DownedPin> pins = new ArrayList<>();

        assertThat(new Miss().interpretFrame(pins))
                .isEqualTo("       ");
    }

    @Test
    @DisplayName("1회 투구 후 출력 상태")
    void testStrike() {
        List<DownedPin> pins = new ArrayList<>();
        pins.add(DownedPin.fromNumber(6));

        assertThat(new Miss().interpretFrame(pins))
                .isEqualTo(" 6 |   ");
    }

    @Test
    @DisplayName("2회 투구 후 출력 상태")
    void testTwoPitch() {
        List<DownedPin> pins = new ArrayList<>();
        DownedPin firstPitch = DownedPin.fromNumber(3);
        DownedPin secondPitch = firstPitch.fromSubordinateTry(DownedPin.fromNumber(4));

        pins.add(firstPitch);
        pins.add(secondPitch);

        assertThat(new Miss().interpretFrame(pins))
                .isEqualTo(" 3 | 4 ");
    }

    @Test
    @DisplayName("거터가 포함된 출력")
    void TestGutter() {
        List<DownedPin> pins = new ArrayList<>();
        DownedPin firstPitch = DownedPin.fromNumber(0);
        DownedPin secondPitch = firstPitch.fromSubordinateTry(DownedPin.fromNumber(4));

        pins.add(firstPitch);
        pins.add(secondPitch);

        assertThat(new Miss().interpretFrame(pins))
                .isEqualTo(" - | 4 ");
    }
}
