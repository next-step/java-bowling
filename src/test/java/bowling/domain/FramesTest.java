package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FramesTest {

    @ParameterizedTest
    @MethodSource("provideFrameIndex")
    @DisplayName("프레임 일급 컬랙션 생성 및 초기화 - 0~8 : NormalFrame, 9 : FinalFrame")
    void create(int index, Frame expected) {
        Frames frames = new Frames();
        assertThat(frames).isEqualTo(new Frames());
        assertThat(frames.getFrames()).size().isEqualTo(10);
        assertThat(frames.getFrames().get(index)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideFrameIndex() {
        return Stream.of(
                Arguments.of(0, new NormalFrame()),
                Arguments.of(9, new FinalFrame())
        );
    }

    @Test
    @DisplayName("현재 프레임 넘버에 해당하는 프레임에 첫번째 타구에서 맞춘 값 저장")
    void addPinsEachFrame() {
        Frames frames = new Frames();
        frames.addPinsCurrentFrame(5);
        NormalFrame currentFrame = (NormalFrame) frames.getFrames().get(0);
        assertThat(currentFrame.getBowlingPins().get(0)).isEqualTo(new BowlingPins(5));
        assertThat(currentFrame.getBowlingPins()).size().isEqualTo(1);
    }

    @Test
    @DisplayName("현재 프레임 넘버에 해당하는 프레임에 두번째까지 맞춘 타격 핀 저장")
    void addPinsEachFrame1() {
        Frames frames = new Frames();
        frames.addPinsCurrentFrame(7);
        frames.addPinsCurrentFrame(3);
        NormalFrame currentFrame = (NormalFrame) frames.getFrames().get(0);
        assertThat(currentFrame.getBowlingPins().get(0)).isEqualTo(new BowlingPins(7));
        assertThat(currentFrame.getBowlingPins().get(1)).isEqualTo(new BowlingPins(3));
    }

    @Test
    @DisplayName("현재 프레임에서 다음 프레임으로 이동 1 -> 2")
    void nextFrame() {
        Frames frames = new Frames();
        frames.moveNextFrame();
        assertThat(frames.getFrameNumber()).isEqualTo(2);
    }

}