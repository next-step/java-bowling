import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FrameTest {
    @Test
    @DisplayName("프레임 생성 테스트")
    void createTest() {
        Frame frame = Frame.of(10);
        assertThat(frame).isEqualTo(Frame.of(10));
    }

    @Test
    @DisplayName("점수생성")
    void checkScore() {
        Frame frame = Frame.of(9);
        frame.addScore(1);
        assertThat(frame.getScore()).isEqualTo(Score.SPARE);

        frame = Frame.of(10);
        assertThat(frame.getScore()).isEqualTo(Score.STRIKE);

        frame = Frame.of(4);
        frame.addScore(4);
        assertThat(frame.getScore()).isEqualTo(Score.MISS);
    }

    @Test
    @DisplayName("점수 생성 시 예외발생")
    void createScoreException() {
        assertThrows(IllegalArgumentException.class, () -> Frame.of(11));
        assertThrows(IllegalArgumentException.class, () -> Frame.of(10).addScore(1));
        assertThrows(IllegalArgumentException.class, () -> Frame.of(4).addScore(7));
    }
}
