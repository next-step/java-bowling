package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @DisplayName("스코어 합계 반영 테스트")
    @Test
    public void addingUpTest() {
        FrameScore strikeFrame = FrameScore.createStrike();
        assertThat(strikeFrame.addNextAddingUpScores(Score.of(3), Score.of(7)))
                .isEqualTo(FrameScore.newInstance(Score.of(20), LeftScoreCount.of(0)));

        FrameScore spareFrame = FrameScore.createSpare();
        assertThat(spareFrame.addNextAddingUpScores(Score.of(3), Score.of(7)))
                .isEqualTo(FrameScore.newInstance(Score.of(13), LeftScoreCount.of(0)));
    }

    @DisplayName("스코어 계산 가능 체크 테스트")
    @Test
    public void canCalculateScoreTest() {

        FrameScore readyFrameScore = FrameScore.createReady();
        assertFalse(readyFrameScore.canCalculateSelfScore());

        FrameScore missFrameScore = FrameScore.createMiss(Score.of(7));
        assertTrue(missFrameScore.canCalculateSelfScore());

        FrameScore strikeFrameScore = FrameScore.createStrike();
        assertFalse(strikeFrameScore.canCalculateSelfScore());

        FrameScore spareFrameScore = FrameScore.createSpare();
        assertFalse(spareFrameScore.canCalculateSelfScore());
    }

}
;