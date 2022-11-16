package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BonusFrameTest {
    public static Stream<Arguments> provideScoresAndStatus() {
        return Stream.of(
                Arguments.of(new Rolls(10), FrameStatus.STRIKE, false),
                Arguments.of(new Rolls(9, 1), FrameStatus.SPARE, false),
                Arguments.of(new Rolls(9, 0), FrameStatus.MISS, true),
                Arguments.of(new Rolls(2), FrameStatus.PROGRESS, false)
        );
    }

    public static Stream<Arguments> provideFrameAndScore() {
        return Stream.of(
                Arguments.of(new NormalFrame(new Rolls(10, 10, 10)), new Score(30, 0)),
                Arguments.of(new NormalFrame(new Rolls(9, 1, 5)), new Score(15, 0)),
                Arguments.of(new NormalFrame(new Rolls(9, 0)), new Score(9, 0))
        );
    }

    @Test
    void 생성() {
        assertThat(new NormalFrame().getScores().size()).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("provideScoresAndStatus")
    void 프레임_상태_변경_및_완료_확인(Rolls rolls, FrameStatus result, boolean isEnd) {
        BonusFrame frame = new BonusFrame(rolls);
        frame.updateStatus();
        assertThat(frame.getStatus()).isEqualTo(result);
        assertThat(frame.isEnd()).isEqualTo(isEnd);
    }

    @Test
    void 입력값_프레임에_추가() {
        BonusFrame frame = new BonusFrame();
        frame.addRoll(new Pin(10));
        assertThat(frame.getScores().getScores()).contains(new Pin(10));
    }

    @ParameterizedTest
    @MethodSource("provideFrameAndScore")
    void 프레임_완료시_스코어_계산(Frame frame, Score score) {
        assertThat(frame.calculateScore()).isEqualTo(score);
    }
}
