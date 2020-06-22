package bowling.domain.frame;

import bowling.domain.dto.FrameResult;
import bowling.domain.pin.PinCount;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    @DisplayName("처음 생성했을 때 NormalFrame 을 리스트에 추가")
    @Test
    public void create() {
        assertThat(Frames.of().isSameCurrentFrame(NormalFrame.ofFirst()))
                .isTrue();
    }

    @DisplayName("현재 프레임 번호를 반환")
    @Test
    public void getFrameNumber() {
        assertThat(Frames.of().getFrameNumber())
                .isEqualTo(FrameNumber.MIN_NUMBER);
    }

    @DisplayName("공을 던진 후 변경된 상태를 가지는 프레임을 추가")
    @ParameterizedTest
    @MethodSource
    public void bowl(final Frames frames, final int expected) {
        assertThat(frames.getFrameNumber())
                .isEqualTo(expected);
    }

    public static Stream<Arguments> bowl() {
        Frames frames1 = Frames.of();
        frames1.bowl(PinCount.of(9));
        frames1.bowl(PinCount.of(1));

        Frames frames2 = Frames.of();
        frames2.bowl(PinCount.of(PinCount.MAX_COUNT));
        frames2.bowl(PinCount.of(PinCount.MAX_COUNT));

        Frames frames3 = Frames.of();
        frames3.bowl(PinCount.of(PinCount.MAX_COUNT));
        frames3.bowl(PinCount.of(9));

        return Stream.of(
                Arguments.of(frames1, 2),
                Arguments.of(frames2, 3),
                Arguments.of(frames3, 2)
        );
    }

    @DisplayName("게임 종료 여부 판단 상태: FinalFrame 조건과 동일")
    @Test
    public void isGameOver() {
        Frames frames = Frames.of();

        IntStream.range(0, 11)
                .forEach(index -> {
                    frames.bowl(PinCount.of(PinCount.MAX_COUNT));
                    assertThat(frames.isGameOver()).isFalse();
                });

        frames.bowl(PinCount.of(PinCount.MAX_COUNT));
        assertThat(frames.isGameOver()).isTrue();
    }

    @DisplayName("모든 프레임의 상태값을 반환")
    @ParameterizedTest
    @MethodSource
    public void getFrameResult(final Frames frames, final List<FrameResult> expected) {
        assertThat(frames.getFrameResult())
                .isEqualTo(expected);
    }

    public static Stream<Arguments> getFrameResult() {
        Frames frames1 = Frames.of();
        frames1.bowl(PinCount.of(9));
        frames1.bowl(PinCount.of(1));

        Frames frames2 = Frames.of();
        PinCount maxCount = PinCount.of(PinCount.MAX_COUNT);
        frames2.bowl(maxCount);
        frames2.bowl(maxCount);

        Frames frames3 = Frames.of();
        frames3.bowl(maxCount);
        frames3.bowl(PinCount.of(9));

        return Stream.of(
                Arguments.of(frames1, Arrays.asList(FrameResult.of("9|/"), FrameResult.of(""))),
                Arguments.of(frames2, Arrays.asList(FrameResult.of("X"), FrameResult.of("X"), FrameResult.of(""))),
                Arguments.of(frames3, Arrays.asList(FrameResult.of("X"), FrameResult.of("9 ")))
        );
    }
}
