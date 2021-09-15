package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreFramesTest {
    private ScoreFrames scoreFrames;

    @BeforeEach
    void init() {
        scoreFrames = new ScoreFrames();
    }

    @Test
    @DisplayName("게임이 진행중인지 체크 - 12회 스트라이크 이후 게임 종료")
    void isContinuedTest() {
        boolean beforeBowl = scoreFrames.isContinued();

        for (int i = 0; i < 12; i++) {
            scoreFrames.bowl(10);
        }

        boolean afterBowl = scoreFrames.isContinued();

        assertThat(beforeBowl).isTrue();
        assertThat(afterBowl).isFalse();
    }

    @Test
    @DisplayName("투구 후 프레임이 끝났는지 확인")
    void bowlTest() {
        boolean firstBowl = scoreFrames.bowl(8);
        boolean secondSpareBowl = scoreFrames.bowl(2);
        boolean thirdStrikeBowl = scoreFrames.bowl(10);

        assertThat(firstBowl).isFalse();

        assertThat(secondSpareBowl).isTrue();

        assertThat(thirdStrikeBowl).isTrue();
    }
}
