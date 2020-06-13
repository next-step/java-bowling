package bowling.domain;

import bowling.step2.domain.Score;
import bowling.step2.domain.frame.Frame;
import bowling.step2.domain.frame.NormalFrame;
import bowling.step2.domain.scores.NormalScores;
import bowling.step2.domain.scores.Scores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NormalFrameTest {

    @DisplayName("다음 프레임을 정상적으로 생성하고 있는지 확인")
    @ParameterizedTest
    @MethodSource("provideFrameAndNextFrame")
    void 다음_프레임_생성_테스트 (Frame frame, Scores prevFrameScores) {
        assertEquals(prevFrameScores, frame.getPrevFrame().getScores());
    }

    private static Stream<Arguments> provideFrameAndNextFrame () {
        Score score1 = Score.valueOf(1);
        Score score2 = Score.valueOf(2);
        Score score3 = Score.valueOf(3);
        Score score4 = Score.valueOf(4);
        Scores scores1 = NormalScores.init().nextInit(score1);
        Scores scores2 = scores1.nextInit(score2);
        Scores scores3 = NormalScores.init().nextInit(score3);
        Scores scores4 = scores3.nextInit(score4);
        Frame frame = NormalFrame.of(1, scores1, null);
        Frame nextFrame1 = frame.createNextFrame(scores2);
        Frame nextFrame2 = nextFrame1.createNextFrame(scores3);
        Frame nextFrame3 = nextFrame2.createNextFrame(scores4);
        return Stream.of(
            Arguments.of(nextFrame1, scores2),
            Arguments.of(nextFrame2, scores2),
            Arguments.of(nextFrame3, scores4)
        );
    }
}
