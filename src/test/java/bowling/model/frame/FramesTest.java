package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("프레임들")
class FramesTest {

    @Test
    @DisplayName("초기 상태로 생성")
    void instance() {
        assertThatNoException().isThrownBy(Frames::init);
    }

    @Test
    @DisplayName("프레임들로 생성")
    void instance_frames() {
        assertThatNoException().isThrownBy(() -> Frames.from(Collections.singletonList(NormalFrame.init(FrameNumber.FIRST))));
    }

    @Test
    @DisplayName("프레임들은 필수")
    void instance_nullArgument_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Frames.from(null));
    }

    @ParameterizedTest(name = "[{index}] {0} 만큼 추가되면 {1}")
    @MethodSource
    @DisplayName("넘어진 핀이 추가된 프레임들")
    void addedFrames(List<Pins> pinsGroup, Frames expected) {
        //when
        Frames thrownFrames = pinsGroup.stream()
                .reduce(Frames.init(), Frames::addedFrames, (frames1, frames2) -> frames2);
        //then
        assertThat(thrownFrames).isEqualTo(expected);
    }

    @Test
    @DisplayName("종료된 프레임들에 핀 추가 불가")
    void addedFrames_endedFrames_thrownIllegalStateArgument() {
        //given
        List<Pins> pinsGroup = IntStream.range(0, 20)
                .mapToObj(i -> Pins.ZERO)
                .collect(Collectors.toList());
        Frames endedFrames = pinsGroup.stream()
                .reduce(Frames.init(), Frames::addedFrames, (frames1, frames2) -> frames2);
        //when, then
        assertThatIllegalStateException().isThrownBy(() -> endedFrames.addedFrames(Pins.MAX));
    }

    @ParameterizedTest(name = "[{index}] {0} 만큼 추가되면 종료 여부는 {1}")
    @MethodSource
    @DisplayName("프레임들 종료 여부")
    void isFinished(List<Pins> pinsGroup, boolean expected) {
        //when
        Frames thrownFrames = pinsGroup.stream()
                .reduce(Frames.init(), Frames::addedFrames, (frames1, frames2) -> frames2);
        //then
        assertThat(thrownFrames.isFinished()).isEqualTo(expected);
    }

    @Test
    @DisplayName("초기 상태의 다음 프레임 번호는 1")
    void nextFrameNumber() {
        assertThat(Frames.init().nextFrameNumber()).isEqualTo(FrameNumber.FIRST);
    }

    @Test
    @DisplayName("마지막 프레임이 끝나면 다음 프레임 번호는 +1")
    void nextFrameNumber_endedFrame() {
        //given
        FrameNumber frameNumber = FrameNumber.FIRST;
        //when, then
        assertThat(Frames.from(Collections.singletonList(NormalFrame.of(frameNumber, Strike.instance()))).nextFrameNumber())
                .isEqualTo(frameNumber.increase());
    }

    @Test
    @DisplayName("주어진 리스트 그대로 반환")
    void list() {
        //given
        List<Frame> firstFrames = Collections.singletonList(NormalFrame.init(FrameNumber.FIRST));
        //when, then
        assertThat(Frames.from(firstFrames).list()).isEqualTo(firstFrames);
    }

    private static Stream<Arguments> addedFrames() {
        return Stream.of(
                Arguments.of(Collections.singletonList(Pins.MAX), Frames.from(Collections.singletonList(NormalFrame.of(FrameNumber.FIRST, Strike.instance())))),
                Arguments.of(Arrays.asList(Pins.MAX, Pins.MAX), Frames.from(Arrays.asList(NormalFrame.of(FrameNumber.FIRST, Strike.instance()), NormalFrame.of(FrameNumber.from(2), Strike.instance()))))
        );
    }

    private static Stream<Arguments> isFinished() {
        return Stream.of(
                Arguments.of(Collections.emptyList(), false),
                Arguments.of(Collections.singletonList(Pins.MAX), false),
                Arguments.of(IntStream.range(0, 20).mapToObj(i -> Pins.ZERO).collect(Collectors.toList()), true)
        );
    }
}
