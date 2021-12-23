package bowling.domain.state.progress;

import static org.assertj.core.api.Assertions.*;

import bowling.Pin;
import bowling.domain.frame.Frame;
import bowling.domain.frame.GeneralFrame;
import bowling.domain.state.end.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FirstProgressTest {

    private final Frame frame = new GeneralFrame(null);
    private static final Pin STRIKE_PIN = Pin.of(10);
    private static final Pin NON_STRIKE_PIN = Pin.of(5);


    @Test
    @DisplayName("Strike 수행시, Stike가 반환된다.")
    void getUpdateStateStrikeTest() {
        FirstProgress firstProgress = new FirstProgress(frame, STRIKE_PIN);
        assertThat(firstProgress.getUpdateState(frame, Pin.of(10))).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("Strike가 아닌경우 SecondProgress가 반환된다.")
    void getUpdateStateNotStrikeTest() {
        FirstProgress firstProgress = new FirstProgress(frame, NON_STRIKE_PIN);
        assertThat(firstProgress.getUpdateState(frame, Pin.of(3))).isInstanceOf(GeneralProgress.class);
    }


}