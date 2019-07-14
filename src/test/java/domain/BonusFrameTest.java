package domain;

import org.junit.jupiter.api.Test;

import static domain.Bowling.TOTAL_FRAME_COUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class BonusFrameTest {
    @Test
    void 보너스투구() {
        Frame frame = new Frame(TOTAL_FRAME_COUNT);
        frame.doBowling(8);
        frame.doBowling(2);
        frame.doBowling(4);
        assertThat(frame.getScore()).isEqualTo(14);
    }

    @Test
    void 커버되지_않은_보너스투구_예외발생() {
        Frame frame = new Frame(10);
        frame.doBowling(8);
        frame.doBowling(1);

        assertThatIllegalStateException().isThrownBy(() -> {
            frame.doBowling(4);
        });
    }
}
