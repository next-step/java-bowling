package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoresTest {
    @Test
    @DisplayName("isEnd 최대 점수 케이스 테스트")
    void isEndTest1() {
        Scores scores = new Scores();
        scores.add(new Score(10));

        assertThat(scores.isEnd()).isTrue();

        Scores scores2 = new Scores();
        scores2.add(new Score(9));

        assertThat(scores2.isEnd()).isFalse();
    }

    @Test
    @DisplayName("isEnd 최대길이 케이스 테스트")
    void isEndTest2() {
        Scores scores = new Scores();
        scores.add(new Score(8));
        scores.add(new Score(2));

        assertThat(scores.isEnd()).isTrue();
    }

    @Test
    @DisplayName("getFrameResult 스트라이크 케이스 테스트")
    void getFrameResultTest1() {
        Scores scores = new Scores();
        scores.add(new Score(10));

        assertThat(scores.getFrameResult()).isEqualTo(FrameResult.STRIKE);
    }

    @Test
    @DisplayName("getFrameResult 스페어 케이스 테스트")
    void getFrameResultTest2() {
        Scores scores = new Scores();
        scores.add(new Score(8));
        scores.add(new Score(2));

        assertThat(scores.getFrameResult()).isEqualTo(FrameResult.SPARE);
    }

    @Test
    @DisplayName("getFrameResult 미스 케이스 테스트")
    void getFrameResultTest3() {
        Scores scores = new Scores();
        scores.add(new Score(8));
        scores.add(new Score(1));

        assertThat(scores.getFrameResult()).isEqualTo(FrameResult.MISS);

        Scores scores2 = new Scores();
        scores.add(new Score(8));

        assertThat(scores2.getFrameResult()).isEqualTo(FrameResult.MISS);

        Scores scores3 = new Scores();

        assertThat(scores3.getFrameResult()).isEqualTo(FrameResult.MISS);
    }
}
