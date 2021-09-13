package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class TrialTest {
    @ParameterizedTest
    @MethodSource("generateIsEndTest1")
    @DisplayName("isEnd 최대 점수 케이스 테스트")
    void isEndTest1(List<Score> scores, boolean expected) {
        Trial trial = new Trial();
        scores.forEach(trial::add);

        assertThat(trial.isNormalEnd()).isEqualTo(expected);
    }

    private static Stream<Arguments> generateIsEndTest1() {
        return Stream.of(
                Arguments.of(Collections.singletonList(new Score(10)), true),
                Arguments.of(Collections.singletonList(new Score(9)), false)
        );
    }

    @Test
    @DisplayName("isEnd 최대길이 케이스 테스트")
    void isEndTest2() {
        Trial trial = new Trial();
        trial.add(new Score(8));
        trial.add(new Score(2));

        assertThat(trial.isNormalEnd()).isTrue();
    }

    @Test
    @DisplayName("getFrameResult 스트라이크 케이스 테스트")
    void getFrameResultTest1() {
        Trial trial = new Trial();
        trial.add(new Score(10));

        assertThat(trial.getFrameResult()).isEqualTo(FrameResult.STRIKE);
    }

    @Test
    @DisplayName("getFrameResult 스페어 케이스 테스트")
    void getFrameResultTest2() {
        Trial trial = new Trial();
        trial.add(new Score(8));
        trial.add(new Score(2));

        assertThat(trial.getFrameResult()).isEqualTo(FrameResult.SPARE);
    }

    @Test
    @DisplayName("getFrameResult 미스 케이스 테스트")
    void getFrameResultTest3() {
        Trial trial = new Trial();
        trial.add(new Score(8));
        trial.add(new Score(1));

        assertThat(trial.getFrameResult()).isEqualTo(FrameResult.MISS);
    }

    @Test
    @DisplayName("getFrameResult None 케이스 테스트")
    void getFrameResultTest4() {
        Trial trial2 = new Trial();
        trial2.add(new Score(8));

        assertThat(trial2.getFrameResult()).isEqualTo(FrameResult.NONE);
    }

    @ParameterizedTest
    @MethodSource("generateIsNormalEndTest")
    @DisplayName("NormalEnd 테스트")
    void isNormalEndTest(List<Score> scores, boolean expected) {
        Trial trial = new Trial();
        scores.forEach(trial::add);

        assertThat(trial.isNormalEnd()).isEqualTo(expected);
    }

    private static Stream<Arguments> generateIsNormalEndTest() {
        return Stream.of(
                Arguments.of(Collections.singletonList(new Score(10)), true),
                Arguments.of(Arrays.asList(new Score(9), new Score(1)), true)
        );
    }

    @ParameterizedTest
    @MethodSource("generateIsFinalEndTest")
    @DisplayName("FinalEnd 테스트")
    void isFinalEndTest(List<Score> scores, boolean expected) {
        Trial trial = new Trial();
        scores.forEach(trial::add);

        assertThat(trial.isFinalEnd()).isEqualTo(expected);
    }

    private static Stream<Arguments> generateIsFinalEndTest() {
        return Stream.of(
                Arguments.of(Arrays.asList(new Score(10), new Score(10), new Score(10)), true),
                Arguments.of(Arrays.asList(new Score(0), new Score(0)), true)
        );
    }
}
