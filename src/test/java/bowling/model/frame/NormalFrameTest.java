package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.finalization.FinalFrame;
import bowling.model.frame.state.FirstThrown;
import bowling.model.frame.state.NotThrown;
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
        assertThatNoException().isThrownBy(() -> NormalFrame.of(FrameNumber.FIRST, Strike.instance()));
    }

    @Test
    @DisplayName("프레임 번호와 상태는 필수")
    void instance_nullNumber_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> NormalFrame.init(null));
        assertThatIllegalArgumentException().isThrownBy(() -> NormalFrame.of(FrameNumber.FIRST, null));
        assertThatIllegalArgumentException().isThrownBy(() -> NormalFrame.of(null, Strike.instance()));
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
        assertThat(NormalFrame.of(FrameNumber.FIRST, Strike.instance()).isEnd()).isTrue();
        assertThat(NormalFrame.of(FrameNumber.FIRST, FirstThrown.from(Pins.ZERO)).isEnd()).isFalse();
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

    @Test
    @DisplayName("현재 상태 그대로 반환")
    void state() {
        assertThat(FinalFrame.init().state()).isEqualTo(NotThrown.instance());
    }

    private static Stream<Arguments> next() {
        return Stream.of(
                Arguments.of(Collections.singletonList(Pins.from(1)), NormalFrame.of(FrameNumber.from(8), FirstThrown.from(Pins.from(1)))),
                Arguments.of(Arrays.asList(Pins.ZERO, Pins.ZERO), NormalFrame.of(FrameNumber.from(8), SecondThrown.of(Pins.ZERO, Pins.ZERO))),
                Arguments.of(Arrays.asList(Pins.MAX, Pins.ZERO), NormalFrame.of(FrameNumber.from(9), FirstThrown.from(Pins.ZERO))),
                Arguments.of(Arrays.asList(Pins.MAX, Pins.MAX, Pins.ZERO), FinalFrame.from(FirstThrown.from(Pins.ZERO)))
        );
    }
}
