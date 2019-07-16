package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResultsTest {

    @DisplayName("각 프레임 점수를 합산한다")
    @Test
    void createResult() {
        // given
        List<FrameResult> frameResult = Arrays.asList(
                new FrameResult(10, "X"),
                new FrameResult(10, "X"),
                new FrameResult(10, "X")
        );

        // when
        Results results = Results.of(frameResult);

        // then
        assertThat(results.getResults())
                .extracting(FrameResult::getScore)
                .containsExactly(10, 20, 30);
    }

    @DisplayName("각 프레임의 토탈 점수를 반환한다")
    @Test
    void getScore() {
        // given
        List<FrameResult> frameResult = Arrays.asList(
                new FrameResult(10, "X"),
                new FrameResult(10, "X"),
                new FrameResult(-1, "X")
        );

        // when
        Results results = Results.of(frameResult);
        List<Integer> scores = results.getScores();

        // then
        assertThat(scores).hasSize(2);
        assertThat(scores).containsExactly(10, 20);
    }

    @DisplayName("각 프레임의 상태를 반환한다")
    @Test
    void getState() {
        // given
        List<FrameResult> frameResult = Arrays.asList(
                new FrameResult(10, "X"),
                new FrameResult(10, "X"),
                new FrameResult(-1, "X")
        );

        // when
        Results results = Results.of(frameResult);
        List<String> states = results.getStates();

        // then
        assertThat(states).hasSize(3);
        assertThat(states).containsExactly("X", "X", "X");
    }
}