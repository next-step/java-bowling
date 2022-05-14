package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.FirstThrown;
import bowling.model.frame.state.Spare;
import bowling.model.frame.state.Strike;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
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
    @DisplayName("상태와 추가 핀들로 생성")
    void instance_state() {
        assertThatNoException().isThrownBy(() -> FinalFrame.of(FrameState.from(Strike.INSTANCE), Collections.singletonList(Pins.ZERO)));
    }

    @Test
    @DisplayName("상태와 추가 핀들은 필수")
    void instance_nullArguments_thrownIllegalArgumentException() {
        Assertions.assertAll(
                () -> assertThatIllegalArgumentException().isThrownBy(() -> FinalFrame.of(null, Collections.singletonList(Pins.ZERO))),
                () -> assertThatIllegalArgumentException().isThrownBy(() -> FinalFrame.of(FrameState.from(Strike.INSTANCE), null))
        );
    }

    @ParameterizedTest(name = "[{index}] {0} 만큼 던지면 종료 여부는 {1}")
    @DisplayName("끝난 상태와 남은 개수가 없으면 종료")
    @MethodSource
    void isEnd(List<Pins> pinsGroup, boolean expected) {
        //when
        Frame finalFrame = pinsGroup.stream()
                .reduce((Frame) FinalFrame.init(), (frame, pins) -> frame.addScore(pins).next(pins), (frame1, frame2) -> frame2);
        //then
        assertThat(finalFrame.isEnd()).isEqualTo(expected);
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

    @ParameterizedTest
    @DisplayName("남은 개수 소유 여부")
    @CsvSource({"0,false", "1,true"})
    void hasRemainCount(int remainCount, boolean expected) {
        assertThat(FinalFrame.of(FrameState.of(Strike.INSTANCE, Score.of(0, remainCount)), Collections.emptyList()).hasRemainCount())
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("점수 추가")
    void addScore() {
        assertThat(FinalFrame.of(FrameState.of(Strike.INSTANCE, Score.of(0, 1)), Collections.emptyList()).addScore(Pins.MAX))
                .isEqualTo(FinalFrame.of(FrameState.of(Strike.INSTANCE, Score.of(10, 0)), Collections.emptyList()));
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
        assertThat(FinalFrame.init().state()).isEqualTo(FrameState.init());
    }

    @Test
    @DisplayName("현재 추가 핀 그대로 반환")
    void additionHitPinsGroup() {
        assertThat(FinalFrame.init().additionHitPinsGroup()).isEqualTo(Collections.emptyList());
    }

    private static Stream<Arguments> isEnd() {
        return Stream.of(
                Arguments.of(Collections.singletonList(Pins.MAX), false),
                Arguments.of(Arrays.asList(Pins.ZERO, Pins.ZERO), true),
                Arguments.of(Arrays.asList(Pins.MAX, Pins.from(5)), false),
                Arguments.of(Arrays.asList(Pins.MAX, Pins.from(5), Pins.from(5)), true),
                Arguments.of(Arrays.asList(Pins.from(1), Pins.from(9)), false),
                Arguments.of(Arrays.asList(Pins.from(1), Pins.from(9), Pins.MAX), true)
        );
    }

    private static Stream<Arguments> next() {
        return Stream.of(
                Arguments.of(Collections.singletonList(Pins.MAX), FinalFrame.of(FrameState.from(Strike.INSTANCE), Collections.emptyList())),
                Arguments.of(Collections.singletonList(Pins.ZERO), FinalFrame.of(FrameState.from(FirstThrown.from(Pins.ZERO)), Collections.emptyList())),
                Arguments.of(Arrays.asList(Pins.MAX, Pins.from(5)), FinalFrame.of(FrameState.of(Strike.INSTANCE, Score.of(0, 2)), Collections.singletonList(Pins.from(5)))),
                Arguments.of(Arrays.asList(Pins.from(1), Pins.from(9)), FinalFrame.of(FrameState.from(Spare.from(Pins.from(1))), Collections.emptyList()))
        );
    }
}
