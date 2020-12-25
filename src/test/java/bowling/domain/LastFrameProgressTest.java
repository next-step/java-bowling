package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LastFrameProgressTest {

    @Test
    @DisplayName("0회 피치 케이스")
    void testInitialPitch() {
        List<NormalFrame> frames = new ArrayList<>();

        assertThat(LastFrameProgress.getProgress(frames))
                .isEqualTo(LastFrameProgress.ON_FIRST_PITCH);
    }

    @Test
    @DisplayName("1회 피치 케이스")
    void testFirstPitch() {
        List<NormalFrame> frames = new ArrayList<>();
        NormalFrame first = new NormalFrame();
        first.record(DownedPin.fromNumber(5));

        assertThat(LastFrameProgress.getProgress(frames))
                .isEqualTo(LastFrameProgress.ON_FIRST_PITCH);
    }


    @Test
    @DisplayName("2회 피치 케이스")
    void testAdditionalPitch() {
        List<NormalFrame> frames = new ArrayList<>();
        NormalFrame first = new NormalFrame();
        first.record(DownedPin.fromNumber(5));
        first.record(DownedPin.fromNumber(3));

        assertThat(LastFrameProgress.getProgress(frames))
                .isEqualTo(LastFrameProgress.ON_ADDITIONAL_PITCH);
    }

    @Test
    @DisplayName("3회 피치 케이스")
    void testEndPitch() {
        List<NormalFrame> frames = new ArrayList<>();
        NormalFrame first = new NormalFrame();
        first.record(DownedPin.fromNumber(5));
        first.record(DownedPin.fromNumber(5));
        NormalFrame second = new NormalFrame();
        second.record(DownedPin.fromNumber(6));

        assertThat(LastFrameProgress.getProgress(frames))
                .isEqualTo(LastFrameProgress.ON_ADDITIONAL_PITCH);
    }
}
