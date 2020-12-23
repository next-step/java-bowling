package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    private Frame normalFrame;

    @BeforeEach
    void setUp() {
        normalFrame = new NormalFrame(0);
    }

    @Test
    @DisplayName("다음 normalFrame으로 넘어감")
    void bowl_notNextNormalFrame() {
        Frame frame = normalFrame.bowl(9);

        assertThat(frame).isSameAs(normalFrame);
    }

    @Test
    @DisplayName("다음 normalFrame으로 넘어감")
    void bowl_nextNormalFrame() {
        Frame frame = normalFrame.bowl(10);

        assertThat(frame).isNotSameAs(normalFrame);
    }

    @Test
    @DisplayName("마지막 라운드로 넘어가기")
    void bowl_nextFinalFrame() {
        normalFrame = new NormalFrame(8);
        Frame frame = normalFrame.bowl(10);
        frame = frame.bowl(10);

        assertThat(frame).isInstanceOf(FinalFrame.class);
    }

    @Test
    @DisplayName("미스 점수 확인")
    void getScore_miss() {
        normalFrame.bowl(4);
        normalFrame.bowl(5);

        Score score = normalFrame.getScore();

        assertThat(score).isEqualTo(new Score(9, 0));
    }

    @Test
    @DisplayName("스트라이크 점수 확인")
    void getScore() {
        normalFrame.bowl(10);

        Score score = normalFrame.getScore();

        assertThat(score).isEqualTo(new Score(10, 2));
    }

    @Test
    @DisplayName("스페어 점수 확인")
    void getScore_spare() {
        normalFrame.bowl(4);
        normalFrame.bowl(6);

        Score score = normalFrame.getScore();

        assertThat(score).isEqualTo(new Score(10, 1));
    }

    @Test
    @DisplayName("다음 차례까지 점수 더하기")
    void addScore_1next() {
        normalFrame.bowl(4);
        Score lastScore = new Score(1, 1);

        Score score = normalFrame.addScore(lastScore);

        assertThat(score).isEqualTo(new Score(5, 0));
    }

    @Test
    @DisplayName("다다음 차례까지 점수 더하기")
    void addScore_2next() {
        normalFrame.bowl(4);
        normalFrame.bowl(5);
        Score lastScore = new Score(1, 2);

        Score score = normalFrame.addScore(lastScore);

        assertThat(score).isEqualTo(new Score(10, 0));
    }
}
