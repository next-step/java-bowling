package retry;

import bowling.retry.Frame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    @DisplayName("프레임 객체 값 비교")
    void checkObjectValue() {
        Frame frame = new Frame(0);
        frame.bowl(5);

        Frame frame2 = new Frame(0);
        frame2.bowl(5);

        assertThat(frame).isEqualTo(frame2);
    }

    @Test
    @DisplayName("두 번째 투구까지 했을 때 프레임 객체 비교")
    void checkObjectValueBySecondBowl() {
        Frame frame = new Frame(0);
        frame.bowl(5);
        frame.bowl(3);

        Frame frame2 = new Frame(0);
        frame2.bowl(5);

        assertThat(frame).isNotEqualTo(frame2);
    }

    @Test
    @DisplayName("핀의 합산")
    void calculateScore() {
        Frame frame = new Frame(0);
        frame.bowl(5);
        frame.bowl(3);

        assertThat(frame.getScore()).isEqualTo(8);
    }
}
