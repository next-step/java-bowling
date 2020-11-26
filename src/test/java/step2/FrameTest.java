package step2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import step2.domain.Frame;
import step2.domain.NormalFrame;
import step2.domain.StrategyTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameTest {

    @DisplayName("프레임 투구 테스트")
    @Test
    void pitches() {
        NormalFrame frame = new NormalFrame(1);
        frame.pitches(StrategyTest.T4);
        assertThat(frame.getFirstScore()).isEqualTo(frame.getCurrentScore());

    }

    @DisplayName("프레임 투구 예외 테스트")
    @Test
    void pitchesWithException() {
        NormalFrame frame = new NormalFrame(1);
        assertThatThrownBy(()->{
            frame.pitches(StrategyTest.T9);
            frame.pitches(StrategyTest.T9);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NormalFrame.ERROR_INVALID_SHOT_POINT);
    }

    @DisplayName("프레임 결과 출력 테스트")
    @ParameterizedTest
    @MethodSource("provideFrameAndString")
    void toStringFrame(Frame frame, String result) {
        System.out.println(frame.getResultString());
        assertThat(frame.getResultString()).isEqualTo(result);
    }

    private static Stream<Arguments> provideFrameAndString() {
        Frame strikeFrame = new NormalFrame(1);
        strikeFrame.pitches(StrategyTest.T10);

        Frame spareFrame = new NormalFrame(2);
        spareFrame.pitches(StrategyTest.T7);
        spareFrame.pitches(StrategyTest.T3);

        Frame missFrame = new NormalFrame(3);
        missFrame.pitches(StrategyTest.T6);
        missFrame.pitches(StrategyTest.T3);

        Frame prevGutterFrame = new NormalFrame(4);
        prevGutterFrame.pitches(StrategyTest.T0);
        prevGutterFrame.pitches(StrategyTest.T5);

        Frame afterGutterFrame = new NormalFrame(4);
        afterGutterFrame.pitches(StrategyTest.T5);
        afterGutterFrame.pitches(StrategyTest.T0);

        return Stream.of(
                Arguments.of(strikeFrame, "X"),
                Arguments.of(spareFrame, "7|/"),
                Arguments.of(missFrame, "6|3"),
                Arguments.of(prevGutterFrame, "-|5"),
                Arguments.of(afterGutterFrame, "5|-")
        );
    }

}
