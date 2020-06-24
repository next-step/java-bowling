package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FrameNumericScoresTest {

    @DisplayName("add 요청을 하면 내부 컬렉션에 객체를 추가함")
    @Test
    public void addScore() {
        FrameNumericScores frameNumericScores = new FrameNumericScores();
        FrameNumericScore firstFrameScore = FrameNumericScore.of(9);
        List<FrameNumericScore> frameNumericScoreList = frameNumericScores.getFrameNumericScores();

        assertThat(frameNumericScoreList.size()).isEqualTo(0);

        frameNumericScores.add(firstFrameScore);

        assertThat(frameNumericScoreList.size()).isEqualTo(1);
    }

    @DisplayName("점수들의 리스트를 요청하면 프레임의 오름차순으로 점수를 누계하여 리스트로 반환함")
    @Test
    public void getScores_누계_검사() {
        FrameNumericScores frameNumericScores = new FrameNumericScores();
        FrameNumericScore firstFrameScore = FrameNumericScore.of(17);
        FrameNumericScore secondFrameScore = FrameNumericScore.of(7);
        FrameNumericScore thirdFrameScore = FrameNumericScore.of(8);

        frameNumericScores.add(firstFrameScore);
        frameNumericScores.add(secondFrameScore);
        frameNumericScores.add(thirdFrameScore);

        List<Integer> frameScores = frameNumericScores.getScores();

        assertThat(frameScores).containsExactly(17, 24, 32);

    }

}
