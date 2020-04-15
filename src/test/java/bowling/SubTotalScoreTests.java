package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("프레임 점수 테스트")
public class SubTotalScoreTests {

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> SubTotalScore.newInstance(20, NextAddingUpScores.newInstance(Arrays.asList(Score.of(10)))));
    }

}
