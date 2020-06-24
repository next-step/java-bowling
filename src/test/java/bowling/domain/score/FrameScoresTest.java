package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FrameScoresTest {

    @DisplayName("add 요청을 하면 내부 컬렉션에 객체를 추가함")
    @Test
    public void addScore() {
        FrameScores frameScores = new FrameScores();
        FrameScore firstFrameScore = FrameScore.of(9);
        List<Integer> frameScoreList = frameScores.getCumulativeFrameScores();

        assertThat(frameScoreList.isEmpty()).isTrue();

        frameScores.add(firstFrameScore);

        frameScoreList = frameScores.getCumulativeFrameScores();

        assertThat(frameScoreList).containsExactly(9);
    }

    @DisplayName("예외값인 null은 추가하지 않음")
    @Test
    public void addScore_null_예외() {
        FrameScores frameScores = new FrameScores();
        FrameScore firstFrameScore = null;
        List<Integer> frameScoreList = frameScores.getCumulativeFrameScores();

        assertThat(frameScoreList.isEmpty()).isTrue();

        frameScores.add(firstFrameScore);

        frameScoreList = frameScores.getCumulativeFrameScores();

        assertThat(frameScoreList.isEmpty()).isTrue();
    }

    @DisplayName("점수들의 리스트를 름요청하면 프레임의 오차순으로 점수를 누계하여 리스트로 반환함")
    @Test
    public void getScores_누계_검사() {
        FrameScores frameNumericScores = new FrameScores();
        FrameScore firstFrameScore = FrameScore.of(17);
        FrameScore secondFrameScore = FrameScore.of(7);
        FrameScore thirdFrameScore = FrameScore.of(8);
        frameNumericScores.add(firstFrameScore);
        frameNumericScores.add(secondFrameScore);
        frameNumericScores.add(thirdFrameScore);

        List<Integer> frameScores = frameNumericScores.getCumulativeFrameScores();

        assertThat(frameScores).containsExactly(17, 24, 32);
    }
}
