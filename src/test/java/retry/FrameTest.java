package retry;

import bowling.retry.Frame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

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

    @Test
    @DisplayName("남은 기회")
    void getChanceCount() {
        Frame frame = new Frame(0);
        frame.bowl(5);

        assertThat(frame.getChanceCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("던질 수 있는 투구 유효성 검사")
    void validateBowl() {
        Frame frame = new Frame(0);
        frame.bowl(1);
        frame.bowl(2);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            frame.bowl(5);
        });
    }
}
