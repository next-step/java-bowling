package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ScoresTest {
    @DisplayName("점수 일급 컬렉션을 생성한다.")
    @Test
    void scores_생성() {
        List<Score> scores = List.of(new Score(10, 0), new Score(10, 0));
        assertThat(new Scores(scores)).isNotNull().isInstanceOf(Scores.class);
    }

    @DisplayName("점수 리스트가 null 일 경우 예외가 발생한다.")
    @ParameterizedTest
    @NullSource
    void scores_점수_리스트_null_인_경우(List<Score> scores) {
        assertThatThrownBy(() -> new Scores(scores)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("리스트의 이전 인덱스의 값에 대한 누적값을 리스트로 반환한다.")
    @ParameterizedTest
    @MethodSource("scores_provider")
    void accumulateScore_누적합_리스트(Scores scores, List<Integer> accumulateScoreList) {
        assertThat(scores.accumulateScore()).isEqualTo(accumulateScoreList);
    }

    static Stream<Arguments> scores_provider() {
        return Stream.of(
                arguments(new Scores(List.of(new Score(10, 0), new Score(10, 0), new Score(10, 0))), List.of(10, 20, 30)),
                arguments(new Scores(List.of(new Score(30, 0), new Score(30, 0), new Score(30, 0))), List.of(30, 60, 90)),
                arguments(new Scores(List.of(new Score(5, 0), new Score(20, 0), new Score(24, 0))), List.of(5, 25, 49))
        );
    }
}