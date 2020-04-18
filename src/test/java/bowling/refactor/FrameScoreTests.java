package bowling.refactor;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("프레임 스코어 테스트")
public class FrameScoreTests {

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> FrameScore.newInstance(Score.of(7), LeftScoreCount.of(1)));
    }

    @DisplayName("생성 (Miss) 테스트")
    @Test
    public void generateMissTest() {
        assertThatCode(() -> FrameScore.createMiss(Score.of(7)));
    }

    @DisplayName("생성 (Spare) 테스트")
    @Test
    public void generateSpareTest() {
        assertThatCode(FrameScore::createSpare);
    }

    @DisplayName("생성 (Strike) 테스트")
    @Test
    public void generateStrikeTest() {
        assertThatCode(FrameScore::createStrike);
    }

    @DisplayName("스코어 반환 테스트")
    @Test
    public void getScoreTest() {
        FrameScore frameScore = FrameScore.createSpare();
        assertThat(frameScore.getScore()).isEqualTo(Score.of(10));
    }

    @DisplayName("스코어 계산 가능 체크 테스트")
    @Test
    public void canCalculateScoreTest() {
        FrameScore frameScore = FrameScore.createMiss(Score.of(7));
        assertFalse(frameScore.canCalculateScore());
    }

}
