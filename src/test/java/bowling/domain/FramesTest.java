package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.exception.IllegalFramesException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class FramesTest {
    @Test
    @DisplayName("프레임 생성을 확인한다")
    void checkedFramesObjectGenerate() {
        // given & when
        Frames frames = Frames.create();

        // then
        assertThat(frames.getValues()).hasSize(1);
    }

    @ParameterizedTest(name = "프레임들이 {0}인 경우, 예외처리를 한다.")
    @NullAndEmptySource
    void exceptionFramesIsNullAndEmpty(List<Frame> values) {
        // when & then
        assertThatThrownBy(() -> Frames.create(values))
                .isInstanceOf(IllegalFramesException.class);
    }

    @Test
    @DisplayName("프레임들의 크기가 10을 초과하는 경우, 예외처리를 한다.")
    void exceptionFramesSizeOverBoundary() {
        // given
        List<Frame> values = new ArrayList<>(11);

        // when & then
        assertThatThrownBy(() -> Frames.create(values))
                .isInstanceOf(IllegalFramesException.class);
    }

    @Test
    @DisplayName("첫 투구 시, 스트라이크가 아니면 현재 프레임이 유지된다")
    void checkedCurrentFrameIsNotStrike() {
        // given
        Frames frames = Frames.create();

        // when
        frames.bowl(new Pitching(3));

        // then
        assertThat(frames.getValues()).hasSize(1);
    }

    @Test
    @DisplayName("프레임이 종료되는 경우, 다음 프레임이 생성된다")
    void checkedNextFrameGenerateForCurrentFrameIsEnd() {
        // given
        Frames frames = Frames.create();

        // when
        frames.bowl(new Pitching(10));

        // then
        assertThat(frames.getValues()).hasSize(2);
    }

    @Test
    @DisplayName("현재 프레임에서 다음 투구가 가능한지 확인한다")
    void checkedNextPitchingIsPossibleAtCurrentFrame() {
        // given
        Frames frames = Frames.create();

        // when
        boolean result = frames.isNextPitching();

        // then
        assertThat(result).isTrue();
    }
}
