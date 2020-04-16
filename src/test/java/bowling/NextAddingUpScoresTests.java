package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
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
    public void updateTest() {
        NextAddingUpScores nextAddingUpScores = NextAddingUpScores.newInstance(Arrays.asList(Score.of(10)));
        assertThat(nextAddingUpScores.update(Arrays.asList(Score.of(10)))).isEqualTo(NextAddingUpScores.newInstance(10, 10));
    }

    @DisplayName("스코어 스트라이크 합산 테스트")
    @Test
    public void sumStrikeTest() {
        NextAddingUpScores nextAddingUpScores = NextAddingUpScores.newInstance(Arrays.asList(Score.of(8), Score.of(1)));
        assertThat(nextAddingUpScores.sumAddingUpStrikeCase()).isEqualTo(9);
    }

    @DisplayName("스코어 스페어 합산 테스트")
    @Test
    public void sumSpareTest() {
        NextAddingUpScores nextAddingUpScores = NextAddingUpScores.newInstance(Arrays.asList(Score.of(8)));
        assertThat(nextAddingUpScores.sumAddingUpSpareCase()).isEqualTo(8);
    }
}
