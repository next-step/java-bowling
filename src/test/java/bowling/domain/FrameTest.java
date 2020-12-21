package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.score.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Created By mand2 on 2020-12-18.
 */
public class FrameTest {

    @ParameterizedTest
    @DisplayName("1~9프레임 생성자")
    @ValueSource(ints = {1, 2, 8, 9})
    void createNormalFrame(int frameIndex) {
        Frame frame = NormalFrame.of(frameIndex);
        assertThat(frame).isEqualTo(NormalFrame.of(frameIndex));
    }

    @Test
    @DisplayName("1~9프레임 생성시 입력가능한 프레임 번호를 벗어나면 예외처리")
    void createNormalFrameRangeException() {
        assertThatThrownBy(() -> NormalFrame.of(10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NormalFrame.ERROR_MESSAGE_FRAME_INDEX_RANGE);
    }

    @Test
    @DisplayName("10프레임 생성자")
    void createFinalFrame() {
        int frameIndex = 10;
        Frame frame = FinalFrame.init();
        assertThat(frame).isEqualTo(FinalFrame.init());
    }

    @ParameterizedTest
    @DisplayName("10프레임 생성시 입력가능한 프레임 번호를 벗어나면 예외처리")
    @ValueSource(ints = {1, 2, 8, 9})
    void createFinalFrameRangeException(int frameIndex) {
        assertThatThrownBy(() -> FinalFrame.init())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(FinalFrame.ERROR_MESSAGE_FINAL_FRAME_INDEX);
    }


    @ParameterizedTest
    @DisplayName("1~9프레임 1차 시도=스트라이크")
    @ValueSource(ints = {1, 2, 8, 9})
    void createStrikeInNormalFrame(int frameIndex) {
        // given
        Frame frame = NormalFrame.of(frameIndex);

        // when
        frame.pitch(Pin.from(10));

        // then
        assertThat(frame.isPlayable()).isFalse();
    }



    private static Stream<Arguments> providedSpare() {
        return Stream.of(
                arguments(1, Pin.from(4), Pin.from(6)),
                arguments(3, Pin.from(1), Pin.from(9)),
                arguments(7, Pin.from(8), Pin.from(2)),
                arguments(9, Pin.from(9), Pin.from(1))
        );
    }

    @ParameterizedTest
    @DisplayName("1~9프레임 2차 시도=스페어")
    @MethodSource("providedSpare")
    void createSpareInNormalFrame(int frameIndex, Pin first, Pin second) {
        // given
        Frame frame = NormalFrame.of(frameIndex);

        // 1 차시도
        frame.pitch(first);
        assertThat(frame.isPlayable()).isTrue();

        // 2 차시도
        frame.pitch(second);
        assertThat(frame.isPlayable()).isFalse();
    }

    @ParameterizedTest
    @DisplayName("10프레임 스페어 후 보너스 게임")
    @MethodSource("providedSpare")
    void createSpareInFinalFrame(int bonusScore, Pin first, Pin second) {
        // given
        Frame frame = FinalFrame.init();

        // 1 차시도
        frame.pitch(first);
        assertThat(frame.isPlayable()).isTrue();

        // 2 차시도
        frame.pitch(second);
        assertThat(frame.isPlayable()).isTrue();
        assertThat(frame.isEnd()).isFalse();

        // 보너스
        frame.pitch(Pin.from(bonusScore));
        assertThat(frame.isEnd()).isTrue();
    }


    private static Stream<Arguments> providedMiss() {
        return Stream.of(
                arguments(Pin.from(4), Pin.from(4)),
                arguments(Pin.from(1), Pin.from(5)),
                arguments(Pin.from(8), Pin.from(1)),
                arguments(Pin.from(9), Pin.from(0))
        );
    }
    @ParameterizedTest
    @DisplayName("10프레임 miss 후 게임 끝나는지 확인")
    @MethodSource("providedMiss")
    void createMissInFinalFrame(Pin first, Pin second) {
        // given
        Frame frame = FinalFrame.init();

        // 1 차시도
        frame.pitch(first);
        assertThat(frame.isPlayable()).isTrue();

        // 2 차시도
        frame.pitch(second);
        assertThat(frame.isPlayable()).isFalse();
        assertThat(frame.isEnd()).isTrue();

    }



    private static Stream<Arguments> providedStrike() {
        return Stream.of(
                arguments(Pin.from(10), Pin.from(10)),
                arguments(Pin.from(10), Pin.from(10)),
                arguments(Pin.from(10), Pin.from(10)),
                arguments(Pin.from(10), Pin.from(10))
        );
    }

    @ParameterizedTest
    @DisplayName("10프레임 3 스트라이크 후 게임 끝나는지 확인")
    @MethodSource("providedStrike")
    void createStrikeInFinalFrame(Pin first, Pin second) {
        // given
        Frame frame = FinalFrame.init();

        // 1 차시도
        frame.pitch(first);
        assertThat(frame.isPlayable()).isTrue();
        assertThat(frame.isEnd()).isFalse();

        // 2 차시도
        frame.pitch(second);
        assertThat(frame.isPlayable()).isTrue();
        assertThat(frame.isEnd()).isFalse();

        // 3 차시도
        frame.pitch(second);
        assertThat(frame.isPlayable()).isTrue();
        assertThat(frame.isEnd()).isTrue();

    }


    private static Stream<Arguments> providedStrikeAndMiss() {
        return Stream.of(
                arguments(Pin.from(10), Pin.from(2), Pin.from(10)),
                arguments(Pin.from(10), Pin.from(3), Pin.from(8)),
                arguments(Pin.from(10), Pin.from(4), Pin.from(0)),
                arguments(Pin.from(10), Pin.from(9), Pin.from(1))
        );
    }

    @ParameterizedTest
    @DisplayName("10프레임 스트라이크 후 miss, 보너스 게임 추가되는지 확인")
    @MethodSource("providedStrikeAndMiss")
    void createStrikeAndMissInFinalFrame(Pin first, Pin second, Pin third) {
        // given
        Frame frame = FinalFrame.init();

        // 1 차시도
        frame.pitch(first);
        assertThat(frame.isPlayable()).isTrue();
        assertThat(frame.isEnd()).isFalse();

        // 2 차시도
        frame.pitch(second);
        assertThat(frame.isPlayable()).isTrue();
        assertThat(frame.isEnd()).isFalse();

        // 3 차시도
        frame.pitch(third);
        assertThat(frame.isPlayable()).isTrue();
        assertThat(frame.isEnd()).isTrue();

    }



}
