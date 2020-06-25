package bowling.domain.frame;

import bowling.domain.dto.StateDtos;
import bowling.domain.pin.PinCount;
import bowling.domain.state.StateExpression;
import bowling.fixture.FramesFixture;
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
import static org.assertj.core.api.Assertions.assertThatCode;

public class FramesTest {

    @DisplayName("Frames 생성 성공")
    @Test
    public void create() {
        assertThatCode(Frames::newInstance)
                .doesNotThrowAnyException();
    }

    @DisplayName("처음 생성했을 때 NormalFrame 을 리스트에 추가 및 현재 프레임 번호를 반환")
    @Test
    public void getFrameNumber() {
        assertThat(Frames.newInstance().getFrameNumber())
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
        return Stream.of(
                Arguments.of(FramesFixture.getSpareFrames(), 2),
                Arguments.of(FramesFixture.getTwoStrikeFrames(), 3),
                Arguments.of(FramesFixture.getStrikeHitFrames(), 2)
        );
    }

    @DisplayName("게임 종료 여부 판단 상태: FinalFrame 조건과 동일")
    @Test
    public void isGameOver() {
        Frames frames = Frames.newInstance();

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
    public void getFrameResult(final Frames frames, final List<StateDtos> expected) {
        IntStream.range(0, frames.getFrameResult().size())
                .forEach(index ->
                    assertThat(frames.getFrameResult().get(index).getSymbol())
                            .isEqualTo(expected.get(index))
                );
    }

    public static Stream<Arguments> getFrameResult() {
        return Stream.of(
                Arguments.of(FramesFixture.getSpareFrames(), Arrays.asList("9|/", StateExpression.READY)),
                Arguments.of(FramesFixture.getTwoStrikeFrames(), Arrays.asList("X", "X", StateExpression.READY)),
                Arguments.of(FramesFixture.getStrikeHitFrames(), Arrays.asList("X", "9"))
        );
    }
}
