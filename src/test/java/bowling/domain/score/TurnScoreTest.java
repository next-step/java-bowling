package bowling.domain.score;

import bowling.exception.InvalidTurnScoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class TurnScoreTest {
    @ValueSource(ints = {
            0, 1, 10
    })
    @ParameterizedTest
    @DisplayName("score 유효성 검사 통과")
    void ofTest(int score) {
        assertThatCode(() -> TurnScore.of(score))
                .doesNotThrowAnyException();
    }

    @ValueSource(ints = {
            -1, 11
    })
    @ParameterizedTest
    @DisplayName("score가 0 미만이거나 10 초과일떄")
    void ofInvalidTest(int score) {
        assertThatThrownBy(() -> TurnScore.of(score))
                .isInstanceOf(InvalidTurnScoreException.class);
    }

    @DisplayName("isAllClear 테스트")
    @Test
    void isAllClearTest() {
        assertThat(TurnScore.of(10).isAllClear())
                .isTrue();
    }

    public static List<TurnScore> toFrameScores(String strScores) {
        return Arrays.stream(strScores.split(","))
                .map(Integer::parseInt)
                .map(TurnScore::of)
                .collect(Collectors.toList());
    }
}