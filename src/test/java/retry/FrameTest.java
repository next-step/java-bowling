package retry;

import bowling.retry.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    @DisplayName("Frame 객체 비교")
    void compareToFrame() {
        // give
        Frame actualFrame = new Frame(1);
        Frame expectedFrame = new Frame(1);

        // when
        boolean same = actualFrame.equals(expectedFrame);

        // then
        assertThat(same).isTrue();
    }

    @Test
    @DisplayName("프레임 상태 확인")
    void bowlTest() {
        // give
        Frame strikeFrame = new Frame(1);
        strikeFrame.bowl(10);

        Frame spareFrame = new Frame(2);
        spareFrame.bowl(2);
        spareFrame.bowl(8);

        Frame missFrame = new Frame(3);
        missFrame.bowl(2);
        missFrame.bowl(3);

        Frame gutterFrame = new Frame(4);
        gutterFrame.bowl(0);
        gutterFrame.bowl(0);


        assertThat(strikeFrame.getFrameStatus()).isInstanceOf(Strike.class);
        assertThat(spareFrame.getFrameStatus()).isInstanceOf(Spare.class);
        assertThat(missFrame.getFrameStatus()).isInstanceOf(Miss.class);
        assertThat(gutterFrame.getFrameStatus()).isInstanceOf(Gutter.class);
    }
}
