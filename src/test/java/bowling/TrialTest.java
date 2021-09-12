package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

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

        assertThat(trial2.getFrameResult()).isEqualTo(FrameResult.NONE);
    }

    @Test
    @DisplayName("NormalEnd 테스트")
    void isNormalEndTest() {
        Trial trial = new Trial();

        trial.add(new Score(10));

        assertThat(trial.isNormalEnd()).isTrue();

        Trial trial2 = new Trial();

        trial2.add(new Score(0));
        trial2.add(new Score(0));

        assertThat(trial2.isNormalEnd()).isTrue();
    }

    @Test
    @DisplayName("FinalEnd 테스트")
    void isFinalEndTest() {
        Trial trial = new Trial();

        trial.add(new Score(10));
        trial.add(new Score(10));
        trial.add(new Score(10));

        assertThat(trial.isFinalEnd()).isTrue();

        Trial trial2 = new Trial();

        trial2.add(new Score(0));
        trial2.add(new Score(0));

        assertThat(trial2.isFinalEnd()).isTrue();
    }
}
