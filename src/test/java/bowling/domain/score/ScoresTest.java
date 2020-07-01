package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.fixture.FramesFixture;
import bowling.fixture.NormalFrameFixture;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class ScoresTest {

    @DisplayName("생성 성공")
    @Test
    public void create() {
        assertThatCode(Scores::newInstance)
                .doesNotThrowAnyException();
    }

    @DisplayName("점수를 계산할 수 없는 경우")
    @ParameterizedTest
    @MethodSource
    public void doNothingOfSumScore(final Frame frame) {
        Scores scores = Scores.newInstance();
        scores.sumScore(frame);

        assertThat(scores.getScores().isEmpty())
                .isTrue();
    }

    private static Stream<Arguments> doNothingOfSumScore() {
        return Stream.of(
                Arguments.of(NormalFrameFixture.getReadyFrame()),
                Arguments.of(NormalFrameFixture.getSpareFrame()),
                Arguments.of(NormalFrameFixture.getOneHitFrame())
        );
    }

    @DisplayName("해당 프레임의 점수를 리스트에 추가")
    @Test
    public void sumScore() {
        Frame frame = FramesFixture.getTwoMissFrames()
                .getFrames()
                .get(1);

        Scores scores = Scores.newInstance();
        scores.sumScore(frame);

        assertThat(scores.getScores().size())
                .isEqualTo(1);
    }
}
