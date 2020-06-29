package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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

        assertThat(frameScores.getFrameScores()).containsExactly(8, 25, 32);
    }

    @DisplayName("12번 스트라이크를 치면 300점이 최종 누계됨")
    @Test
    public void getFrameScores_300점() {
        List<FrameScore> frameScoreList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            frameScoreList.add(FrameScore.ofMiss(10));
        }

        List<Integer> frameScores = FrameScores.of(frameScoreList).getFrameScores();

        assertThat(frameScores.get(frameScores.size() - 1)).isEqualTo(300);
    }
}
