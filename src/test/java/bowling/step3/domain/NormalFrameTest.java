package bowling.step3.domain;

import bowling.step3.domain.frame.Frame;
import bowling.step3.domain.frame.NormalFrame;
import bowling.step3.domain.scores.NormalScores;
import bowling.step3.domain.scores.Scores;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NormalFrameTest {

    @DisplayName("현재 프레임이 종료되지 않은 상태에서 createNextFrame을 했을 때 null로 할당하는지 확인")
    @Test
    public void 다음_프레임_생성_테스트() {
        Frame frame = NormalFrame.of(1, NormalScores.init(), null);
        frame.createNextFrameOfScores(frame.getScores().nextInit(Score.valueOf(1)));
        assertEquals(null, frame.getNextFrame());
    }

    @DisplayName("현재 프레임이 완료 되었을 때 다음 프레임이 정상적으로 생성되는지 확인")
    @ParameterizedTest
    @MethodSource("provideFrameAndNextFrame")
    void 다음_프레임_생성_테스트(Frame frame) {
        assertEquals(true, frame.getNextFrame() != null);
    }

    private static Stream<Arguments> provideFrameAndNextFrame() {
        Frame frame1 = NormalFrame.of(1, NormalScores.init(), null);
        Frame frame2 = NormalFrame.of(2, NormalScores.init(), null);

        frame1.createNextFrameOfScores(frame1.getScores().nextInit(Score.getStrike()));
        frame2.createNextFrameOfScores(frame2.getScores().nextInit(Score.valueOf(1)));
        frame2.createNextFrameOfScores(frame2.getScores().nextInit(Score.valueOf(2)));
        return Stream.of(
            Arguments.of(frame1),
            Arguments.of(frame2)
        );
    }
}
