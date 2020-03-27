package bowling.domain.frame;

import bowling.domain.state.NextReady;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    @Test
    @DisplayName("프레임 객체 비교")
    void equalsToFrame() {
        Frame frame = new Frame(1);
        Frame expectedFrame = new Frame(1);

        boolean same = frame.equals(expectedFrame);

        assertThat(same).isTrue();
    }

    @Test
    @DisplayName("bowl 이후 생성되는 상태 비교")
    void compareToStateByAfterBowl() {
        Frame frame = new Frame(1);
        Frame expectedFrame = frame.bowl(2);

        boolean same = frame.equals(expectedFrame);

        assertThat(same).isTrue();
        assertThat(expectedFrame.getState() instanceof NextReady).isTrue();
    }
}
