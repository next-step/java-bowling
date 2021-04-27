package bowling.frame;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalFrameTest {

    @Test
    @DisplayName("스트라이크, 스페어가 아닌 경우 3번 투구 불가")
    void frame_pitch_not_three() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(2);
        finalFrame.bowl(2);
        assertThatThrownBy(() -> finalFrame.bowl(2))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @ParameterizedTest
    @CsvSource(value = {"10,10,10,X|X|X", "1,9,10,1|/|X", "10,2,1,X|2|1"})
    @DisplayName("스트라이크, 스페어인 경우 3번 투구")
    void frame_pitch_three(int first, int second, int third, String expect) {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(first);
        finalFrame.bowl(second);
        finalFrame.bowl(third);
        assertThat(finalFrame.getFallenPins()).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource(value = {"5,5,10", "1,2,3"})
    void second_pitch(int first, int second, int expect) {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(first);
        finalFrame.bowl(second);
        assertThat(finalFrame.getScore()).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource(value = {"10,10,10,30", "1,9,3,13"})
    void second_pitch(int first, int second, int third, int expect) {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(first);
        finalFrame.bowl(second);
        finalFrame.bowl(third);
        assertThat(finalFrame.getScore()).isEqualTo(expect);
    }
}
