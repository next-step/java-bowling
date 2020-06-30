package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FrameScoresTest {

    @DisplayName("점수 리턴을 요청하면 내부 컬렉션의 점수들을 누계하여 리스트로 반환함")
    @Test
    public void getFrameScores_8점_25점_32점() {
        List<FrameScore> frameScoreList =
                Arrays.asList(FrameScore.ofMiss(8), FrameScore.ofMiss(10), FrameScore.ofMiss(7));
        FrameScores frameScores = FrameScores.of(frameScoreList);

        assertThat(frameScores.getFrameScores()).containsExactly(8, 18, 25);
    }
}
