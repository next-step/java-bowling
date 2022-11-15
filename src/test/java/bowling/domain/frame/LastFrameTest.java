package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class LastFrameTest {
    @DisplayName("보너스 점수를 얻을 수 있는 경우에 대한 테스트")
    @ParameterizedTest(name = "{3}")
    @CsvSource({
        "10,10,10,3 Strike",
        "10,10,5,2 Strike & 1 Bonus",
        "10,4,6,1 Strike & 1 Spare",
        "10,4,4,1 Strike & 1 Miss",
        "5,5,10,1 Spare & 1 Strike",
        "5,5,4,1 Spare & 1 Bonus"
    })
    void testThreeBowls(int firstPins, int secondPins, int thirdPins, String description) {
        Frame frame = new LastFrame();
        frame.bowl(firstPins);
        assertThat(frame.isFrameFinish()).isFalse();
        frame.bowl(secondPins);
        assertThat(frame.isFrameFinish()).isFalse();
        frame.bowl(thirdPins);
        assertThat(frame.isFrameFinish()).isTrue();
    }

    @Test
    @DisplayName("Miss로 끝날 경우 보너스 기회가 주어지지 않는다.")
    void testMissBowl() {
        Frame frame = new LastFrame();
        frame.bowl(4);
        assertThat(frame.isFrameFinish()).isFalse();
        frame.bowl(5);
        assertThat(frame.isFrameFinish()).isTrue();
    }

    @DisplayName("프레임 종료 후에도 볼링 경기를 시도할 시 예외를 던진다.")
    @ParameterizedTest(name = "pins={0}")
    @MethodSource("provideThrowingExceptionSource")
    void bowlAfterFrameFinished(List<Integer> pinsList) {
        Frame frame = new LastFrame();
        pinsList.forEach(frame::bowl);
        assertThatThrownBy(() -> frame.bowl(6))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("이미 종료된 프레임입니다");
    }

    @Test
    @DisplayName("마지막 프레임에서는 다음 프레임을 생성할 수 없다.")
    void createInvalidNextFrame() {
        assertThatThrownBy(() -> new LastFrame().createNextFrame())
            .isInstanceOf(UnsupportedOperationException.class);
    }

    private static Stream<Arguments> provideThrowingExceptionSource() {
        return Stream.of(
            Arguments.of(List.of(10, 10, 10)),
            Arguments.of(List.of(4, 5))
        );
    }
}
