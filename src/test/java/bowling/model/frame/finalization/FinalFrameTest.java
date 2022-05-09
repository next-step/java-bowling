package bowling.model.frame.finalization;

import bowling.model.Pins;
import bowling.model.frame.Frame;
import bowling.model.frame.FrameNumber;
import bowling.model.frame.state.FirstThrown;
import bowling.model.frame.state.NotThrown;
import bowling.model.frame.state.Spare;
import bowling.model.frame.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("마지막 프레임")
class FinalFrameTest {

    @Test
    @DisplayName("초기 상태로 생성")
    void instance() {
        assertThatNoException().isThrownBy(FinalFrame::init);
    }

    @Test
    @DisplayName("상태로 생성")
    void instance_state() {
        assertThatNoException().isThrownBy(() -> FinalFrame.from(Strike.instance()));
    }

    @Test
    @DisplayName("상태는 필수")
    void instance_nullState_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> FinalFrame.from(null));
    }

    @ParameterizedTest(name = "[{index}] {0} 만큼 던지면 다음 프레임은 {1}")
    @DisplayName("다음 프레임")
    @MethodSource
    void next(List<Pins> pinsGroup, Frame expected) {
        //when
        Frame finalFrame = pinsGroup.stream()
                .reduce(FinalFrame.init(), Frame::next, (frame1, frame2) -> frame2);
        //then
        assertThat(finalFrame).isEqualTo(expected);
    }

    @Test
    @DisplayName("종료된 상태에서 다음 프레임 생성 불가")
    void next_endedFrame_thrownIllegalStateException() {
        //given
        Frame endedFrame = FinalFrame.init().next(Pins.ZERO).next(Pins.ZERO);
        //when, then
        assertThatIllegalStateException().isThrownBy(() -> endedFrame.next(Pins.MAX));
    }

    @ParameterizedTest(name = "[{index}] {0} 만큼 던지면 종료 여부는 {1}")
    @DisplayName("종료 여부")
    @MethodSource
    void isEnd(List<Pins> pinsGroup, boolean expected) {
        //when
        Frame finalFrame = pinsGroup.stream()
                .reduce(FinalFrame.init(), Frame::next, (frame1, frame2) -> frame2);
        //then
        assertThat(finalFrame.isEnd()).isEqualTo(expected);
    }

    @Test
    @DisplayName("마지막 여부는 항상 true")
    void isFinal() {
        assertThat(FinalFrame.init().isFinal()).isTrue();
    }

    @Test
    @DisplayName("프레임 번호는 10")
    void number() {
        assertThat(FinalFrame.init().number()).isEqualTo(FrameNumber.LAST);
    }

    @Test
    @DisplayName("현재 상태 그대로 반환")
    void state() {
        assertThat(FinalFrame.init().state()).isEqualTo(NotThrown.instance());
    }

    private static Stream<Arguments> isEnd() {
        return Stream.of(
                Arguments.of(Collections.singletonList(Pins.MAX), false),
                Arguments.of(Arrays.asList(Pins.ZERO, Pins.ZERO), true),
                Arguments.of(Arrays.asList(Pins.MAX, Pins.from(5)), false),
                Arguments.of(Arrays.asList(Pins.from(1), Pins.from(9)), false)
        );
    }

    private static Stream<Arguments> next() {
        return Stream.of(
                Arguments.of(Collections.singletonList(Pins.MAX), FinalFrame.from(Strike.instance())),
                Arguments.of(Collections.singletonList(Pins.ZERO), FinalFrame.from(FirstThrown.from(Pins.ZERO))),
                Arguments.of(Arrays.asList(Pins.MAX, Pins.from(5)), FinalFrame.from(BonusThrown.of(BonusHit.of(Strike.instance(), Pins.from(5)), 1))),
                Arguments.of(Arrays.asList(Pins.from(1), Pins.from(9)), FinalFrame.from(Spare.from(Pins.from(1))))
        );
    }
}
