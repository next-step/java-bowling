package bowling.domain.frame;

import bowling.domain.bowl.CanNotPitchException;
import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FramesTest {

    Frames frames;

    @BeforeEach
    void initFrames() {
        frames = Frames.init();
    }

    private void pitch(int hitCount) {
        frames.pitch(pin(hitCount));
    }

    private Pin pin(int i) {
        return Pin.from(i);
    }

    @DisplayName("게임이 끝나는 다양한 경우")
    @ParameterizedTest(name = "[{index}] hitCounts: {0}")
    @MethodSource("hitCounts")
    void pitch_endOfGame(List<Integer> hitCounts) {
        for (Integer hitCount : hitCounts) {
            pitch(hitCount);
        }

        assertThatThrownBy(() -> pitch(1)).isInstanceOf(CanNotPitchException.class);
    }

    public static Stream<Arguments> hitCounts() {
        // 세프레임 단위로 주석
        return Stream.of(
                Arguments.of(asList(10, 10, 10,/**/ 10, 10, 10,/**/ 10, 10, 10,/**/ 10, 10, 10)),
                Arguments.of(asList(10, 10, 1, 2,/**/ 10, 1, 2, 10,/**/ 10, 1, 2, 0, 2,/**/ 10, 0, 3)),
                Arguments.of(asList(0, 0, 1, 2, 3, 4,/**/ 10, 10, 10,/**/ 10, 10, 10,/**/ 9, 1, 3)),
                Arguments.of(asList(1, 9, 10, 3, 5,/**/ 1, 9, 10, 10,/**/ 10, 3, 6, 10,/**/ 1, 2)),
                Arguments.of(asList(1, 9, 1, 0, 8, 1,/**/ 2, 6, 0, 1, 9, 1,/**/ 3, 6, 10, 10,/**/ 0, 10, 0))
        );
    }

    @DisplayName("투구할 때 이전프레임의 점수를 계산한다.")
    @Test
    void pitch_calculatePreviousScore() {
        //when
        // 1프레임: 스트라이크
        frames.pitch(Pin.allHitPin());

        // 2프레임: 스트라이크
        frames.pitch(Pin.allHitPin());

        // 3프레임: 미스
        frames.pitch(pin(3));
        frames.pitch(pin(5));

        // 4프레임: 스트라이크
        frames.pitch(Pin.allHitPin());

        //then
        List<Score> scores = frames.scores();
        assertThat(scores.get(0).value()).isEqualTo(23);
        assertThat(scores.get(1).value()).isEqualTo(41);
        assertThat(scores.get(2).value()).isEqualTo(49);
    }

}
