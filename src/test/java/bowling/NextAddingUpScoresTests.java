package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("합산 스코어 객체 테스트")
public class NextAddingUpScoresTests {

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> NextAddingUpScores.newInstance(Arrays.asList(Score.of(6), Score.of(4))));
        assertThatCode(() -> NextAddingUpScores.newInstance(Arrays.asList(Score.of(10))));
    }

    @DisplayName("스코어 추가 테스트")
    @Test
    public void generateTest() {
        NextAddingUpScores nextAddingUpScores = NextAddingUpScores.newInstance(Arrays.asList(Score.of(10)));
        assertThatCode(() -> nextAddingUpScores.add(Score.of(10)));
    }
}
