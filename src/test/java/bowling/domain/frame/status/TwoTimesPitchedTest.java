package bowling.domain.frame.status;

import bowling.domain.frame.DownedPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TwoTimesPitchedTest {

    @Test
    @DisplayName("스페어 출력 테스트(Gutter X)")
    void spareDescriptionWithoutGutter() {
        DownedPin firstPitch = DownedPin.fromNumber(5);
        DownedPin secondPitch = firstPitch.fromPreviousPitch(5);
        TwoTimesPitched status = new TwoTimesPitched(firstPitch, secondPitch);

        assertThat(status.getDescription())
                .isEqualTo("5 | /");
    }

    @Test
    @DisplayName("스페어 출력 테스트(Gutter O)")
    void spareDescriptionWithGutter() {
        DownedPin firstPitch = DownedPin.fromNumber(0);
        DownedPin secondPitch = firstPitch.fromPreviousPitch(10);
        TwoTimesPitched status = new TwoTimesPitched(firstPitch, secondPitch);

        assertThat(status.getDescription())
                .isEqualTo("- | /");

    }

    @Test
    @DisplayName("미스 출력 테스트(Gutter X)")
    void missDescriptionWithoutGutter() {
        DownedPin firstPitch = DownedPin.fromNumber(5);
        DownedPin secondPitch = firstPitch.fromPreviousPitch(4);
        TwoTimesPitched status = new TwoTimesPitched(firstPitch, secondPitch);

        assertThat(status.getDescription())
                .isEqualTo("5 | 4");
    }

    @Test
    @DisplayName("미스 출력 테스트(Gutter O)")
    void missDescriptionWithGutter() {
        DownedPin firstPitch = DownedPin.fromNumber(0);
        DownedPin secondPitch = firstPitch.fromPreviousPitch(0);
        TwoTimesPitched status = new TwoTimesPitched(firstPitch, secondPitch);

        assertThat(status.getDescription())
                .isEqualTo("- | -");
    }
}
