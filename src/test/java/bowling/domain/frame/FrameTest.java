package bowling.domain.frame;

import bowling.bowlingexception.InvalidDownedPinNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class FrameTest {

    @Test
    @DisplayName("2회 진행하였을 경우의 종료조건")
    void testIsEnd() {
        Frame frame = new Frame();
        int firstPitch = 4;
        int secondPitch = 3;

        assertThat(frame.isEnd()).isFalse();
        frame.record(firstPitch);
        assertThat(frame.isEnd()).isFalse();
        frame.record(secondPitch);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("2회 진행시의 초과 범위 예외처리")
    void testInvalidRange() {
        Frame frame = new Frame();
        int firstPitch = 4;
        int secondPitch = 7;

        frame.record(firstPitch);

        assertThatThrownBy(() -> frame.record(secondPitch))
                .isInstanceOf(InvalidDownedPinNumberException.class);
    }

    @Test
    @DisplayName("10개를 첫 피치에 성공했을 때의 종료")
    void testIsStrike() {
        Frame frame = new Frame();

        assertThat(frame.isEnd()).isFalse();
        frame.record(10);
        assertThat(frame.isEnd()).isTrue();
    }
}
