package bowling.domain.frame;

import bowling.domain.bowl.*;
import bowling.domain.pin.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static bowling.domain.score.ScoreTest.score;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FramesTest {

    Frames frames;

    @BeforeEach
    void initFrames() {
        frames = Frames.init();
    }

    private boolean pitch(int hitCount) {
        return frames.pitch(pin(hitCount));
    }

    @DisplayName("게임이 끝나는 다양한 경우")
    @ParameterizedTest(name = "[{index}] hitCounts: {0}")
    @MethodSource("hitCounts")
    void pitch_endOfGame(List<Integer> hitCounts) {
        boolean canPitchMore = true;
        for (Integer hitCount : hitCounts) {
            canPitchMore = pitch(hitCount);
        }

        assertThat(canPitchMore).isFalse();
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
        // 1프레임: 스페어
        frames.pitch(pin(2));
        frames.pitch(pin(8));

        // 2프레임: 스트라이크
        frames.pitch(Pin.allHitPin());

        // 3프레임: 미스
        frames.pitch(pin(0));
        frames.pitch(pin(1));

        // 4프레임: 스트라이크
        frames.pitch(Pin.allHitPin());

        //then
        Frame firstFrame = new Frame(1, score(20), new SpareBowl(pin(2), pin(8)));
        Frame secondFrame = new Frame(2, score(31), new StrikeBowl());
        Frame thirdFrame = new Frame(3, score(32), new MissBowl(pin(0), pin(1)));
        Frame fourthFrame = new Frame(4, score(42, 2), new StrikeBowl());
        Frame fifthFrame = new Frame(5, score(42, 0), FirstBowl.bowl());

        assertThat(frames.frames).containsExactly(firstFrame, secondFrame, thirdFrame, fourthFrame, fifthFrame);
    }

    private Pin pin(int i) {
        return Pin.from(i);
    }

}
