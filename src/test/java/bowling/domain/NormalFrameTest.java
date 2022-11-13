package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    public static Stream<Arguments> provideScoresAndResult() {
        return Stream.of(
                Arguments.of(new Rolls(10), true, FrameStatus.STRIKE),
                Arguments.of(new Rolls(9, 1), true, FrameStatus.SPARE),
                Arguments.of(new Rolls(2), false, null)
        );
    }

    @Test
    void 생성() {
        assertThat(new NormalFrame().getScores().size()).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("provideScoresAndResult")
    void 프레임_완료_확인(Rolls rolls, boolean isEnd, FrameStatus result) {
        NormalFrame frame = new NormalFrame(rolls);
        assertThat(frame.end()).isEqualTo(isEnd);
        assertThat(frame.getResult()).isEqualTo(result);
    }

    @Test
    void 입력값_프레임에_추가() {
        NormalFrame frame = new NormalFrame();
        frame.addScore(new Pin(10));
        assertThat(frame.getScores().getScores()).contains(new Pin(10));
    }
}
