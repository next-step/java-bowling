package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameStatusTest {

    @Test
    @DisplayName("Strike 여부확인(Strike)")
    void testIsStrike() {
        assertThat(FrameStatus.getStatus(DownedPin.fromNumber(10), null))
                .isEqualTo(FrameStatus.STRIKE);
    }
}
