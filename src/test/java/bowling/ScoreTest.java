package bowling;

import bowling.domain.Frame;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    @DisplayName("요청 값에 따른 스코어 테스트")
    void checkScoreByRequestTest() {
        Score strike = new Score(10, 1);
        Score spare = new Score(5+5, 0);
        Score gutter = new Score(0, 1);
        Score gutter2 = new Score(0, 0);
        Score miss = new Score(7, 1);
        Score miss2 = new Score(2, 0);

        assertThat(strike.getScore()).isEqualTo("X");
        assertThat(spare.getScore()).isEqualTo("/");
        assertThat(gutter.getScore()).isEqualTo("-");
        assertThat(gutter2.getScore()).isEqualTo("-");
        assertThat(miss.getScore()).isEqualTo("7");
        assertThat(miss2.getScore()).isEqualTo("2");
    }

    @Test
    @DisplayName("프레임 객체에 따른 스코어 테스트")
    void checkScoreByFrameObjectTest() {
        Frame frame = Frame.firstFrame(5);
        Frame frame1 = frame.nextFrame(4);

        assertThat(frame.getScore()).isEqualTo("5");
        assertThat(frame1.getScore()).isEqualTo("4");
    }
}
