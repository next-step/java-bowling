package bowling.domain.frame;

import bowling.domain.status.BowlStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @DisplayName("다음 프레임")
    @ParameterizedTest
    @EnumSource(value = BowlStatus.class, names = {"STRIKE", "SPARE", "MISS"})
    void next_frame(BowlStatus state) {
        Frame frame = new NormalFrame(1, state);
        assertThat(frame.bowl()).isEqualTo(new NormalFrame(2, BowlStatus.NORAML));
    }

    @DisplayName("마지막 프레임")
    @ParameterizedTest
    @EnumSource(value = BowlStatus.class, names = {"STRIKE", "SPARE", "MISS"})
    void next_frame_final(BowlStatus state) {
        Frame frame = new NormalFrame(9, state);
        assertThat(frame.bowl()).isInstanceOf(FinalFrame.class);
    }
}