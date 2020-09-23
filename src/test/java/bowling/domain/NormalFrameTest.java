package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NormalFrameTest {

    @DisplayName("Frame 생성")
    @Test
    void frameInstanceCreate() {
        NormalFrame frame = FrameFactory.first();
        assertThat(FrameFactory.next(frame, 1)).isInstanceOf(Frame.class);
    }

    @DisplayName("투구 점수가 범위를 벗어난 경우")
    @ValueSource(ints = { -1, 11 })
    @ParameterizedTest
    void swingFailCase(int score) {

        assertThatIllegalArgumentException()
                .isThrownBy(() -> (new NormalFrame()).swing(score));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> (new NormalFrame()).swing(score));
    }

    @DisplayName("한 프레임 당 점수는 합쳐서 10점")
    @CsvSource(value = {"9,3", "5,7", "4,8"})
    @ParameterizedTest
    void overScoreCase(int score1, int score2) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            NormalFrame frame = new NormalFrame();
            frame.swing(score1);
            frame.swing(score2);
        });
    }


    @DisplayName("NormalFrame은 swing을 두 번 하면 종료")
    @CsvSource(value = {"1,2", "3,4"})
    @ParameterizedTest
    void only2swing(int score1, int score2) {

        NormalFrame frame = new NormalFrame();
        frame.swing(score1);
        frame.swing(score2);

        assertTrue(frame.isEndedFrame());
    }
}
