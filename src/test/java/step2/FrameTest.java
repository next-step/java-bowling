package step2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import step2.domain.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameTest {

    @DisplayName("프레임 투구 테스트")
    @Test
    void pitches() {
        NormalFrame frame = NormalFrame.of(1);
        frame.pitches(StrategyTest.T4);
        assertThat(frame.getScore()).isEqualTo(frame.getCurrentScore());

    }

    @DisplayName("프레임 투구 예외 테스트")
    @Test
    void pitchesWithException() {
        NormalFrame frame = NormalFrame.of(1);
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
        Frame strikeFrame = NormalFrame.of(1);
        strikeFrame.pitches(StrategyTest.T10);

        Frame spareFrame = NormalFrame.of(2);
        spareFrame.pitches(StrategyTest.T7);
        spareFrame.pitches(StrategyTest.T3);

        Frame missFrame = NormalFrame.of(3);
        missFrame.pitches(StrategyTest.T6);
        missFrame.pitches(StrategyTest.T3);

        Frame prevGutterFrame = NormalFrame.of(4);
        prevGutterFrame.pitches(StrategyTest.T0);
        prevGutterFrame.pitches(StrategyTest.T5);

        Frame afterGutterFrame = NormalFrame.of(4);
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

    @DisplayName("프레임 출력결과 테스트")
    @ParameterizedTest
    @MethodSource("provideNormalFrame")
    void normalFrame(Frame frame, String result) {
        assertThat(frame.getResultString()).isEqualTo(result);
    }

    private static Stream<Arguments> provideNormalFrame() {
        NormalFrame frame = NormalFrame.of(0);
        frame.pitches(StrategyTest.T10);

        NormalFrame frame2 = NormalFrame.of(1);
        frame2.pitches(StrategyTest.T9);
        frame2.pitches(StrategyTest.T1);

        NormalFrame frame3 = NormalFrame.of(1);
        frame3.pitches(StrategyTest.T0);
        frame3.pitches(StrategyTest.T10);

        NormalFrame frame4 = NormalFrame.of(1);
        frame4.pitches(StrategyTest.T5);
        frame4.pitches(StrategyTest.T4);

        NormalFrame frame5 = NormalFrame.of(1);
        frame5.pitches(StrategyTest.T5);
        frame5.pitches(StrategyTest.T0);

        return Stream.of(
                Arguments.of(frame, "X"),
                Arguments.of(frame2, "9|/"),
                Arguments.of(frame3, "-|/"),
                Arguments.of(frame4, "5|4"),
                Arguments.of(frame5, "5|-")
        );
    }

    @DisplayName("마지막 프레임 출력결과 테스트")
    @ParameterizedTest
    @MethodSource("provideFinalFrame")
    void finalFrame(Frame frame, String result) {
        assertThat(frame.getResultString()).isEqualTo(result);
    }

    private static Stream<Arguments> provideFinalFrame() {
        FinalFrame frame1 = new FinalFrame(10);
        FinalFrame frame2 = new FinalFrame(10);

        frame1.pitches(StrategyTest.T10);
        frame1.pitches(StrategyTest.T5);
        frame1.pitches(StrategyTest.T5);

        frame2.pitches(StrategyTest.T0);
        frame2.pitches(StrategyTest.T10);
        frame2.pitches(StrategyTest.T10);

        return Stream.of(
                Arguments.of(frame1, "X|5|/"),
                Arguments.of(frame2, "-|/|X")
        );
    }


}
