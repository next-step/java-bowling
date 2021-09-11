package step2.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class BowLingScoreViewFormatTest {
    @DisplayName("spair 결과 테스트")
    @Test
    void strikeViewTest() {
        List<Integer> bowlingScores = Arrays.asList(10);
        List<String> actual = BowLingScoreViewFormat.transferBowlingScoreViewFormat(bowlingScores);
        Assertions.assertThat(actual.get(0)).isEqualTo("X");
    }

    @DisplayName("spair 결과 테스트")
    @Test
    void spairViewTest() {
        List<Integer> bowlingScores = Arrays.asList(4,6);
        List<String> actual = BowLingScoreViewFormat.transferBowlingScoreViewFormat(bowlingScores);
        Assertions.assertThat(actual.get(1)).isEqualTo("/");
    }

    @DisplayName("score 결과 테스트")
    @Test
    void scoreViewTest() {
        List<Integer> bowlingScores = Arrays.asList(4,6);
        List<String> actual = BowLingScoreViewFormat.transferBowlingScoreViewFormat(bowlingScores);
        Assertions.assertThat(actual.get(0)).isEqualTo("4");
    }
}