package bowling.state;

import bowling.domain.Frame;
import bowling.domain.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created By mand2 on 2020-12-19.
 */
public class StateTest {

    @Test
    @DisplayName("상태 전략 기본")
    void create() {
        Frame frame = NormalFrame.of(1);

        BowlingState bowlingState = Open.of(frame);
    }
}
