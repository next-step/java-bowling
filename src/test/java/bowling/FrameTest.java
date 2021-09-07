package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FrameTest {
    @Test
    @DisplayName("프레임 생성")
    void createFrame() {
        Frame frame = new Frame(new Score(7, 2));
        assertThat(frame.getScore()).isEqualTo(9);
    }

    @Test
    @DisplayName("프레임 상태")
    void frameStatus() {
        Frame frame1 = new Frame(new Score(7, 2));
        Frame frame2 = new Frame(new Score(0, 0));
        Frame frame3 = new Frame(new Score(10, 0));
        Frame frame4 = new Frame(new Score(8, 2));
        assertAll(
                () -> assertThat(frame1.getStatus()).isEqualTo(Status.MISS),
                () -> assertThat(frame2.getStatus()).isEqualTo(Status.GUTTER),
                () -> assertThat(frame3.getStatus()).isEqualTo(Status.STRIKE),
                () -> assertThat(frame4.getStatus()).isEqualTo(Status.SPARE));
    }
}
