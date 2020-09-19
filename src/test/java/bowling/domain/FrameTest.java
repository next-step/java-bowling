package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FrameTest {

    @DisplayName("Frame 생성")
    @Test
    void frameInstanceCreate() {
        Frame frame = new Frame();
        assertThat(frame.nextFrame(false)).isInstanceOf(Frame.class);
        assertThat(frame.nextFrame(true)).isInstanceOf(FinalFrame.class);
    }

    @DisplayName("투구 점수가 범위를 벗어난 경우")
    @ValueSource(ints = { -1, 11 })
    @ParameterizedTest
    void swingFailCase(int score) {

        assertThatIllegalArgumentException()
                .isThrownBy(() -> (new Frame()).swing(score));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> (new FinalFrame()).swing(score));
    }

    @DisplayName("한 프레임 당 점수는 합쳐서 10점")
    @CsvSource(value = {"9,3", "5,7", "4,8"})
    @ParameterizedTest
    void overScoreCase(int score1, int score2) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Frame frame = new Frame();
            frame.swing(score1);
            frame.swing(score2);
        });
    }
}
