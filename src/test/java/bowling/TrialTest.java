package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrialTest {
    @Test
    @DisplayName("isEnd 최대 점수 케이스 테스트")
    void isEndTest1() {
        Trial trial = new Trial();
        trial.add(new Score(10));

        assertThat(trial.isNormalEnd()).isTrue();

        Trial trial2 = new Trial();
        trial2.add(new Score(9));

        assertThat(trial2.isNormalEnd()).isFalse();
    }

    @Test
    @DisplayName("isEnd 최대길이 케이스 테스트")
    void isEndTest2() {
        Trial trial = new Trial();
        trial.add(new Score(8));
        trial.add(new Score(2));

        assertThat(trial.isNormalEnd()).isTrue();
    }

    @Test
    @DisplayName("getFrameResult 스트라이크 케이스 테스트")
    void getFrameResultTest1() {
        Trial trial = new Trial();
        trial.add(new Score(10));

        assertThat(trial.getFrameResult()).isEqualTo(FrameResult.STRIKE);
    }

    @Test
    @DisplayName("getFrameResult 스페어 케이스 테스트")
    void getFrameResultTest2() {
        Trial trial = new Trial();
        trial.add(new Score(8));
        trial.add(new Score(2));

        assertThat(trial.getFrameResult()).isEqualTo(FrameResult.SPARE);
    }

    @Test
    @DisplayName("getFrameResult 미스 케이스 테스트")
    void getFrameResultTest3() {
        Trial trial = new Trial();
        trial.add(new Score(8));
        trial.add(new Score(1));

        assertThat(trial.getFrameResult()).isEqualTo(FrameResult.MISS);

        Trial trial2 = new Trial();
        trial.add(new Score(8));

        assertThat(trial2.getFrameResult()).isEqualTo(FrameResult.MISS);

        Trial trial3 = new Trial();

        assertThat(trial3.getFrameResult()).isEqualTo(FrameResult.MISS);
    }
}
