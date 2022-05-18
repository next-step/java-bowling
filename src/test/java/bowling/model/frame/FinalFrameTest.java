package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.FirstThrown;
import bowling.model.frame.state.Spare;
import bowling.model.frame.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
        assertThatNoException().isThrownBy(() -> FinalFrame.from(FrameState.from(Strike.INSTANCE)));
    }

    @Test
    @DisplayName("상태는 필수")
    void instance_nullArguments_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> FinalFrame.from(null));
    }

    @ParameterizedTest(name = "[{index}] {0} 만큼 던지면 종료 여부는 {1}")
    @DisplayName("끝난 상태와 남은 개수가 없으면 종료")
    @MethodSource
    void isEnd(List<Pins> pinsGroup, boolean expected) {
        //when
        Frame finalFrame = pinsGroup.stream()
                .reduce((Frame) FinalFrame.init(), (frame, pins) -> frame.next(pins), (frame1, frame2) -> frame2);
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
    @CsvSource({"1,true", "2,false"})
    void hasRemainCount(int additionPins, boolean expected) {
        //given
        List<BallState> states = IntStream.range(0, additionPins)
                .mapToObj(i -> FirstThrown.from(Pins.ZERO)).collect(Collectors.toList());
        //when, then
        assertThat(FinalFrame.from(FrameState.of(Strike.INSTANCE, states)).hasRemainCount())
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("핀들 추가")
    void addBonusPins() {
        assertThat(FinalFrame.from(FrameState.from(Strike.INSTANCE)).addBonusPins(Pins.MAX))
                .isEqualTo(FinalFrame.from(FrameState.of(Strike.INSTANCE, Collections.singletonList(Strike.INSTANCE))));
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
    @DisplayName("합한 핀들 그대로 반환")
    void sumPins() {
        assertThat(FinalFrame.init().sumPinsCount()).isZero();
    }

    @Test
    @DisplayName("10점 3번 던지면 x|x|x 마크")
    void mark() {
        assertThat(FinalFrame.init().next(Pins.MAX).next(Pins.MAX).next(Pins.MAX).mark())
                .isEqualTo("x|x|x");
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
                Arguments.of(Collections.singletonList(Pins.MAX), FinalFrame.from(FrameState.from(Strike.INSTANCE))),
                Arguments.of(Collections.singletonList(Pins.ZERO), FinalFrame.from(FrameState.from(FirstThrown.from(Pins.ZERO)))),
                Arguments.of(Arrays.asList(Pins.MAX, Pins.from(5)), FinalFrame.from(FrameState.of(Strike.INSTANCE, Collections.singletonList(FirstThrown.from(Pins.from(5)))))),
                Arguments.of(Arrays.asList(Pins.from(1), Pins.from(9)), FinalFrame.from(FrameState.from(Spare.from(Pins.from(1)))))
        );
    }
}
