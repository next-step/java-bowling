package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.FirstThrown;
import bowling.model.frame.state.SecondThrown;
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
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("일반 프레임")
class NormalFrameTest {

    @Test
    @DisplayName("프레임 번호는 초기 상태로 생성")
    void instance() {
        assertThatNoException().isThrownBy(() -> NormalFrame.init(FrameNumber.FIRST));
    }

    @Test
    @DisplayName("프레임 번호와 상태로 생성")
    void instance_numberAndState() {
        assertThatNoException().isThrownBy(() -> NormalFrame.of(FrameNumber.FIRST, FrameState.init()));
    }

    @Test
    @DisplayName("프레임 번호와 상태는 필수")
    void instance_nullNumber_thrownIllegalArgumentException() {
        assertAll(
                () -> assertThatIllegalArgumentException().isThrownBy(() -> NormalFrame.init(null)),
                () -> assertThatIllegalArgumentException().isThrownBy(() -> NormalFrame.of(FrameNumber.FIRST, null)),
                () -> assertThatIllegalArgumentException().isThrownBy(() -> NormalFrame.of(null, FrameState.init()))
        );
    }

    @Test
    @DisplayName("마지막 번호로 생성 불가")
    void instance_lastNumber_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> NormalFrame.init(FrameNumber.LAST));
    }

    @ParameterizedTest(name = "[{index}] 8 프레임에서 {0} 만큼 던지면 다음 프레임은 {1}")
    @DisplayName("8프레임의 다음")
    @MethodSource
    void next(List<Pins> pinsGroup, Frame expected) {
        //when
        Frame nextFrame = pinsGroup.stream()
                .reduce(NormalFrame.init(FrameNumber.from(8)), Frame::next, (frame1, frame2) -> frame2);
        //then
        assertThat(nextFrame).isEqualTo(expected);
    }

    @Test
    @DisplayName("상태에 따른 종료 여부")
    void isEnd() {
        assertAll(
                () -> assertThat(NormalFrame.of(FrameNumber.FIRST, FrameState.init()).isEnd()).isFalse(),
                () -> assertThat(NormalFrame.of(FrameNumber.FIRST, FrameState.from(Strike.INSTANCE)).isEnd()).isTrue()
        );
    }

    @Test
    @DisplayName("마지막 여부는 항상 false")
    void isFinal() {
        assertThat(NormalFrame.init(FrameNumber.FIRST).isFinal()).isFalse();
    }

    @Test
    @DisplayName("주어진 프레임 번호는 그대로 반환")
    void number() {
        //given
        FrameNumber number = FrameNumber.FIRST;
        //when, then
        assertThat(NormalFrame.init(number).number()).isEqualTo(number);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("상태에 따른 남은 갯수 소유 여부")
    void hasRemainCount(FrameState state, boolean expected) {
        assertThat(NormalFrame.of(FrameNumber.FIRST, state).hasRemainCount()).isEqualTo(expected);
    }

    @Test
    @DisplayName("기본 점수에 10점 추가하면 10점 스코어의 프레임")
    void addScore() {
        assertThat(NormalFrame.of(FrameNumber.FIRST, FrameState.of(Strike.INSTANCE, Score.of(0, 1))).addScore(Pins.MAX))
                .isEqualTo(NormalFrame.of(FrameNumber.FIRST, FrameState.of(Strike.INSTANCE, Score.of(10, 0))));
    }

    @Test
    @DisplayName("주어진 상태 그대로 반환")
    void state() {
        //given
        FrameState state = FrameState.of(Strike.INSTANCE, Score.of(0, 1));
        //when, then
        assertThat(NormalFrame.of(FrameNumber.FIRST, state).state()).isEqualTo(state);
    }

    private static Stream<Arguments> next() {
        return Stream.of(
                Arguments.of(Collections.singletonList(Pins.from(1)), NormalFrame.of(FrameNumber.from(8), FrameState.from(FirstThrown.from(Pins.from(1))))),
                Arguments.of(Arrays.asList(Pins.ZERO, Pins.ZERO), NormalFrame.of(FrameNumber.from(8), FrameState.from(SecondThrown.of(Pins.ZERO, Pins.ZERO)))),
                Arguments.of(Arrays.asList(Pins.MAX, Pins.ZERO), NormalFrame.of(FrameNumber.from(9), FrameState.from(FirstThrown.from(Pins.ZERO)))),
                Arguments.of(Arrays.asList(Pins.MAX, Pins.MAX, Pins.ZERO), FinalFrame.of(FrameState.from(FirstThrown.from(Pins.ZERO)), Collections.emptyList()))
        );
    }

    private static Stream<Arguments> hasRemainCount() {
        return Stream.of(
                Arguments.of(FrameState.of(Strike.INSTANCE, Score.of(0, 1)), true),
                Arguments.of(FrameState.of(Strike.INSTANCE, Score.of(0, 0)), false)
        );
    }
}
