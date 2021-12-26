package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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
    @DisplayName("현재 프레임 넘버에 해당하는 프레임에 두번째까지 맞춘 타격 핀 저장")
    void addPinsEachFrame1() {
        Frames frames = new Frames();
        frames.addHittingPinsAtCurrentFrame(7);
        frames.addHittingPinsAtCurrentFrame(3);
        NormalFrame currentFrame = (NormalFrame) frames.getFrames().get(0);
        assertThat(currentFrame.getBowlingPins()).containsExactly(new BowlingPins(7), new BowlingPins(3));
    }

    @Test
    @DisplayName("현재 프레임에서 다음 프레임으로 이동 1 -> 2")
    void nextFrame() {
        Frames frames = new Frames();
        frames.addHittingPinsAtCurrentFrame(10);
        frames.moveNextFrame();
        assertThat(frames.getFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("마지막프레임 투구가 끝나면(게임 종료) -> false")
    void isFinalGame() {
        Frames frames = new Frames();
        for (int i = 1; i <= 9; i++) {
            frames.addHittingPinsAtCurrentFrame(10);
            frames.moveNextFrame();
        }
        frames.addHittingPinsAtCurrentFrame(10);
        frames.addHittingPinsAtCurrentFrame(10);
        frames.addHittingPinsAtCurrentFrame(10);
        frames.moveNextFrame();
        assertThat(frames.isNotFinalGame()).isFalse();
    }

    @Test
    @DisplayName("마지막프레임 투구가 끝나기 전에는 -> true")
    void isNotFinalGame() {
        Frames frames = new Frames();
        assertThat(frames.isNotFinalGame()).isTrue();
    }

    @Test
    @DisplayName("인덱스 프레임 찾기")
    void findFrame() {
        Frames frames = new Frames();
        assertThat(frames.findFrame(0)).isEqualTo(new NormalFrame());
        assertThat(frames.findFrame(9)).isEqualTo(new FinalFrame());
    }

}