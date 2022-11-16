package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    public static Stream<Arguments> provideScoresAndStatus() {
        return Stream.of(
                Arguments.of(new Rolls(10), FrameStatus.STRIKE, true),
                Arguments.of(new Rolls(9, 1), FrameStatus.SPARE, true),
                Arguments.of(new Rolls(9, 0), FrameStatus.MISS, true),
                Arguments.of(new Rolls(2), FrameStatus.PROGRESS, false)
        );
    }

    public static Stream<Arguments> provideFrameAndScore() {
        return Stream.of(
                Arguments.of(new NormalFrame(new Rolls(10)), new Score(10, 2)),
                Arguments.of(new NormalFrame(new Rolls(9, 1)), new Score(10, 1)),
                Arguments.of(new NormalFrame(new Rolls(9, 0)), new Score(9, 0))
        );
    }

    @Test
    void 생성() {
        assertThat(new NormalFrame().getScores().size()).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("provideScoresAndStatus")
    void 프레임_상태_업데이트(Rolls rolls, FrameStatus status, boolean isEnd) {
        NormalFrame frame = new NormalFrame(rolls);
        frame.updateStatus();
        assertThat(frame.getStatus()).isEqualTo(status);
        assertThat(frame.isEnd()).isEqualTo(isEnd);
    }

    @Test
    void 입력값_프레임에_추가() {
        NormalFrame frame = new NormalFrame();
        frame.addRoll(new Pin(10));
        assertThat(frame.getScores().getScores()).contains(new Pin(10));
    }

    @ParameterizedTest
    @MethodSource("provideFrameAndScore")
    void 프레임_완료시_스코어_계산(Frame frame, Score score) {
        assertThat(frame.calculateScore()).isEqualTo(score);
    }
}
